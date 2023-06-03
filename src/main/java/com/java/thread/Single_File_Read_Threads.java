package com.java.thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Single_File_Read_Threads implements Runnable {

	private FileChannel _channel;
	private long _startLocation;
	private int _size;
	int _sequence_number;

	public Single_File_Read_Threads(long loc, int size, FileChannel chnl, int sequence) {
		_startLocation = loc;
		_size = size;
		_channel = chnl;
		_sequence_number = sequence;
	}

	@Override
	public void run() {
		
		
		// allocate memory
		ByteBuffer buff = ByteBuffer.allocate(_size);

		// Read file chunk to RAM
		try {
			_channel.read(buff, _startLocation);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// chunk to String
		String string_chunk = new String(buff.array(), Charset.forName("UTF-8"));
		System.out.println(Thread.currentThread().getName());
		System.out.println(string_chunk);
		
		
		

	}

	public static int toIntExact(long l) {
		int i = (int) l;
		if ((long) i != l) {
			throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
		}
		return i;
	}

	
	public static void main(String[] args) throws Exception {

	
		

		FileInputStream fileInputStream = new FileInputStream("D:\\Spring Modules\\Java\\Files\\venkat.txt");
		FileChannel channel = fileInputStream.getChannel();
		long remaining_size = channel.size(); // get the total number of bytes in the file
		long chunk_size = remaining_size / Integer.parseInt("3"); // file_size/threads

		//System.out.println("remaining_size:"+remaining_size);
		// thread pool
		ExecutorService executor = Executors.newFixedThreadPool(3);

		long start_loc = 0;// file pointer
		int i = 0; // loop counter
		while (remaining_size >= chunk_size) {
			// launches a new thread
			executor.execute(new Single_File_Read_Threads(start_loc, toIntExact(chunk_size), channel, i));
			remaining_size = remaining_size - chunk_size;
			start_loc = start_loc + chunk_size;
			i++;
		}

		// load the last remaining piece
		executor.execute(new Single_File_Read_Threads(start_loc, toIntExact(remaining_size), channel, i));

		// Tear Down
		executor.shutdown();
		//System.out.println("Finished all threads");
	   // fileInputStream.close();

	}

}
