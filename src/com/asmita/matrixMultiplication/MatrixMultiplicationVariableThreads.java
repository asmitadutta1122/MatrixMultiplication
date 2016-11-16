package com.asmita.matrixMultiplication;

import com.asmita.matrixMultiplication.concurrency.ConcurrencyManager;

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
