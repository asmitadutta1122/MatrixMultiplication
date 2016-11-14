package com.asmita.matrixMultiplicationApp;

import com.asmita.matrixMultiplication.MatrixMultiplicationFixedThreads;
import com.asmita.matrixMultiplication.MatrixMultiplicationSingleThread;
import com.asmita.matrixMultiplication.MatrixMultiplicationVariableThreads;

public class MatrixMultiplicationApp {

	public static void main(String[] args) {
		int [][] mat1 = new int [3][4];
		int [][] mat2 = new int [4][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				mat1[i][j] = 1000;
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				mat2[i][j] = 1;
			}
		}
		//MatrixMultiplicationFixedThreads mul = new MatrixMultiplicationFixedThreads(mat1, mat2);
		MatrixMultiplicationVariableThreads mul = new MatrixMultiplicationVariableThreads(mat1, mat2, 2);
		int [][] result = mul.multiply();
		System.out.println(toString(mat1));
		System.out.println(toString(mat2));
		System.out.println(toString(result));
	}
	
	public static String toString(int [][] m) {
        String result = "";
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[i].length; j++) {
                result += String.format("%10d", m[i][j]);
            }
            result += "\n";
        }
        return result;
    }
	
}
