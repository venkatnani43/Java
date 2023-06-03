package com.java.thread.Synchronization;

public class Object_Level_Lock implements Runnable {

	private static int count = 0;

	@Override
	public void run() {

		inc();
	}
	
	

/* Always try to use synchronized block instead of method level synchronization */
	public void inc() {

		synchronized (this) {
			for (int i = 0; i < 100000; i++) {
				count++;
			}
		}
	}
	
	public synchronized void inc2() {

		for (int i = 0; i < 100000; i++) {
			count++;
		}
	}

	

	public static void main(String[] args) throws InterruptedException {

		Object_Level_Lock obj = new Object_Level_Lock();

		Thread t1 = new Thread(obj);
		Thread t2 = new Thread(obj);
		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println(count);

	}

}
