package com.cricket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

public class League {
	public static void main(String[] args) {
		File dir = new File(".");
		try {
			String pathname = dir.getCanonicalPath()+File.separator;
			File fin = new File(pathname+"input.txt");
			FileReader fReader = new FileReader(fin);
			BufferedReader reader = new BufferedReader(fReader);
			Iterator<String> it = reader.lines().iterator();
			while(it.hasNext()) {
				FileReader in = new FileReader(pathname+it.next());
				BufferedReader r1 = new BufferedReader(in);
				Stream<String> lines = r1.lines();
				String manOfMatch = findMoM(lines);
				System.out.println(manOfMatch);
				in.close();
				r1.close();
			}
			fReader.close();
			reader.close();
		} catch (IOException e) {
			System.out.println("Error opening input.txt at " + dir.getAbsolutePath());
			e.printStackTrace();
		}
	}

	public static String findMoM(Stream<String> lines) {
		Match match = new Match(lines);
		match.process();
		return match.computeBestPlayer();
	}
}
