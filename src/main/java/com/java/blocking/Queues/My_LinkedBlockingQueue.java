package com.java.blocking.Queues;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class My_LinkedBlockingQueue {

	public void producer(BlockingQueue<Integer> queue) throws InterruptedException {

		for (int i = 1; i <= 100; i++) {

			queue.put(i);
			System.out.println("producer =" + i);
			Thread.sleep(1000l);
		}
	}

	public void consumer_1(BlockingQueue<Integer> queue) throws InterruptedException {

		while (true) {

			System.out.println(Thread.currentThread().getName() + "= " + queue.take());
			Thread.sleep(500l);
		}

	}

	public static void main(String[] args) {
		My_LinkedBlockingQueue m = new My_LinkedBlockingQueue();
		My_LinkedBlockingQueue m2 = new My_LinkedBlockingQueue();
		
		ExecutorService exe = Executors.newFixedThreadPool(10);
		BlockingQueue<Integer> queue = new ArrayBlockingQueue(1);
		exe.execute(() -> {
			try {
				m.producer(queue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		for (int i = 0; i < 10; i++) {
			exe.execute(() -> {
				try {
					m2.consumer_1(queue);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			
		}
		
		
		exe.shutdown();
	}

}
