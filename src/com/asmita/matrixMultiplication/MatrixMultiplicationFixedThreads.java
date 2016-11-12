package com.asmita.matrixMultiplication;

import com.asmita.matrixMultiplicationThreads.MultiplicationThread;

public class MatrixMultiplicationFixedThreads implements MatrixMultiplication {
	private final int [][] mat1;
	private final int [][] mat2;
	private final int [][] resultMat;
	private boolean resultComputed;
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
		if (resultComputed) {
			return resultMat;
		}
		MultiplicationThread t1 = new MultiplicationThread(mat1, mat2, resultMat, 0);
		MultiplicationThread t2 = new MultiplicationThread(mat1, mat2, resultMat, 1);
		MultiplicationThread t3 = new MultiplicationThread(mat1, mat2, resultMat, 2);
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resultComputed = true;
		return resultMat;
	}

}
