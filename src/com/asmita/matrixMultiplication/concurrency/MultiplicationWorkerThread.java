package com.asmita.matrixMultiplication.concurrency;

import com.asmita.matrixMultiplication.concurrency.ConcurrencyManager.MultiplicationStateManager;

/*
 * MultiplicationWorkerThread -
 * A little advanced version of MultiplicationThread where worker threads can use concurrency context
 * to understand which row has to be processed next.
 * @author  Asmita Dutta
 */
public class MultiplicationWorkerThread extends Thread {
	private final int [][] mat1;
	private final int [][] mat2;
	private final int [][] resultMat;
	private final MultiplicationStateManager stateManager;
	public MultiplicationWorkerThread(int[][] mat1, int[][] mat2, int [][] resultMat, MultiplicationStateManager stateManager) {
		this.mat1 = mat1;
		this.mat2 = mat2;
		this.resultMat = resultMat;
		this.stateManager = stateManager;
	}
	@Override
	public void run() {
		while (true) {
			int row = this.stateManager.getNextRow();
			if (row == MultiplicationStateManager.ALL_DONE) {
				break;
			}
			for (int j = 0; j < mat2[0].length; j++) {
	            for (int k = 0; k < mat1[0].length; k++) {
	            	resultMat[row][j] += mat1[row][k] * mat2[k][j];
	            }
	        }
		}
		
	}

}
