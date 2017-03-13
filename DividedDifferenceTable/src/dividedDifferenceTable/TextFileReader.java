package dividedDifferenceTable;

import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextFileReader {

	public List<Float> x; // x values
	public List<Float> y; // f(x)

	public TextFileReader() throws FileNotFoundException, IOException {
		x = saveX();
		y = saveY();
	}

	public void readFile() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			System.out.println(everything);
		}
	}

	public List<Float> saveX() throws FileNotFoundException, IOException {
		List<Float> floats = new ArrayList<Float>();
		File file = new File("data.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			String[] list = line.split(" ");
			for (String s : list) {
				floats.add(Float.parseFloat(s));
			}
		}
		return floats;
	}

	public List<Float> saveY() throws FileNotFoundException, IOException {
		List<Float> floats = new ArrayList<Float>();
		File file = new File("data.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			br.readLine();
			String line = br.readLine();
			String[] list = line.split(" ");
			for (String s : list) {
				floats.add(Float.parseFloat(s));
			}
		}
		return floats;
	}

	public void printX() {
		for (Float s : x) {
			System.out.println(s);
		}
	}

	public void printY() {
		for (Float s : y) {
			System.out.println(s);
		}
	}

	public float[] convertToArr(List<Float> list) {
		float ar[] = new float[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ar[i] = list.get(i);
		}
		return ar;
	}
}