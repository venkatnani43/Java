package com.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class My_ExecuteService_Blocking_2 {

	public static Integer add(int a, int b) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

		}
		return a + b;

	}

	public static Integer multiply(int result) {
		return result * 2;
	}

	public void perform() throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(10);

		List<Future<Integer>> finalFutureList = new ArrayList<>();
		List<Integer> finalResultList = new ArrayList<>();

		for (int i = 1; i <= 60; i++) {
			int k = i;

			Future<Integer> future_1 = executor.submit(() -> {

				return add(k, 2);

			});

			Integer sum_result = future_1.get(); // blocking

			Future<Integer> future_2 = executor.submit(() -> {

				return multiply(sum_result);
			});

			finalFutureList.add(future_2);
		}

		for (Future<Integer> future : finalFutureList) {
			finalResultList.add(future.get());
		}
		System.out.println(finalResultList);

		executor.shutdown();
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		My_ExecuteService_Blocking_2 m = new My_ExecuteService_Blocking_2();
		m.perform();
	}

}
