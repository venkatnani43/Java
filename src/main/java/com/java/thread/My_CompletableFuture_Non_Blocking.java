package com.java.thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class My_CompletableFuture_Non_Blocking {

	String file1 = "D:\\Spring Modules\\Java\\Files\\abc.txt";
	String file2 = "D:\\Spring Modules\\Java\\Files\\xyz.txt";
	String file3 = "D:\\Spring Modules\\Java\\Files\\sample.txt";

	public void async() throws InterruptedException, ExecutionException {

		long startTime = System.currentTimeMillis(); // Get the start Time
		long endTime = 0;
		CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
			readFile(file1);
			return 0;
		});
		CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
			readFile(file2);
			return 0;
		});

		CompletableFuture<Integer> task3 = CompletableFuture.supplyAsync(() -> {
			readFile(file3);
			return 0;
		});

		// it makes main thread to wait until other threads completed.
		
		  CompletableFuture.allOf(task1, task2, task3).join();
		  
		  System.out.println("thread : "+Thread.currentThread().getName()); endTime =
		  System.currentTimeMillis(); // Get the end Time
		  
		  System.out.println("Differencce in Seconds: " + (endTime - startTime) /
		  1000); // Print the difference in
		 
	}

	public void readFile(String fileName) {
		System.out.println(Thread.currentThread().getName());
		/*
		 * long startTime = System.currentTimeMillis(); // Get the start Time long
		 * endTime = 0;
		 */
		int count = 0;
		BufferedReader br = null;
		try {
			// Instance of FileReader wrapped in a BufferedReader
			br = new BufferedReader(new FileReader(fileName));
			// Read chars from the file, returns -1 when end of stream is reached
			int i;
			while ((i = br.read()) != -1) {
				char ch = (char) i;
				// System.out.println("char is - " + ch);
				count++;
			}
			System.out.println("count:" + count);
			/*
			 * endTime = System.currentTimeMillis(); // Get the end Time
			 * 
			 * System.out.println("Differencce in Seconds: " + (endTime - startTime) /
			 * 1000); // Print the difference in
			 */ // seconds

		} catch (IOException ioExp) {
			System.out.println("Error while reading file " + ioExp.getMessage());
		} finally {
			try {
				// Close the stream
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		My_CompletableFuture_Non_Blocking m = new My_CompletableFuture_Non_Blocking();
		m.async();

	}

}
