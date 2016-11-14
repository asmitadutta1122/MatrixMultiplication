package com.asmita.matrixMultiplication;

import com.asmita.matrixMultiplication.concurrency.ConcurrencyManager;

public class MatrixMultiplicationVariableThreads implements MatrixMultiplication {
	private final int [][] resultMat;
	private boolean resultComputed;
	private final ConcurrencyManager concurrencyManager;
	public MatrixMultiplicationVariableThreads(int[][] mat1, int[][] mat2, int numThreads) {
		if (mat1[0].length != mat2.length) {
			throw new IllegalArgumentException("Column size of matrix 1 is not matching Row size of matrix 2");
		}
		resultMat = new int [mat1.length][mat2[0].length];
		resultComputed = false;
		this.concurrencyManager = new ConcurrencyManager(mat1, mat2, resultMat, numThreads);
	}
	@Override
	public int[][] multiply() {
		if (this.resultComputed) {
			return resultMat;
		}
		concurrencyManager.startMultiplication();
		resultComputed = true;
		return resultMat;
	}

}
