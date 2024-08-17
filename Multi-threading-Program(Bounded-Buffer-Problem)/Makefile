all: x

x: main.o
	gcc	-o x main.o -pthread

main.o: main.c
	gcc -c -Wall -pthread main.c

clean:
	rm -f x *.o