package com.asmita.matrixMultiplication.fileHandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * FileHandler --
 * 
 * This class is used for reading and writing input/output files for matrix multiplication.
 * The format of the files is as follows where first row contains number of rows and columns
 * and second row onwards, the matrix.
 * 
 * 5 5
 * 4 5 6 7 8
 * 1 2 3 4 5
 * 9 8 7 6 5
 * 4 3 3 4 3
 * 1 1 1 1 1
 * 
 * @author  Asmita Dutta
 */
public class FileHandler {
	public static int [][] readInputMatrix(String filename) {
		String thisLine;
		int [][] array;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			thisLine = br.readLine();
			if (thisLine == null || thisLine.trim().equals("")) {
				throw new IllegalArgumentException("Empty file " + filename);
			}
			String[] lineArray = thisLine.split(" ");
			if (lineArray.length != 2) {
				throw new IllegalArgumentException("Invalid format of input file " + filename);
			}
			int row = Integer.parseInt(lineArray[0]);
			int col = Integer.parseInt(lineArray[1]);
			array = new int[row][col];
			for (int i = 0; i < row; i++) {
				if ((thisLine = br.readLine()) != null) {
					if (thisLine.trim().equals("")) {
						throw new IllegalArgumentException("Invalid format of input file, blank line encountered " + filename);
					} else {
						lineArray = thisLine.trim().split("\\s+");
						if (lineArray.length != col) {
							throw new IllegalArgumentException("Invalid format of input file, need more numbers in line " + filename);
						}
						int j = 0;
						for (String number : lineArray) {
							array[i][j++] = Integer.parseInt(number.trim());
						}
					}
				} else {
					throw new IllegalArgumentException("Invalid format of input file, need more number of lines " + filename);
				}
			}
		} catch (IOException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Error reading file " + filename);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return array;
	}
	public static void writeOutputMatrix(int [][] result, String filename) {
		try {
			String content = new StringBuilder().append(result.length + " " +result[0].length + "\n")
					.append(toString(result)).toString();
			Files.write(Paths.get(filename), content.getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
