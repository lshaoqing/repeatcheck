package com.fu.linmou.structure.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author linMou
 * @Description: 哈希表 ----- 链地址法
 * @Date: 2020/9/25 16:28
 * @Version: 1.0
 */
public class HashChain {
}

class Link {
    private int iData;
    public Link next;

    public Link(int it) {
        iData = it;
    }

    public int getKey() {
        return iData;
    }

    public void displayLink() {
        System.out.print(iData + " ");
    }
}

class SortedList {
    private Link first;

    public void SortedList() {
        first = null;
    }

    public void insert(Link theLink) {
        int key = theLink.getKey();
        Link previous = null;
        Link current = first;

        while(current != null && key > current.getKey()) {
            previous = current;
            current = current.next;
        }
        if(previous == null){
            first = theLink;
        }else {
            previous.next = theLink;
        }
        theLink.next = current;
    }

    public void delete(int key) {
        Link previous = null;
        Link current = first;

        while( current != null && key != current.getKey()) {
            previous = current;
            current = current.next;
        }
        if(previous == null) {
            first = first.next;
        }else {
            previous.next = current.next;
        }
    }

    public Link find(int key) {
        Link current = first;

        while(current != null && current.getKey() <= key) {
            if(current.getKey() == key) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void displayList() {
        System.out.print("List(first -->last): ");
        Link current = first;
        while(current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }
}

class HashTable3 {
    private SortedList[] hashArray;
    private int arraySize;

    public HashTable3(int size) {
        arraySize = size;
        hashArray = new SortedList[arraySize];
        for(int j = 0; j< arraySize; j++) {
            hashArray[j] = new SortedList();
        }
    }

    public void displayTable() {
        for(int j = 0; j < arraySize; j++) {
            System.out.print(j+ ". ");
            hashArray[j].displayList();
        }
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }

    public void insert(Link theLink) {
        int key = theLink.getKey();
        int hashVal = hashFunc(key);
        hashArray[hashVal].insert(theLink);
    }

    public void delete(int key) {
        int hashVal = hashFunc(key);
        hashArray[hashVal].delete(key);
    }

    public Link find(int key) {
        int hashVal = hashFunc(key);
        Link theLink = hashArray[hashVal].find(key);
        return theLink;
    }
}

class HashChainApp{
    public static void main(String[] args) throws IOException {
        int aKey;
        Link aDataItem;
        int size,n,keysPerCell = 100;

        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();

        HashTable3 theHashTable = new HashTable3(size);
        for(int j = 0; j<n; j++) {
            aKey = (int)(Math.random()*keysPerCell*size);
            aDataItem = new Link(aKey);
            theHashTable.insert(aDataItem);
        }
        while(true) {
            System.out.print("Enter first letter of ");
            System.out.print("show,insert,delete,or find: ");
            char choice = getChar();
            switch (choice) {
                case 's':
                    theHashTable.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem = new Link(aKey);
                    theHashTable.insert(aDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem = theHashTable.find(aKey);
                    if(aDataItem != null){
                        System.out.println("Found " + aKey);
                    } else{
                        System.out.println("Could not find " + aKey);
                    }
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
