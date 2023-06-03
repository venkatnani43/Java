package com.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class My_CompletableFuture_Non_Blocking_2 {

	public static Integer add(int a, int b) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

		}
		return a + b;

	}

	public static Integer multiply(int result) {
		// Thread.sleep(1000);
		return result * 2;
	}

	public void perform() throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<CompletableFuture<Integer>> list = new ArrayList<>();
		List<Integer> finalResult = new ArrayList<>();

		for (int i = 1; i <= 60; i++) {
			int k = i;
			list.add(CompletableFuture.supplyAsync(() -> add(k, 2), executor).thenApplyAsync(result -> multiply(result),
					executor));

		}
		for (CompletableFuture<Integer> future : list) {
			finalResult.add(future.get()); // 4
		}

		System.out.println(finalResult);

		executor.shutdown();
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		My_CompletableFuture_Non_Blocking_2 m = new My_CompletableFuture_Non_Blocking_2();
		m.perform();
	}

}
