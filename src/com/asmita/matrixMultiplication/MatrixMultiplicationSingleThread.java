package com.asmita.matrixMultiplication;

public class MatrixMultiplicationSingleThread implements MatrixMultiplication {
	private final int [][] mat1;
	private final int [][] mat2;
	private final int [][] resultMat;
	private boolean resultComputed;
	private long executionTime;
	public MatrixMultiplicationSingleThread(int[][] mat1, int[][] mat2) {
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
		for (int i = 0; i < mat1.length; i++) { 
            for (int j = 0; j < mat2[0].length; j++) { 
                for (int k = 0; k < mat1[0].length; k++) { 
                    resultMat[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
		resultComputed = true;
		setExecutionTime(System.currentTimeMillis() - startTime);
		return resultMat;
	}

	@Override
	public long getExecutionTime() {
		return this.executionTime;
	}

	private void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

}
