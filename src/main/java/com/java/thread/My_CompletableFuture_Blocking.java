package com.java.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class My_CompletableFuture_Blocking {

	public static void blocking() throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(3);
		CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
			try {
				readFile();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}, executor);
		System.out.println(task1.get());// blocking
		CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
			try {
				readFile();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 2;
		}, executor);
		System.out.println(task2.get());// blocking
		CompletableFuture<Integer> task3 = CompletableFuture.supplyAsync(() -> {
			try {
				readFile();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 3;
		}, executor);
		System.out.println(task3.get());// blocking
		
		executor.shutdown();

	}

	public static void Non_Blocking() throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(3);
		CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
			try {
				readFile();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}, executor);

		CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
			try {
				readFile();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 2;
		}, executor);

		CompletableFuture<Integer> task3 = CompletableFuture.supplyAsync(() -> {
			try {
				readFile();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 3;
		}, executor);

		System.out.println(task1.get());
		System.out.println(task2.get());
		System.out.println(task3.get());
		executor.shutdown();
	}

	public static void readFile() throws InterruptedException {
		System.out.println(Thread.currentThread().getName());
		Thread.sleep(10000);
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		blocking();
	}
}
