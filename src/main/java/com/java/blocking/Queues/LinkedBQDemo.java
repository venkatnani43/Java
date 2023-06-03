package com.java.blocking.Queues;

import java.nio.Buffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBQDemo {
	  public static void main(String[] args) {
		  LinkBuffer buffer = new LinkBuffer();
	    // Starting two threads
	    ExecutorService executor = Executors.newFixedThreadPool(2);
	    executor.execute(new LinkProdTask(buffer));
	    executor.execute(new LinkConTask(buffer));
	    executor.shutdown();
	  }
	}

	/**
	 * Producer class
	*/
	class LinkProdTask implements Runnable{
		LinkBuffer buffer;
	  LinkProdTask(LinkBuffer buffer){
	    this.buffer = buffer;
	  }
	  @Override
	  public void run() {
	    for(int i = 0; i < 2000; i++){
	      buffer.put(i);
	    }
	  }
	}

	/**
	 * Consumer Class
	*/
	class LinkConTask implements Runnable{
		LinkBuffer buffer;
	  LinkConTask(LinkBuffer buffer){
	    this.buffer = buffer;
	  }
	  @Override
	  public void run() {
	    for(int i = 0; i < 2000; i++){
	      buffer.get();;
	    }
	  }    
	}

	//Shared class used by threads
	class LinkBuffer{
	  int i;
	  // Bounded LinkedBlockingQueue of size 1
	  BlockingQueue<Integer> linkedBlockingQ = new LinkedBlockingQueue<Integer>(1);
	  public void get(){
	    try {
	      // take method to get from blockingqueue
	      int i = linkedBlockingQ.take();
	      System.out.println("Consumer recd - " + i);
	    } catch (InterruptedException e) {
	      System.out.println("Error " + e.getMessage());
	    }
	  }
	    
	  public void put(int i){
	    this.i = i;
	    try {
	      // putting in blocking queue
	      linkedBlockingQ.put(i);
	      System.out.println("Putting - " + i);
	    } catch (InterruptedException e) {
	      System.out.println("Error " + e.getMessage());
	    }      
	  }
	}
