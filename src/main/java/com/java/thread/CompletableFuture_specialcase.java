package com.java.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



import lombok.extern.slf4j.Slf4j;
@Slf4j
public class CompletableFuture_specialcase {
	
	public CompletableFuture<Employee> fetchEmployee_two(ExecutorService executor) {

		CompletableFuture<Employee> cf = CompletableFuture.supplyAsync(() -> {
			System.out.println("fetchEmployee" + Thread.currentThread().getName());
			Employee e = new Employee(1, "venkat", 400000);
			try {
				Thread.sleep(10000L);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("fetchEmployee Done");
			return e;
		}, executor);
		return cf;

	}
	public CompletableFuture<EmployeeAddress> fetchEmployeeAddress_two(ExecutorService executor)
			throws InterruptedException {

		CompletableFuture<EmployeeAddress> cf = CompletableFuture.supplyAsync(() -> {
			System.out.println("fetchEmployeeAddress" + Thread.currentThread().getName());
			EmployeeAddress e = new EmployeeAddress("india", "50001");
			try {
				Thread.sleep(10000L);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("fetchEmployeeAddress Done");
			return e;
		}, executor);
		return cf;

	}

	public CompletableFuture<EmployeeAccountDetails> fetchEmployeeAccountDetails_two(ExecutorService executor)
			throws InterruptedException {

		CompletableFuture<EmployeeAccountDetails> cf = CompletableFuture.supplyAsync(() -> {
			System.out.println("fetchEmployeeAccountDetails" + Thread.currentThread().getName());
			EmployeeAccountDetails e = new EmployeeAccountDetails(101, "HDFc");
			try {
				Thread.sleep(10000L);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("fetchEmployeeAccountDetails_two Done");
			return e;
		}, executor);
		return cf;

	}
	
	public void non_blocking() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(3);

		CompletableFuture<Employee> emp = fetchEmployee_two(executor);
		CompletableFuture<EmployeeAddress> add =fetchEmployeeAddress_two(executor);
		CompletableFuture<EmployeeAccountDetails> account = fetchEmployeeAccountDetails_two(executor);
		executor.shutdown();
	}
	
	public void non_blocking2() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		ExecutorService executor2 = Executors.newFixedThreadPool(3);
		ExecutorService executor3 = Executors.newFixedThreadPool(3);

		CompletableFuture<Employee> emp = fetchEmployee_two(executor);
		CompletableFuture<EmployeeAddress> add =fetchEmployeeAddress_two(executor2);
		CompletableFuture<EmployeeAccountDetails> account = fetchEmployeeAccountDetails_two(executor3);

		executor.shutdown();
		executor2.shutdown();
		executor3.shutdown();
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		CompletableFuture_specialcase c=new CompletableFuture_specialcase();
		c.non_blocking2();
	}

}
