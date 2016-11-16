package com.asmita.matrixMultiplication;

import java.util.ArrayList;

import com.asmita.matrixMultiplication.concurrency.MultiplicationThread;

/*
 * MatrixMultiplicationFixedThreads -
 * This implementation of matrix multiplication has fixed number of threads. The number
 * of threads is equal to the number of rows in the result array.
 * 
 * @author  Asmita Dutta
 */
public class MatrixMultiplicationFixedThreads implements MatrixMultiplication {
	private final int [][] mat1;
	private final int [][] mat2;
	private final int [][] resultMat;
	private boolean resultComputed;
	private long executionTime;
	public MatrixMultiplicationFixedThreads(int[][] mat1, int[][] mat2) {
		if (mat1[0].length != mat2.length) {
			throw new IllegalArgumentException("Column size of matrix 1 is not matching Row size of matrix 2");
		}
		this.mat1 = mat1;
		this.mat2 = mat2;
		resultMat = new int [mat1.length][mat2[0].length];
		resultComputed = false;
	}
	
	@Override
	public int[][] multiply() {
		long startTime = System.currentTimeMillis();
		if (resultComputed) {
			return resultMat;
		}
		ArrayList<MultiplicationThread> threadList = new ArrayList<>();
		for (int i = 0; i < mat1.length; i++) {
			threadList.add(new MultiplicationThread(mat1, mat2, resultMat, i));
		}
		
		for (MultiplicationThread t : threadList) {
			t.start();
		}
		try {
			for (MultiplicationThread t : threadList) {
				t.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		resultComputed = true;
		setExecutionTime(System.currentTimeMillis() - startTime);
		return resultMat;
	}

	@Override
	public long getExecutionTime() {
		return this.executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

}
