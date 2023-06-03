package com.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchWord2 implements Runnable {

	private int startIndex;
	private int endIndex;
	private String word;
	private int localCounter = 0;

	private List<String> words;

	public SearchWord2(int start, int end, String token, List<String> words) {
		this.startIndex = start;
		this.endIndex = end;
		this.word = token;
		this.words = words;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		System.out.println("startIndex=" + startIndex);
		try {
			Thread.sleep(10000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = startIndex; i <= endIndex; i++) {

			if (words.get(i).equals(word)) {
				localCounter++;
			}
		}
		System.out.println("endindex=" + endIndex);

	}

	public int getCounter() {
		return localCounter;
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

		SearchWord2 task1 = new SearchWord2(0, 100000, "john", words);
		SearchWord2 task2 = new SearchWord2(100001, 200000, "john", words);
		SearchWord2 task3 = new SearchWord2(200001, 300000, "john", words);

		Thread t1 = new Thread(task1);
		Thread t2 = new Thread(task2);
		Thread t3 = new Thread(task3);

		// start threads
		t1.start();
		t2.start();
		t3.start();

		// wait for threads to finish
		t1.join();
		t2.join();
		t3.join();

		// collect results
		int counter = 0;
		counter += task1.getCounter();
		counter += task2.getCounter();
		counter += task3.getCounter();

		System.out.println("counter :=" + counter);

	}
}
