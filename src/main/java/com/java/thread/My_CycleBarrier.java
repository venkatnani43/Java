package com.java.thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class My_CycleBarrier {
	public void perform() throws InterruptedException {
		long startTime = System.currentTimeMillis(); // Get the start Time

		// it makes main thread to wait until other threads completed.

		Runnable mainThread = () -> {

			System.out.println(" main thread");

			long endTime = System.currentTimeMillis(); // Get the end Time

			System.out.println("Differencce in Seconds: " + (endTime - startTime) / 1000); // Print the difference in

		};

		CyclicBarrier cd = new CyclicBarrier(3, mainThread);
		ExecutorService executor = Executors.newFixedThreadPool(3);

		executor.execute(() -> {

			readFile(file1);
			try {
				cd.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		executor.execute(() -> {

			readFile(file2);
			try {
				cd.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		executor.execute(() -> {

			readFile(file3);
			try {
				cd.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		executor.shutdown();

	}

	public static void main(String[] args) throws InterruptedException {
		My_CycleBarrier m = new My_CycleBarrier();
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
