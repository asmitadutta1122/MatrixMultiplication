package com.asmita.matrixMultiplication.concurrency;

import java.util.ArrayList;

public class ConcurrencyManager {
	private final MultiplicationStateManager stateManager;
	private final int [][] mat1;
	private final int [][] mat2;
	private final int [][] resultMat;
	private final ArrayList<MultiplicationWorkerThread> threadList;
	public ConcurrencyManager(int[][] mat1, int[][] mat2, int [][] resultMat, int numThreads) {
		
		this.mat1 = mat1;
		this.mat2 = mat2;
		this.resultMat = resultMat;
		this.stateManager = new MultiplicationStateManager(mat1.length);
		this.threadList = new ArrayList<>();
		for (int i = 0; i < numThreads; i++) {
			threadList.add(new MultiplicationWorkerThread(mat1, mat2, resultMat, stateManager));
		}
	}
	
	public void startMultiplication() {
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
