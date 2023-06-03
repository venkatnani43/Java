package com.java.thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class My_CompeltableFuture {
	
	
		
		String file1 = "D:\\Spring Modules\\Java\\Files\\abc.txt";
		String file2 = "D:\\Spring Modules\\Java\\Files\\xyz.txt";
		String file3 = "D:\\Spring Modules\\Java\\Files\\sample.txt";

		public void runAsync() throws InterruptedException, ExecutionException {

			long startTime = System.currentTimeMillis(); // Get the start Time
			long endTime = 0;
			
			CompletableFuture<Void>  task1 = CompletableFuture.runAsync(() -> {
				readFile(file1);
				
			});
			
			CompletableFuture<Void>  task2 = CompletableFuture.runAsync(() -> {
				readFile(file2);
				
			});

			CompletableFuture<Void>  task3 = CompletableFuture.runAsync(() -> {
				readFile(file3);
				
			});

			// it makes main thread to wait until other threads completed.
			CompletableFuture.allOf(task1, task2, task3).join();

			System.out.println("thread : "+Thread.currentThread().getName());
			endTime = System.currentTimeMillis(); // Get the end Time

			System.out.println("Differencce in Seconds: " + (endTime - startTime) / 1000); // Print the difference in

		}
	
		public void supplyAsync() throws InterruptedException, ExecutionException {


			long startTime = System.currentTimeMillis(); // Get the start Time
			long endTime = 0;
			CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
				readFile(file1);
				return 1;
			});
			CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
				readFile(file2);
				return 2;
			});

			CompletableFuture<Integer> task3 = CompletableFuture.supplyAsync(() -> {
				readFile(file3);
				return 3;
			});

			// it makes main thread to wait until other threads completed.
			CompletableFuture.allOf(task1, task2, task3).join();

			System.out.println("thread : "+Thread.currentThread().getName());
			endTime = System.currentTimeMillis(); // Get the end Time

			System.out.println("Differencce in Seconds: " + (endTime - startTime) / 1000); // Print the difference in
		}
		
		public void supplyAsync_Executor() throws InterruptedException, ExecutionException {
           //thread obtained from the Executor or from the ForkJoinPool.commonPool() 
			ExecutorService executor = Executors.newFixedThreadPool(3);
			
			long startTime = System.currentTimeMillis(); // Get the start Time
			long endTime = 0;
			CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
				readFile(file1);
				return 1;
			},executor);
			CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
				readFile(file2);
				return 2;
			},executor);

			CompletableFuture<Integer> task3 = CompletableFuture.supplyAsync(() -> {
				readFile(file3);
				return 3;
			},executor);

			// it makes main thread to wait until other threads completed.
			CompletableFuture.allOf(task1, task2, task3).join();

			System.out.println("thread : "+Thread.currentThread().getName());
			endTime = System.currentTimeMillis(); // Get the end Time

			System.out.println("Differencce in Seconds: " + (endTime - startTime) / 1000); // Print the difference in
			executor.shutdown();
		}
		
		public void runAsync_Executor() throws InterruptedException, ExecutionException {
	           //thread obtained from the Executor or from the ForkJoinPool.commonPool() 
				ExecutorService executor = Executors.newFixedThreadPool(3);
				
				long startTime = System.currentTimeMillis(); // Get the start Time
				long endTime = 0;
				CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
					readFile(file1);
					
				},executor);
				CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
					readFile(file2);
					
				},executor);

				CompletableFuture<Void> task3 = CompletableFuture.runAsync(() -> {
					readFile(file3);
					
				},executor);

				// it makes main thread to wait until other threads completed.
				CompletableFuture.allOf(task1, task2, task3).join();

				System.out.println("thread : "+Thread.currentThread().getName());
				endTime = System.currentTimeMillis(); // Get the end Time

				System.out.println("Differencce in Seconds: " + (endTime - startTime) / 1000); // Print the difference in
				executor.shutdown();
			}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		My_CompeltableFuture m=new My_CompeltableFuture();
		m.supplyAsync_Executor();
		
		
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


}
