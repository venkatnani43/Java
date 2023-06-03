package com.java.thread.Synchronization;

public class Class_Level_Lock implements Runnable {

	private static int count = 0;

	@Override
	public void run() {

		inc();
	}

	
	/* Always try to use synchronized block of class level instead of method level static synchronization */
	public void inc() {

		synchronized (Class_Level_Lock.class) {
			for (int i = 0; i < 100000; i++) {
				count++;
			}
		}
	}
	
	public static synchronized void inc2() {

		for (int i = 0; i < 100000; i++) {
			count++;
		}

	}

	public static void main(String[] args) throws InterruptedException {

		Class_Level_Lock obj = new Class_Level_Lock();
		Class_Level_Lock obj2 = new Class_Level_Lock();

		Thread t1 = new Thread(obj);
		Thread t2 = new Thread(obj2);
		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println(count);

	}

}
