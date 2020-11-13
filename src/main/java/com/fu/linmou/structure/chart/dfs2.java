package com.fu.linmou.structure.chart;

/**
 * @author linMou
 * @Description: 数据结构 --图  广度优先搜索  采用数据结构  队列
 * @Date: 2020/10/13 14:11
 * @Version: 1.0
 */
public class dfs2 {
}

class Queue {
    private final int SIZE = 20;
    private int[] queArray;
    private int front;
    private int rear;

    public Queue() {
        queArray = new int[SIZE];
        front = 0;
        rear = -1;
    }

    public void insert(int j) {
        if(rear == SIZE-1)
            rear = -1;
        queArray[++rear] = j;
    }

    public int remove() {
        int temp = queArray[front++];
        if(front == SIZE)
            front = 0;
        return temp;

    }

    public boolean isEmpty() {
        return (rear+1 == front || (front+SIZE-1==rear));
    }
}

class  Vertey {
    public char lable;
    public boolean wasVisited;

    public Vertey(char lab) {
        lable = lab;
        wasVisited = false;
    }
}

class Graph2 {
    private final int MAX_VERTS = 20;
    private Vertey vertexList[];
    private int adjmat[][];
    private int nVerts;
    private Queue theQueue;

    public Graph2() {
        vertexList = new Vertey[MAX_VERTS];
        adjmat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for(int j=0; j<MAX_VERTS; j++)
            for(int k=0; k<MAX_VERTS; k++)
            adjmat[j][k] = 0;
            theQueue = new Queue();
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertey(lab);
    }

    public void addEdge(int start, int end) {
        adjmat[start][end] = 1;
        adjmat[end][start] = 1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].lable +  " ");
    }

    public void bfs() {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        theQueue.insert(0);
        int v2;

        while (!theQueue.isEmpty()) {
            int v1 = theQueue.remove();
            while((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                vertexList[v2].wasVisited = true;
                displayVertex(v2);
                theQueue.insert(v2);
            }
        }
        for(int j =0; j<nVerts; j++)
            vertexList[j].wasVisited = false;
    }

    public int getAdjUnvisitedVertex(int v) {
        for(int j=0; j<nVerts; j++)
            if(adjmat[v][j] == 1 && vertexList[j].wasVisited == false)
                return j;
        return -1;
    }
}

class  BFSApp {
    public static void main(String[] args) {

        Graph2 theGraph = new Graph2();
        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');

        theGraph.addEdge(0,1);
        theGraph.addEdge(1,2);
        theGraph.addEdge(0,3);
        theGraph.addEdge(3,4);

        System.out.print("Visits: ");
        theGraph.bfs();
        System.out.println(" ");
    }
}