package com.java.thread;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadFileByChunks {
    public static void main(String[] args) throws IOException {
        int chunks = Runtime.getRuntime().availableProcessors();
        
        
        long[] offsets = new long[chunks];
        File file = new File("D:\\Spring Modules\\Java\\Files\\venkat.txt");

        // determine line boundaries for number of chunks
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        for (int i = 1; i < chunks; i++) {
            raf.seek(i * file.length() / chunks);

            while (true) {
                int read = raf.read();
                if (read == '\n' || read == -1) {
                    break;
                }
            }

            offsets[i] = raf.getFilePointer();
        }
        
		/*
		 * for(long i:offsets) { System.out.println(i); }
		 */
        raf.close();

        // process each chunk using a thread for each one
       // System.out.println("chucnks+"+chunks);
        ExecutorService service = Executors.newFixedThreadPool(chunks);
        for (int i = 0; i < chunks; i++) {
            long start = offsets[i];
            long end = i < chunks - 1 ? offsets[i + 1] : file.length();
            service.execute(new FileProcessor(file, start, end));
        }
       // service.shutdown();
    }

    static class FileProcessor implements Runnable {
        private final File file;
        private final long start;
        private final long end;

        public FileProcessor(File file, long start, long end) {
            this.file = file;
            this.start = start;
            this.end = end;
        }

        public void run() {
        	//System.out.println(Thread.currentThread().getName());
    		long startTime = System.currentTimeMillis(); // Get the start Time
    		long endTime = 0;
        	
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                raf.seek(start);

                while (raf.getFilePointer() < end) {
                    String line = raf.readLine();
                    if (line == null) {
                        continue;
                    }

                    // do what you need per line here
                   System.out.println(line);
                }

                raf.close();
                
                endTime = System.currentTimeMillis(); // Get the end Time

				//System.out.println(" *******Differencce in Seconds: " + (endTime - startTime) / 1000); // Print the difference in
				
            } catch (IOException e) {
                // deal with exception
            }
        }
    }
}