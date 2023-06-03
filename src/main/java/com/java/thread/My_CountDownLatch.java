package com.java.thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class My_CountDownLatch {

	public void perform() throws InterruptedException {

		long startTime = System.currentTimeMillis(); // Get the start Time
		long endTime = 0;
		
		CountDownLatch cd = new CountDownLatch(3);
		ExecutorService executor = Executors.newFixedThreadPool(3);

		executor.execute(() -> {
			
			readFile(file1);
			cd.countDown();

		});

		executor.execute(() -> {
			
			readFile(file2);
			cd.countDown();

		});

		executor.execute(() -> {
		
			readFile(file3);
			cd.countDown();

		});

		////it makes  main thread to wait until other threads completed.
		cd.await();

		System.out.println(Thread.currentThread().getName() + " thread");

		endTime = System.currentTimeMillis(); // Get the end Time

		System.out.println("Differencce in Seconds: " + (endTime - startTime) / 1000); // Print the difference in

		executor.shutdown();

	}

	public static void main(String[] args) throws InterruptedException {
		My_CountDownLatch m=new My_CountDownLatch();
		m.perform();
	}

	String file1 = "D:\\Spring Modules\\Java\\Files\\abc.txt";
	String file2 = "D:\\Spring Modules\\Java\\Files\\xyz.txt";
	String file3 = "D:\\Spring Modules\\Java\\Files\\sample.txt";

	public static void readFile(String fileName) {
		System.out.println(Thread.currentThread().getName());

		int count = 0;
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(fileName));

			int i;
			while ((i = br.read()) != -1) {
				char ch = (char) i;

				count++;
			}
			System.out.println("count:" + count);

		} catch (IOException ioExp) {
			System.out.println("Error while reading file " + ioExp.getMessage());
		} finally {
			try {

				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
