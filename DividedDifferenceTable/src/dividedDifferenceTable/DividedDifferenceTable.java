/**
 * 
 */
package dividedDifferenceTable;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author maxkim
 *
 */
public class DividedDifferenceTable {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		TextFileReader fileReader = new TextFileReader();
		fileReader.readFile();
		fileReader.printX();
		fileReader.printY();
	}

}
