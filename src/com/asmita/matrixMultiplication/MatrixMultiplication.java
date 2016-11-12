package com.asmita.matrixMultiplication;

public class MatrixMultiplication {
	private final int [][] mat1;
	private final int [][] mat2;
	private final int [][] resultMat;
	private boolean resultComputed;
	public MatrixMultiplication(int[][] mat1, int[][] mat2) {
		if (mat1[0].length != mat2.length) {
			throw new IllegalArgumentException("Column size of matrix 1 is not matching Row size of matrix 2");
		}
		this.mat1 = mat1;
		this.mat2 = mat2;
		resultMat = new int [mat1.length][mat2[0].length];
		resultComputed = false;
	}
	
	public int[][] multiply() {
		if (resultComputed) {
			return resultMat;
		}
		for (int i = 0; i < mat1.length; i++) { // aRow
            for (int j = 0; j < mat2[0].length; j++) { // bColumn
                for (int k = 0; k < mat1[0].length; k++) { // aColumn
                    resultMat[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
		resultComputed = true;
		return resultMat;
	}

}
