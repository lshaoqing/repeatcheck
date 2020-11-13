package com.fu.linmou.structure.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/9/25 10:39
 * @Version: 1.0
 */
public class HashDouble {
    public static void main(String[] args) throws IOException {
        DataItem2 aDataItem2;
        int aKey,size,n;

        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();

        HashTable2 theHashTable2 = new HashTable2(size);

        for(int j=0; j<n; j++) {
            aKey = (int)(Math.random() * 2 * size);
            System.out.print(aKey + " ");

            aDataItem2 = new DataItem2(aKey);
            theHashTable2.insert(aKey,aDataItem2);
        }
        System.out.println(" ");
        while(true) {
            System.out.print("Enter first letter of ");
            System.out.print("show,insert,delete,or find: ");
            char choice = getChar();
            switch (choice) {
                case 's':
                    theHashTable2.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem2 = new DataItem2(aKey);
                    theHashTable2.insert(aKey,aDataItem2);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable2.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem2 = theHashTable2.find(aKey);
                    if(aDataItem2 != null){
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

 class DataItem2 {
        private int iData;

        public DataItem2(int ii) {
            iData = ii;
        }

        public int getKey() {
            return iData;
        }
    }
    
    class HashTable2{
        private DataItem2[] hashArray;
        private int arraySize;
        private DataItem2 nonItem;

        HashTable2(int size) {
            arraySize = size;
            hashArray = new DataItem2[arraySize];
            nonItem = new DataItem2(-1);
        }

        public void displayTable() {
            System.out.print("Table: ");
            for(int j=0; j<arraySize; j++) {
                if(hashArray[j] != null){
                    System.out.print(hashArray[j].getKey() + " ");
                }
                else{
                    System.out.print("** ");
                }
            }
            System.out.println(" ");
        }

        public int hashFunc1(int key) {
            return key % arraySize;
        }

        public int hashFunc2(int key) {
            return 5 - key % 5;
        }

        public void insert(int key,DataItem2 item) {
            int hashVal = hashFunc1(key);
            int stepSize = hashFunc2(key);

            while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
                hashVal += stepSize;
                hashVal %= arraySize;
            }
            hashArray[hashVal] = item;
        }

        public DataItem2 delete(int key) {
            int hashVal = hashFunc1(key);
            int stepSize = hashFunc2(key);
            while(hashArray[hashVal] != null) {
                if(hashArray[hashVal].getKey() == key) {
                    DataItem2 temp = hashArray[hashVal];
                    hashArray[hashVal] = nonItem;
                    return temp;
                }
                hashVal += stepSize;
                hashVal %= arraySize;
            }
            return null;
        }

        public DataItem2 find(int key) {
            int hashVal = hashFunc1(key);
            int stepSize = hashFunc2(key);

            while(hashArray[hashVal] != null) {
                if(hashArray[hashVal].getKey() == key) {
                    return hashArray[hashVal];
                }
                hashVal += stepSize;
                hashVal %= arraySize;
            }
            return null;
        }

    }
