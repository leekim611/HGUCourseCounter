package edu.handong.analysis.utils;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {

	public static ArrayList<String> getLines(String file, boolean removeHeader) {
		ArrayList<String> lines = new ArrayList<String>();
		Scanner inputStream = null;
		if (removeHeader) {
			try {
				inputStream = new Scanner(new File(file));
			}  catch (FileNotFoundException e) {
				System.out.println ("Error opening the file " + file);
				System.exit (0);
			}
			while (inputStream.hasNextLine ()) {
				String line = inputStream.nextLine ();
				lines.add(line);
			}
			inputStream.close ();
		}
		return lines;
	}
	public static void writeAFile(ArrayList<String> lines, String targerFileName) {
		return;
	}
}
