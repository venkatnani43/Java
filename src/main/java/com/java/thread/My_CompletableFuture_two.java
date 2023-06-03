package com.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.java.test.Test;

public class My_CompletableFuture_two {

	public void perform1_non_blocking() {

		Thread t1 = new Thread(() -> {
			readFile();
		});

		Thread t2 = new Thread(() -> {
			readFile();
		});

		t1.start();
		t2.start();
	}

	public void perform2_non_blocking() {

		ExecutorService service = Executors.newFixedThreadPool(2);

		service.execute(() -> {
			readFile();
		});

		service.execute(() -> {
			readFile();
		});

		service.shutdown();
	}

	public void perform3_non_blocking() throws InterruptedException, ExecutionException {

		List<Future<Integer>> list = new ArrayList<>();
		ExecutorService service = Executors.newFixedThreadPool(2);

		Future<Integer> f1 = service.submit(() -> {
			return readFile2();
		});
		list.add(f1);

		Future<Integer> f2 = service.submit(() -> {
			return readFile2();
		});
		list.add(f2);

		for (Future<Integer> f : list) {
			System.out.println(f.get());
		}

		service.shutdown();
	}

	public void perform4_non_blocking() throws Exception {

		List<Callable<Integer>> list = new ArrayList<>();
		ExecutorService service = Executors.newFixedThreadPool(2);

		list.add(() -> {
			return readFile2();
		});

		list.add(() -> {
			return readFile2();
		});

		List<Future<Integer>> futures = service.invokeAll(list);

		for (Future<Integer> f : futures) {
			System.out.println(f.get());
		}

		service.shutdown();
	}

	public void perform5_blocking() throws InterruptedException, ExecutionException {

		ExecutorService service = Executors.newFixedThreadPool(2);

		Future<Integer> f1 = service.submit(() -> {
			return readFile2();
		});

		System.out.println(f1.get());// blocking

		Future<Integer> f2 = service.submit(() -> {
			return readFile2();
		});

		System.out.println(f2.get());// blocking

		service.shutdown();
	}

	public void perform55_blocking() throws InterruptedException, ExecutionException {

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

	public void perform7_blocking() throws InterruptedException, ExecutionException {

		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			return readFile2();
		});
		System.out.println(cf.get()); // blocking

		CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
			return readFile2();
		});

		System.out.println(cf2.get()); // blocking

	}

	public void perform6_non_blocking() throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(10);

		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			return readFile2();
		}, executor);

		CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
			return readFile2();
		}, executor);

		executor.shutdown();
	}

	public void perform_non_blocking_two() throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(10);

		for (int i = 0; i < 10; i++) {

			CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
				return readFile2();
			}, executor);

		}

		executor.shutdown();
	}

	public void perform7_non_blocking() throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(10);

		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			return readFile2();
		}, executor);

		CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
			return readFile2();
		}, executor);

		CompletableFuture.allOf(cf, cf2).join();

		System.out.println("main thread executing after all threads are completed their execution");

		executor.shutdown();
	}

	public void perform8_non_blocking() throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(10);

		CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
			readFile2();
		}, executor);

		CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> {
			readFile2();
		}, executor);

	}

	public void perform() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(10);

		CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
			return readValue();
		}, executor).thenAcceptAsync(value -> {

			System.out.println(value.toUpperCase());
		});
		executor.shutdown();
	}

	public void perform6() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(10);

		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
			return readValue();
		}, executor).completeAsync(() -> {

			return "venkat";
		});
		System.out.println(cf.get());
		executor.shutdown();
	}

	public void perform2() throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(2);

		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			return readValue();
		}).thenApply(value -> readValue(value))
		  .thenApply(newvalue -> readLength(newvalue));

		System.out.println("Value- " + cf.get());
		executor.shutdown();
	}

	public void perform22_blocking() throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(30);

		for (int i = 0; i < 10; i++) {

			CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
				return readValue();
			}, executor).thenApplyAsync(value -> readValue(value), executor)
					    .thenApplyAsync(newvalue -> readLength(newvalue), executor);

			System.out.println("Value- " + cf.get());// blocking
		}
		executor.shutdown();
	}

	public void perform222_Non_blocking() throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(30);

		List<CompletableFuture<Integer>> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {

			CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
				return readValue();
			}, executor).thenApplyAsync(value -> readValue(value), executor)
					   .thenApplyAsync(newvalue -> readLength(newvalue), executor);
			list.add(cf);
		}

		for (CompletableFuture<Integer> cf : list) {
			System.out.println("Value- " + cf.get());// blocking
		}
		executor.shutdown();
	}

	public void perform3() throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(2);

		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			return readValue();
		}, executor).thenApplyAsync(value -> readValue(value), executor)
				    .thenApplyAsync(newvalue -> readLength(newvalue), executor);

		System.out.println("Value- " + cf.get());
		executor.shutdown();
	}

	

	public void perform5() throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(2);

		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			return readValue();
		}, executor).thenComposeAsync(value -> CompletableFuture.completedFuture(readValue(value)), executor)
				.thenComposeAsync(newvalue -> CompletableFuture.completedFuture(readLength(newvalue)), executor);

		System.out.println("Value- " + cf.get());
		executor.shutdown();
	}

	public void combine() throws InterruptedException, ExecutionException {
		// both future 1 & future 2 run independently and result are combined
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			return readValue();
		});

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			return readValue("john");
		});

		CompletableFuture<String> result = future1.thenCombine(future2, (str1, str2) -> str1 + " " + str2);
		System.out.println("Value- " + result.get());
	}

	public static void main(String[] args) throws Exception {

		My_CompletableFuture_two t = new My_CompletableFuture_two();
		t.perform55_blocking();

	}

	public void perform7() {

		String str = null;
		CompletableFuture<String> value = CompletableFuture.supplyAsync(() -> {
			if (str == null)
				throw new IllegalArgumentException("Invalid String value passed " + str);
			return str;
		}).handle((s, exp) -> {
			if (exp != null) {
				System.out.println("Exception thrown with message - " + exp.getMessage());
				s = "";
			}
			return s;
		});
	}

	public void perform8() {

		String str = null;
		CompletableFuture<String> value = CompletableFuture.supplyAsync(() -> {
			if (str == null)
				throw new IllegalArgumentException("Invalid String value passed " + str);
			return str;
		}).whenComplete((s, exp) -> {
			System.out.println("in whenComplete method");
			if (exp != null) {
				System.out.println("Exception thrown with message - " + exp.getMessage());
				// s = "";
			}
		});
	}

	public void perform9() {

		String str = null;
		CompletableFuture<String> value = CompletableFuture.supplyAsync(() -> {
			if (str == null) {
				throw new IllegalArgumentException("Invalid String value passed " + str);
			}
			return str;
		}).exceptionally(exp -> {
			System.out.println("Exception thrown with message - " + exp.getMessage());
			return "";
		});
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

	public String readValue(String value) {
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ":  string added ");
		return value + "hello";
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

	public void readFile() {
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": reading file done");
	}

	public int readFile2() {
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": reading file done");
		return 1;
	}

	public CompletableFuture<Integer> readFile3() {
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": reading file done");
		return CompletableFuture.completedFuture(1);
	}

}
