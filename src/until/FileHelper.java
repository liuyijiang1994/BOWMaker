package until;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

	public static List<String> readFileByRow(String FileDir) {
		List<String> contents = new ArrayList<String>();
		try {
			// read file content from file
			FileReader reader = new FileReader(FileDir);
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				contents.add(str);
			}
			br.close();
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	return contents;
	}
	
}
