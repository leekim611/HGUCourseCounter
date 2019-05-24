package edu.handong.analysis.utils;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Utils {
	public static ArrayList<String> getLines(String file, boolean removeHeader) {
		ArrayList<String> lines = new ArrayList<String>();
		Scanner inputStream = null;
		boolean firstLine = true;
		if (removeHeader) {
			try {
				inputStream = new Scanner(new File(file));
			}  catch (FileNotFoundException e) {
				System.out.println ("The file path does not exist. Please check your CLI argument!");
				System.exit (0);
			}
			// directory
			while (inputStream.hasNextLine ()) {
				String line = inputStream.nextLine ();
				if (firstLine) {
					firstLine = false;
					continue;
				}
				lines.add(line);
			}
			inputStream.close ();
		}
		return lines;
	}
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		PrintWriter outputStream = null;
		File makeDirectory = new File(targetFileName);
		if (!makeDirectory.getParentFile().exists()) {
			makeDirectory.getParentFile().mkdirs();
		}
		try {
			outputStream = new PrintWriter(targetFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (String line : lines) {
			outputStream.println(line);
		}
		outputStream.close();
	}
}
