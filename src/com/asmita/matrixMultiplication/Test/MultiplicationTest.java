package com.asmita.matrixMultiplication.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.asmita.matrixMultiplication.MatrixMultiplicationVariableThreads;
import com.asmita.matrixMultiplication.fileHandling.FileHandler;

/*
 * MultiplicationTest - 
 * Unit tests.
 * 
 * @author  Asmita Dutta
 */
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

	@Test
	public void testFileHandling() {
		String resourceDir = System.getProperty("user.dir") + "/resources/";
		String file1 = resourceDir + "1.3.in";
		String file2 = resourceDir + "2.3.in";
		String file3 = resourceDir + "1.3.out";
		int [][]mat1 = FileHandler.readInputMatrix(file1);
		int [][]mat2 = FileHandler.readInputMatrix(file2);
		MatrixMultiplicationVariableThreads mulVarThread = new MatrixMultiplicationVariableThreads(mat1, mat2, 3);
		int [][] result = mulVarThread.multiply();
		FileHandler.writeOutputMatrix(result, file3);
		int [][]result2 = FileHandler.readInputMatrix(file3);
		assertArrayEquals(result, result2);
	}
}
