package com.solvd.laba.block1.universityEnrollment.university;

public class CustomLinkedList<T> {
    private Node<T> head;
    private int size;

    // Constructor
    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Node class
    private static class Node<T> {
        T data;
        Node<T> next;

        // Constructor
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Add element at the end
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Remove element at a specific index
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    // Display elements
    public void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Get size of the linked list
    public int size() {
        return size;
    }

    // Get element at a specific index
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }
}
