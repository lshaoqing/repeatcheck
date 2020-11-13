package com.fu.linmou.structure.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author linMou
 * @Description:  堆排序
 * @Date: 2020/10/12 13:54
 * @Version: 1.0
 */
public class HeapSort {
}

class Node2{
    private int iData;

    public Node2(int key) {
        iData = key;
    }

    public int getKey() {
        return iData;
    }
}

class Heap2{
    private Node2[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap2(int mx){
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node2[maxSize];
    }

    public Node2 remove() {
        Node2 root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public void trickleDown(int index) {
        int largerChild;
        Node2 top = heapArray[index];
        while(index <currentSize/2) {
            int leftChild = 2*index +1;
            int rightChild = leftChild + 1;

            if(rightChild <currentSize && heapArray[leftChild].getKey() <heapArray[rightChild].getKey())
                largerChild = rightChild;
            else
                largerChild = leftChild;

            if(top.getKey() >= heapArray[largerChild].getKey())
                break;

            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index] = top;
    }

    public void displayHeap() {
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = "...................";
        System.out.println(dots+dots);

        while (currentSize > 0) {
            if(column == 0)
                for(int k=0; k<nBlanks; k++)
                    System.out.print(' ');
            System.out.print(heapArray[j].getKey());

            if(++j == currentSize)
                break;

            if(++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            }else
                for(int k=0; k<nBlanks*2-2; k++)
                    System.out.print(' ');
        }
        System.out.println("\n" + dots + dots);
    }

    public void displayArray() {
        for(int j=0; j<maxSize; j++)
            System.out.print(heapArray[j].getKey() + " ");
        System.out.println("");
    }

    public void insertAt(int index, Node2 newNode) {
        heapArray[index] = newNode;
    }

    public void incrementSize() {
        currentSize++;
    }
}

class HeapSortApp{
    public static void main(String[] args) throws IOException {
        int size,j;
        System.out.print("Enter number of items: ");
        size = getInt();
        Heap2 theHeap = new Heap2(size);

        for(j=0; j<size; j++) {
            int random = (int)(Math.random() * 100);
            Node2 newNode = new Node2(random);
            theHeap.insertAt(j,newNode);
            theHeap.incrementSize();
        }
        System.out.print("Random: ");
        theHeap.displayArray();

        for(j = size/2-1; j>=0; j--)
            theHeap.trickleDown(j);

        System.out.print("Heap:   ");
        theHeap.displayArray();
        theHeap.displayHeap();

        for(j=size-1; j>=0; j--) {
            Node2 biggestNode = theHeap.remove();
            theHeap.insertAt(j,biggestNode);
        }
        System.out.print("Sorted: ");
        theHeap.displayArray();
        theHeap.displayHeap();
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
