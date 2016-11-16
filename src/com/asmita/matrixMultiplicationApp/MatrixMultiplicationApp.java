package com.asmita.matrixMultiplicationApp;

import com.asmita.matrixMultiplication.MatrixMultiplicationVariableThreads;
import com.asmita.matrixMultiplication.fileHandling.FileHandler;

public class MatrixMultiplicationApp {

	public static void main(String[] args) {
		String input1 = null;
		String input2 = null;
		String output = null;
		int numThreads = 0;
		if (args.length != 8) {
			throw new IllegalArgumentException("Insufficient options, please provide --input1 <file1>"
					+ " --input2 <file2> --output <outfile> --threads <number of threads>");
		}
		for (int i = 0; i < args.length; i++) {
			if(args[i].trim().equals("--input1")) {
				input1 = args[++i];
			} else if(args[i].trim().equals("--input2")) {
				input2 = args[++i];
			} else if(args[i].trim().equals("--output")) {
				output = args[++i];
			} else if(args[i].trim().equals("--threads")) {
				numThreads = Integer.parseInt(args[++i]);
			} else {
				throw new IllegalArgumentException("Not a valid option: "+args[i] + " only --input1, --input2,"
						+ " --threads and --output are allowed");
			}
		}
	        	
		int [][] mat1 = FileHandler.readInputMatrix(input1);;
		int [][] mat2 = FileHandler.readInputMatrix(input2);;
		
		MatrixMultiplicationVariableThreads mul = new MatrixMultiplicationVariableThreads(mat1, mat2, numThreads);
		int [][] result = mul.multiply();
		FileHandler.writeOutputMatrix(result, output);
		System.out.println("Execution time " + mul.getExecutionTime() + " milliseconds.");
	}
}
