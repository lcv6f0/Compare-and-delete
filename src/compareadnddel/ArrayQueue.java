/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compareadnddel;

/**
 *
 * @author Christian
 */
class ArrayQueue<E> implements Queue<E> {

    public static final int DEFAULT_CAPACITY = 10000000;
    private int f, numElem;
    private int capacity;
    private E s[];

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        f = 0;
        numElem = 0;
        s = (E[]) new Object[capacity];
    }

    // Returns the number of elements in the queue
    public int size() {
        return numElem;
    }
//clear the queue

    public void clear() {
        f = 0;
        numElem = 0;
        s = (E[]) new Object[capacity];
    }

    // Returns true if queue is empty.
    @Override
    public boolean isEmpty() {
        return numElem == 0;
    }

    // Returns true if queue is full.
    @Override
    public boolean isFull() {
        return numElem == s.length;
    }

    //Returns the front item from the queue.
    //But does not remove it.
    @Override
    public E front() throws QueueException {
     
        return s[f];
    }

    // Inserts item at the rear of the queue.
    @Override
    public void enqueue(E item) throws QueueException {
        if (isFull()) {
            throw new QueueException("Queue is full");
        }
        int freeIndex = (f + numElem) % s.length;
        s[freeIndex] = item;
        numElem++;
    }

    // Inserts item at the rear of the queue.
    @Override
    public E dequeue() throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Queue is empty");
        }
        E item = s[f];
        s[f] = null;
        f = (f + 1) % s.length;
        numElem--;
        return item;
    }

    //To use to display the content of the queue.
    @Override
    public String toString() {
        int ff = f, r = (f + numElem) % s.length;
        String str = "[";
        if (size() > 0) {
            str += s[ff];
        }
        ff = (ff + 1) % s.length;
        while (ff != r) {
            str += ", " + s[ff];
            ff = (ff + 1) % s.length;
        }
        return str + "]";
    }
}

/**
 *
 * @author L
 */
interface Queue<E> {

    // Returns true if queue is empty.
    public boolean isEmpty();

    //Returns the front item from the queue.
    //But does not remove it.
    public E front() throws QueueException;

    // Inserts item at the rear of the queue.
    public void enqueue(E item) throws QueueException;

    // Removes an item from the rear the queue and returns it.
    public E dequeue() throws QueueException;

    // Returns true if the queue is full.
    public boolean isFull();
}
