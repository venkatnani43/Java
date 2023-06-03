package com.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Real_understanding_Executor_CompletableFuture {

	public void perform55_blocking() throws InterruptedException, ExecutionException {

		// no parallel execution, each task will be executed one after another by threads
		ExecutorService service = Executors.newFixedThreadPool(20);

		for (int i = 0; i < 10; i++) {

			Future<String> f1 = service.submit(() -> {
				return readValue();
			});

			String str = f1.get();// blocking

			Future<Integer> f2 = service.submit(() -> {
				return readLength(str);
			});

			System.out.println(f2.get());// blocking
		}

		service.shutdown();
	}

	public void perform222_Non_blocking() throws InterruptedException, ExecutionException {
		
		//  parallel execution, 10 tasks will be started & executed at a time by threads.
		ExecutorService executor = Executors.newFixedThreadPool(30);

		List<CompletableFuture<Integer>> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {

			CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
				return readValue();
			}, executor).thenApplyAsync(newvalue -> readLength(newvalue), executor);

			list.add(cf);
		}

		for (CompletableFuture<Integer> cf : list) {
			System.out.println("Value- " + cf.get());
		}
		executor.shutdown();
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Real_understanding_Executor_CompletableFuture r = new Real_understanding_Executor_CompletableFuture();
		r.perform55_blocking();
	}

	public String readValue() {
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": reading string done");
		return "venkat";
	}

	public int readLength(String value) {
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": total length");
		return value.length();
	}
}
