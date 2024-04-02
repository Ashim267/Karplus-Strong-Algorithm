// File name: DblQueue.java
// Author: Ashim Chand
// userid: chanda
// Email: ashimchand@vanderbilt.edu
// Class: CS2201
// Honor Statement: Will not use unfair means
// Assignment Number: 6
// Description: Queue Implimentation
// Last Changed: 3/26/2024
//

public class DblQueue {

    // TODO
    // Your task is to completely implement a Queue class for holding
    // values of type double. This class should create an unbounded
    // queue class that is based on a singly linked list. Make sure all
    // methods are as specified in the project spec, otherwise our
    // grading code will not work with it and you will be penalized.
        public static class Node {
            double data;
            Node next;

            Node(double data) {
                this.data = data;
                this.next = null;
            }
        }

        private Node front, rear;
        private int size;

        public DblQueue() {
            front = rear = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void enqueue(double item) {
            Node newNode = new Node(item);
            if (isEmpty()) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            size++;
        }

        public void dequeue() {
            if (isEmpty()) {
                throw new RuntimeException("Queue Empty");
            }
            front = front.next;
            if (front == null) {
                rear = null;
            }
            size--;
        }

        public double front() {
            if (isEmpty()) {
                throw new RuntimeException("Queue Empty");
            }
            return front.data;
        }

        public int size() {
            return size;
        }

        public DblQueue clone() {
            DblQueue clone = new DblQueue();
            Node current = this.front;
            while (current != null) {
                clone.enqueue(current.data);
                current = current.next;
            }
            return clone;
        }

        public boolean equals(Object other) {
            if (!(other instanceof DblQueue)) return false;
            DblQueue otherQ = (DblQueue) other;
            if (this.size != otherQ.size) return false;
            Node thisCurrent = this.front;
            Node otherCurrent = otherQ.front;
            while (thisCurrent != null) {
                if (thisCurrent.data != otherCurrent.data) {
                    return false;
                }
                thisCurrent = thisCurrent.next;
                otherCurrent = otherCurrent.next;
            }
            return true;
        }
    }


