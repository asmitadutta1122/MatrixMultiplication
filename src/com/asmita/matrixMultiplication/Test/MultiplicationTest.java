package com.asmita.matrixMultiplication.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.asmita.matrixMultiplication.MatrixMultiplicationVariableThreads;

public class MultiplicationTest {

	@Test
	public void testWithMultipleWorkerThreads() {
		int mat1 [][] = { {1000, 1000, 1000, 1000}, {1000, 1000, 1000, 1000}, {1000, 1000, 1000, 1000} };
		int mat2 [][] = { {1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1} };
		int expectedResult [][] = { {4000, 4000, 4000}, {4000, 4000, 4000}, {4000, 4000, 4000} };
		MatrixMultiplicationVariableThreads mulVarThread = new MatrixMultiplicationVariableThreads(mat1, mat2, 2);
		int [][] result = mulVarThread.multiply();
		assertArrayEquals(expectedResult, result);
		mulVarThread = new MatrixMultiplicationVariableThreads(mat1, mat2, 3);
		result = mulVarThread.multiply();
		assertArrayEquals(expectedResult, result);
		mulVarThread = new MatrixMultiplicationVariableThreads(mat1, mat2, 5);
		result = mulVarThread.multiply();
		assertArrayEquals(expectedResult, result);
	}

}
