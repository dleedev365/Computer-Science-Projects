/****************************************************************************************
*	AUTHOR: DANIEL LEE								*
*	LAST UPDATE ON: JAN 14, 2018							*
*****************************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <sys/time.h>
#include <math.h>
#include <unistd.h>
#include <semaphore.h>

#define NUM_THREADS 3
#define MATERIAL_BUFFER_MAX 10
#define PRODUCT_BUFFER_MAX 10000
#define TOOL_BUFFER_MAX 3 // fixed
#define TRUE 1
#define FALSE 0

struct buffers {
	char materials[MATERIAL_BUFFER_MAX];
	char products[PRODUCT_BUFFER_MAX];
	char tools[TOOL_BUFFER_MAX];

	int material_buffer_head;
	int material_buffer_tail;
	int material_buffer_current_size;

	int products_buffer_head;
	int products_buffer_tail;
	int products_buffer_current_size;

	int tools_buffer_head;
	int tools_buffer_tail;
	int tools_buffer_current_size;

	sem_t materials_in_use, materials_available;
	sem_t tools_in_use, tools_available;
	sem_t generator_resting, generator_working;
};

pthread_mutex_t material_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t tool_mutex = PTHREAD_MUTEX_INITIALIZER;

pthread_t consumer_threads[NUM_THREADS];
pthread_t producer_threads[NUM_THREADS];

struct buffers buffer;

char material_1 = '1';
char material_2 = '2';
char material_3 = '3';

char tool_x = 'x';
char tool_y = 'y';
char tool_z = 'z';

int count_material_type_A;
int count_material_type_B;
int count_material_type_C;

int count_product_type_A;
int count_product_type_B;
int count_product_type_C;

char product_A = 'A';
char product_B = 'B';
char product_C = 'C';

void update(struct buffers *b) {
	count_material_type_A = 0;
	count_material_type_B = 0;
	count_material_type_C = 0;

	count_product_type_A = 0;
	count_product_type_B = 0;
	count_product_type_C = 0;

	// count how many of each material are generated
	for (int i = 0; i < b->material_buffer_current_size; i++) {
		if (b->materials[(b->material_buffer_head + i) % MATERIAL_BUFFER_MAX] == material_1)
			count_material_type_A++;
		if (b->materials[(b->material_buffer_head + i) % MATERIAL_BUFFER_MAX] == material_2)
			count_material_type_B++;
		if (b->materials[(b->material_buffer_head + i) % MATERIAL_BUFFER_MAX] == material_3)
			count_material_type_C++;
	}

	// count how many of eeach product are put in the output queue
	for (int i = 0; i < b->products_buffer_current_size; i++) {
		if (b->products[(b->products_buffer_head + i) % PRODUCT_BUFFER_MAX] == product_A)
			count_product_type_A++;
		if (b->products[(b->products_buffer_head + i) % PRODUCT_BUFFER_MAX] == product_B)
			count_product_type_B++;
		if (b->products[(b->products_buffer_head + i) % PRODUCT_BUFFER_MAX] == product_C)
			count_product_type_C++;
	}

	printf("Material Buffer [ ");
	for (int i = 0; i < b->material_buffer_current_size; i++) {
		printf("%c ", b->materials[i]);
	}
	printf("]\n");
	printf("Material [1]: %i\n", count_material_type_A);
	printf("Material [2]: %i\n", count_material_type_B);
	printf("material [3]: %i\n", count_material_type_C);

	printf("Tool Buffer [ ");
	for (int i = 0; i < b->tools_buffer_current_size; i++) {
		printf("%c ", b->tools[i]);
	}
	printf("]\n");

	printf("Product Buffer [ ");
	for (int i = 0; i < b->products_buffer_current_size; i++) {
		printf("%c ", b->products[i]);
	}
	printf("]\n");
	printf("\n");
	/*
	printf("Product[A]:%i\n",count_product_type_A);
	printf("Product[B]:%i\n",count_product_type_B);
	printf("Product[C]:%i\n",count_product_type_C);
	*/
}

void insert_material(struct buffers *b, char item) {
	// If empty 
	if (b->material_buffer_current_size == 0) {
		b->material_buffer_head = 0;
		b->material_buffer_tail = 0;
		b->materials[b->material_buffer_tail % MATERIAL_BUFFER_MAX] = item;
		b->material_buffer_current_size++;
	}
	else {
		b->materials[(b->material_buffer_tail + 1) % MATERIAL_BUFFER_MAX] = item;
		b->material_buffer_tail = (b->material_buffer_tail + 1) % MATERIAL_BUFFER_MAX;
		b->material_buffer_current_size++;
	}
}

/*First in First out*/
char grab_material(struct buffers *b) {
	char target;
	/*There are more than one element in the queue*/
	if (b->material_buffer_current_size > 1 && b->material_buffer_current_size <= MATERIAL_BUFFER_MAX) {
		target = b->materials[b->material_buffer_head % MATERIAL_BUFFER_MAX];
		b->material_buffer_head = (b->material_buffer_head + 1) % MATERIAL_BUFFER_MAX;
		b->material_buffer_current_size = b->material_buffer_current_size - 1;
		return target;
	}
	/*only one element in the queue*/
	else if (b->material_buffer_current_size == 1) {
		target = b->materials[b->material_buffer_head % MATERIAL_BUFFER_MAX];
		b->material_buffer_current_size = b->material_buffer_current_size - 1;
		b->material_buffer_head = 0;
		b->material_buffer_tail = 0;
		return target;
	}
	else {
		return -1;
	}
}

void *consumer(void *thread_ID) {
	long tid;
	tid = (long)thread_ID;
	char type = '1' + (int)tid; // id varies from 0 to NUM_thread
								//printf("Consumer thread %ld staring ...\n", tid);

	while (TRUE) {
		if (buffer.materials[buffer.material_buffer_tail] == type && buffer.material_buffer_current_size > 0) {
			sleep(1);//wait 
		}

		sem_wait(&buffer.materials_in_use); //decrement
		pthread_mutex_lock(&material_mutex); /* enter critical region */
											 //printf(" >> [C] Material Critical Section >>\n");
		insert_material(&buffer, type);/* put the material into the input buffers */
		update(&buffer);

		pthread_mutex_unlock(&material_mutex); /* leave critical region */
		sem_post(&buffer.materials_available); /* increment*/
											   //printf("<<  [C] Material Critical Region <<\n");        
	}
	pthread_exit(NULL);
};

void insert_tool(struct buffers *b, char tool) {
	//empty
	if (b->tools_buffer_current_size == 0) {
		b->tools_buffer_head = 0;
		b->tools_buffer_tail = 0;
		b->tools[b->tools_buffer_tail % TOOL_BUFFER_MAX] = tool;
		b->tools_buffer_current_size++;
	}
	else {
		b->tools[(b->tools_buffer_tail + 1) % TOOL_BUFFER_MAX] = tool;
		b->tools_buffer_tail = (b->tools_buffer_tail + 1) % TOOL_BUFFER_MAX;
		b->tools_buffer_current_size++;
	}
};

char grab_tool(struct buffers *b) {
	char target;
	// There are more than one element in the queue
	if (b->tools_buffer_current_size > 1 && b->tools_buffer_current_size <= TOOL_BUFFER_MAX) {
		target = b->tools[(b->tools_buffer_head) % TOOL_BUFFER_MAX];
		b->tools_buffer_head = (b->tools_buffer_head + 1) % TOOL_BUFFER_MAX;
		b->tools_buffer_current_size--;
		return target;
	}
	// Only one element in the queue
	else if (b->tools_buffer_current_size == 1) {
		target = b->tools[(b->tools_buffer_head) % TOOL_BUFFER_MAX];
		b->tools_buffer_current_size--;
		b->tools_buffer_head = 0;
		b->tools_buffer_tail = 0;
		return target;
	}
	else {
		return -1;
	}
}

void insert_product(struct buffers *b, char product) {
	// empty
	if (b->products_buffer_current_size == 0) {
		b->products_buffer_head = 0;
		b->products_buffer_tail = 0;
		b->products[b->products_buffer_tail % PRODUCT_BUFFER_MAX] = product;
		b->products_buffer_current_size++;
	}
	else {
		b->products[(b->products_buffer_tail + 1) % PRODUCT_BUFFER_MAX] = product;
		b->products_buffer_tail = (b->products_buffer_tail + 1) % PRODUCT_BUFFER_MAX;
		b->products_buffer_current_size++;
	}
};


void *producer(void *thread_ID) {
	//long tid;
	char current_tool = '?';
	char current_material = '?';
	//tid = (long)thread_ID;
	//printf("Producer thread %ld staring ...\n",tid);
	int tools_mismatch = FALSE;

	while (TRUE) {
		if (!tools_mismatch) {

			/* grab a material*/
			sem_wait(&buffer.materials_available); // decrement
			pthread_mutex_lock(&material_mutex); /* enter critical region */
												 //printf(" >> [P] Material Critical Section >>\n");

			current_material = grab_material(&buffer);
			update(&buffer);

			pthread_mutex_unlock(&material_mutex); /* leave critical region */
			sem_post(&buffer.materials_in_use); // increment
												// printf("<<  [P] Material Critical Region <<\n");      
		}

		/* grab a tool*/
		sem_wait(&buffer.tools_available); // decrement
		pthread_mutex_lock(&tool_mutex); /* enter critical region */
										 //printf(" >> [P] Tool Critical Section >>\n");

		current_tool = grab_tool(&buffer);
		update(&buffer);

		pthread_mutex_unlock(&tool_mutex); /* leave critical region */
		sem_post(&buffer.tools_in_use); // increment
										// printf("<<  [P] Material Critical Region <<\n");

		if (current_tool == 'x' && current_material == '1') {
			insert_product(&buffer, 'A');
		}
		else if (current_tool == 'y' && current_material == '2') {
			insert_product(&buffer, 'B');
		}
		else if (current_tool == 'z' && current_material == '3') {
			insert_product(&buffer, 'C');
		}
		else {
			sleep(1);
		}

		/* put a tool back*/
		sem_wait(&buffer.tools_in_use); // decrement
		pthread_mutex_lock(&tool_mutex); /* enter critical region */
										 //printf(" >> [P] Tool Critical Section >>\n");

		insert_tool(&buffer, current_tool);
		update(&buffer);

		pthread_mutex_unlock(&tool_mutex); /* leave critical region */
		sem_post(&buffer.tools_available); // incrementt
										   //printf("<<  [P] Tool Critical Region <<\n");  
	}

	pthread_exit(NULL);
};

int main(int argc, char *argv[]) {

	insert_tool(&buffer, 'x');
	insert_tool(&buffer, 'y');
	insert_tool(&buffer, 'z');

	sem_init(&buffer.materials_in_use, 0, 0);
	sem_init(&buffer.materials_available, 0, MATERIAL_BUFFER_MAX);

	sem_init(&buffer.tools_in_use, 0, 0);
	sem_init(&buffer.tools_available, 0, TOOL_BUFFER_MAX);


	for (int i = 0; i < NUM_THREADS; i++) {
		pthread_create(&consumer_threads[i], NULL, consumer, (void *)i);
		pthread_create(&producer_threads[i], NULL, producer, (void *)i);
	}

	/* Wait until thread is done its work */
	for (int i = 0; i < NUM_THREADS; i++) {
		pthread_join(consumer_threads[i], NULL);
		pthread_join(producer_threads[i], NULL);
	}

	/* Destory threads & mutexes*/
	pthread_exit(NULL);

	return 0;
}
