package ctrls;

import java.io.*;

public class Document {
	
	public static String readFile(String filename) {
		StringBuilder s = new StringBuilder();
		try(BufferedReader in = new BufferedReader(
				new InputStreamReader(new FileInputStream(filename)))) {
			for (String line = in.readLine();  line != null; line = in.readLine()) {
				s.append(line);
				s.append("\n");
			}	
		} catch (Exception e) {
			System.out.println(e);
		}
		return s.toString(); 
	}
}
