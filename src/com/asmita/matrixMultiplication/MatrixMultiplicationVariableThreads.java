package com.asmita.matrixMultiplication;

import com.asmita.matrixMultiplication.concurrency.ConcurrencyManager;

/*
 * MatrixMultiplicationVariableThreads -
 * This implementation of matrix multiplication has option of specifying how many
 * threads should be created to compute matrix multiplication. The way this implementation
 * works is by multiplying one row to one column in one iteration. Because of this, having
 * more threads than the rows in result array would not yield any gains. And hence internally
 * the number of threads is capped at number of rows in result array. If there is use of 
 * creating more threads than the number of rows, then other implementation could be written where
 * each worker thread can work on each cell of the result matrix. In that case the max number of
 * threads could be row x col of result matrix.
 * 
 * @author  Asmita Dutta
 */
public class MatrixMultiplicationVariableThreads implements MatrixMultiplication {
	private boolean resultComputed;
	private final ConcurrencyManager concurrencyManager;
	private final int [][] mat1;
	private final int [][] mat2;
	private final int [][] resultMat;
	private final int numThreads;
	private long executionTime;
	public MatrixMultiplicationVariableThreads(int[][] mat1, int[][] mat2, int numThreads) {
		if (mat1[0].length != mat2.length) {
			throw new IllegalArgumentException("Column size of matrix 1 is not matching Row size of matrix 2");
		}
		this.mat1 = mat1;
		this.mat2 = mat2;
		this.resultMat = new int [mat1.length][mat2[0].length];
		this.numThreads = numThreads;
		this.resultComputed = false;
		this.concurrencyManager = new ConcurrencyManager();
	}
	@Override
	public int[][] multiply() {
		long startTime = System.currentTimeMillis();
		if (this.resultComputed) {
			return resultMat;
		}
		concurrencyManager.multiply(mat1, mat2, resultMat, numThreads);
		resultComputed = true;
		setExecutionTime(System.currentTimeMillis() - startTime);
		return resultMat;
	}
	public long getExecutionTime() {
		return executionTime;
	}
	private void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

}
