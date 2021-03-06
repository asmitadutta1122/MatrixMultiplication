package com.asmita.matrixMultiplication.concurrency;

import java.util.ArrayList;

/*
 * ConcurrencyManager -
 * This class is responsible for spawning the worker threads, managing concurrency states etc.
 * @author  Asmita Dutta
 */
public class ConcurrencyManager {
	
	public void multiply(int[][] mat1, int[][] mat2, int [][] resultMat, int numThreads) {
		MultiplicationStateManager stateManager = new MultiplicationStateManager(mat1.length);
		ArrayList<MultiplicationWorkerThread> threadList = new ArrayList<>();
		
		if (numThreads > mat1[0].length) {
			numThreads = mat1[0].length;
		}
		for (int i = 0; i < numThreads; i++) {
			threadList.add(new MultiplicationWorkerThread(mat1, mat2, resultMat, stateManager));
		}
		for (MultiplicationWorkerThread t : threadList) {
			t.start();
		}
		try {
			for (MultiplicationWorkerThread t : threadList) {
				t.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * This class can be used by worker threads to get concurrency context. By calling getNextRow
	 * a worker thread knows which row it has to work on.
	 */
	public class MultiplicationStateManager {
		private int nextRow;
		private int maxRows;
		public static final int ALL_DONE = -1;
		public MultiplicationStateManager(int maxRows) {
			super();
			this.nextRow = 0;
			this.maxRows = maxRows;
		}
		public synchronized int getNextRow() {
			if (nextRow == maxRows) {
				return ALL_DONE;
			}
			return nextRow++;
		}
	}
}
