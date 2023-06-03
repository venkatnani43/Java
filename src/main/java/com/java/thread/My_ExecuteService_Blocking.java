package com.java.thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class My_ExecuteService_Blocking {

	public static void readFile(String fileName) {
		System.out.println(Thread.currentThread().getName());
		long startTime = System.currentTimeMillis(); // Get the start Time
		long endTime = 0;
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
			endTime = System.currentTimeMillis(); // Get the end Time

			System.out.println("Differencce in Seconds: " + (endTime - startTime) / 1000); // Print the difference in
																							// seconds

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

		String file1 = "D:\\Spring Modules\\Java\\Files\\abc.txt";
		String file2 = "D:\\Spring Modules\\Java\\Files\\xyz.txt";
		String file3 = "D:\\Spring Modules\\Java\\Files\\sample.txt";

		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		long startTime = System.currentTimeMillis(); // Get the start Time
		long endTime = 0;
		int count = 0;
		
		Future<Integer> f1 = executor.submit(() -> {

			readFile(file1);
			return 1;
		});

		Integer i1 = f1.get(); // blocking
		System.out.println("future 1 =" + i1);
		
		

		Future<Integer> f2 = executor.submit(() -> {

			readFile(file2);
			return 2;
		});

		Integer i2 = f2.get();// blocking
		System.out.println("future 2 =" + i2);
		
		

		Future<Integer> f3 = executor.submit(() -> {

			readFile(file3);
			return 3;
		});

		Integer i3 = f3.get(); // blocking
		System.out.println("future 3 =" + i3);
		
		System.out.println("count:" + count);
		endTime = System.currentTimeMillis(); // Get the end Time

		System.out.println("Differencce in Seconds: " + (endTime - startTime) / 1000); // Print the difference in


		executor.shutdown();

	}

}
