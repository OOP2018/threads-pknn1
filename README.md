## Threads and Synchronization

This lab illustrates the problem of synchronization when many threads are operating on a shared object.  The general issue is called "thread safety", and it is a major cause of errors in computer software.

## Assignment

To the problems on the lab sheet and record your answers here.

1. Record average run times.
2. Write your explanation of the results.  Use good English and proper grammar.  Also use good Markdown formatting.

## ThreadCount run times

These are the average runtime using 3 or more runs of the application.
The Counter class is the object being shared by the threads.
The threads use the counter to add and subtract values.

| Counter class           | Limit              | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|
| Unsynchronized counter  |     10,000,000     |  0.0152326084s  |
| Using ReentrantLock     |     10,000,000     |  1.1855800396s  |
| Syncronized method      |     10,000,000     |  0.5401960115s  |
| AtomicLong for total    |     10,000,000     |  0.4446862867s  |

## 1. Using unsynchronized counter object

Question 1.1: Total should be zero but the actual result sometimes not zero, and most of them are not equals.
AddTask and SubtractTask are run on different thread, and they both run asynchronously then when both of those accessing the same data, 
 that data can be overwrite and give a wrong calculation.    
Question 1.2: The average time is 0.014215898800000001s when run 5 times with limit equals to 10,000,000.  

## 2. Implications for Multi-threaded Applications
Question 2: This behavior can be annoying when somebody transfer the money and the owner withdraw it at the same time. Both transaction might
access the data at the same time and affects to balance of the account.

## 3. Counter with ReentrantLock

Question 3.1: The total is always zero.    
Question 3.2: The Lock is the interface that managing the thread, Lock will lock one thread to be able to run at the time.     
Question 3.3: `ReentrantLock` lock the object to be accessed by one thread at the time, we better use it when we want to 
lock the data that can be overwrite.     

## 4. Counter with synchronized method

Question 4.1: The totals is always zero.    
Question 4.2: The synchronized method running on the thread will block another thread from access to the data until the first thread
is done with object.     
Question 4.3: synchronized is the keyword that added to the class or method to make it syncable with another structure, that means
any process with multi-thread processing will be visible for another thread but the access will be blocked. We better use it when we want to 
lock the data that can be overwrite.       

## 5. Counter with AtomicLong

Question 5.1: `AtomicCounter` is said by the API docs that it is **Lock-free, Thread-safe**, it can somehow managed the data without us knowing 
what to implements from it.

Question 5.2: `AtomicCounter` is easy to use and rocket fast. We can use `Atomic Counter` when we want speed-up the process.      

## 6. Analysis of Results

Question 6.1: `AtomicCounter` is the fastest and `Lock` is the slowest.     
Question 6.2: Synchronized.

## 7. Using Many Threads (optional)

| Counter class           | Limit              | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|
| Unsynchronized counter  |     10,000,000     |  0.169199s      |
| Using ReentrantLock     |     10,000,000     |  6.633804s      |
| Syncronized method      |     10,000,000     |  8.973703s      |
| AtomicLong for total    |     10,000,000     |  2.960015s      |