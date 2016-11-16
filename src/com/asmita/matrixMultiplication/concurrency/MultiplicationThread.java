package com.asmita.matrixMultiplication.concurrency;

/*
 * MultiplicationThread -
 * A simple thread implementation to do matrix multiplication for a row.
 * @author  Asmita Dutta
 */
public class MultiplicationThread  extends Thread {
	private final int [][] mat1;
	private final int [][] mat2;
	private final int [][] resultMat;
	private final int row;
	public MultiplicationThread(int[][] mat1, int[][] mat2, int[][] resultMat, int row) {
		super();
		//Add preconditions to check that columns of mat1 = rows of mat 2
		this.mat1 = mat1;
		this.mat2 = mat2;
		this.resultMat = resultMat;
		this.row = row;
	}
	
	@Override
	public void run() {
		for (int j = 0; j < mat2[0].length; j++) {
            for (int k = 0; k < mat1[0].length; k++) {
            	resultMat[row][j] += mat1[row][k] * mat2[k][j];
            }
        } 
	}
}
