package com.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SearchWord implements Callable<Integer> {

	private int startIndex;
	private int endIndex;
	private String word;
	private int counter = 0;

	private List<String> words;

	public SearchWord(int start, int end, String token, List<String> words) {
		this.startIndex = start;
		this.endIndex = end;
		this.word = token;
		this.words = words;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println(Thread.currentThread().getName());
		System.out.println("startIndex="+startIndex);
		Thread.sleep(10000l);

		for (int i = startIndex; i <= endIndex; i++) {

			if (words.get(i).equals(word)) {
				counter++;
			}
		}
		System.out.println("endindex="+endIndex);
		return counter;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		List<String> words = new ArrayList<>();
		for (int i = 0; i <= 300000; i++) {

			if (i == 70000 || i == 170000 || i == 270000) {
				words.add("john");
			} else {
				words.add("connor" + i);
			}
		}

		List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
		tasks.add(new SearchWord(0, 100000, "john", words));
		tasks.add(new SearchWord(100001, 200000, "john", words));
		tasks.add(new SearchWord(200001, 300000, "john", words));

		// create thread pool and start tasks
		ExecutorService exec = Executors.newFixedThreadPool(3);
		List<Future<Integer>> results = exec.invokeAll(tasks);

		// wait for tasks to finish and collect results
		int counter = 0;
		for (Future<Integer> f : results) {
			counter = counter + f.get();
		}

		System.out.println("counter :=" + counter);

	}
}
