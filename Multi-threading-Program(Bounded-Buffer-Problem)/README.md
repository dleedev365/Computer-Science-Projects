# Purpose
The purpose of the project is to design a multi-threaded program with use of semaphore and mutex to avoid deadlocks

# Technologies
  - POSIX Threads

# Process
### Step 1: The Language of Choice
The project involves use of a processor which sits at the core of a computer. To get to the core,  a programming language must be low-level, which means it must provide little or no abstraction of programming concepts and be very close to writing actual machine instructions. My language of choice was C as it is a low-level language and supports multi-threaded programming with POSIX threads library.

### Step 2: The Understanding of Multi-threading
<img src="https://slideplayer.com/slide/9128173/27/images/3/A+typical+program+Multi-Thread+Start+Task1+Task2+Task1+Task2+Task3.jpg" width="500">
The very first step I took before delving into coding was understanding the concept of multi-threading. Essentially, it is a programming model that divides the processor to perform different tasks at the same time. In other words, a program or an operating system process manages its use by more than one user at a time and multiple requests by the same user without having to have multiple copies of the programming running in the computer. Multi-threaded programs can help with improving the performance of a device or even allow adding new features without the need to change or upgrade the processor.

### Step 3: The Implementation
To create the program, I first needed to create two groups of threads – worker thread and consumer thread. The worker threads pick a tool from one resource, create a new product with a material randomly generated from other resource and pushes a complete product into anther resource. Then, the consumer threads use a product created by a worker thread. As both types of threads share a common resource such as an array, semaphore and mutex which work as a key to control access to the common resource.
  - [View Source Code](/main.c)

### Step 4: Final Result & Evaluation
<img src="https://github.com/danlee0528/producer-consumer-problem/blob/master/pcp.JPG" width="500">
As a result, threads continued to produce and consume materials from a common resource without a deadlock (different threads are blocked to access the common resource, think it as a traffic jam at a 4-way intersection).  I learned that multi-threaded programming efficiently uses the computer processor to do more than one tasks at a time. However, the design format of outputs could’ve been built better in a way that it only shows changing materials from resources. The current program displays outputs whenever a thread is executed even if there is no change in material from any resource.
