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
		float[] x = fileReader.convertToArr(fileReader.x); // x
		float[] y = fileReader.convertToArr(fileReader.y); // f(x)
		// first entry in each column in the divided-difference table
		// f[x0], f[x0,x1], f[x0,x1,x2],,,
		float[] ddtFirst = getFirstEntry(x, y); // divided difference tale
		// printArray(x);
		// printArray(y);
		// printArray(ddtFirst);
		float[][] ddt = makeDDT(x, y);
		printDDT(x,ddt);
	}

	public static void printArray(float[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println(" ");
	}

	public static float[] getFirstEntry(float[] x, float[] y) {
		int n = x.length;
		// first entry in each column in the divided-difference table
		float[] dd = y.clone();
		for (int i = 1; i < n; i++) {
			for (int j = n - 1; j > 0; j--) {
				if (j - i >= 0) {
					dd[j] = (dd[j] - dd[j - 1]) / (x[j] - x[j - i]);
					// System.out.println(dd[j] + " ");
				}
			}
		}
		return dd;
	}

	public static float[][] makeDDT(float[] xvalues, float[] yvalues) {
		float[] x = xvalues.clone();
		float[] fx = yvalues.clone();
		int n = x.length;
		float[][] ddt = new float[n][n+1];
		System.out.println("n: " + n);
		for(int i = 0; i < n; i++) {
			ddt[0][i] = fx[i];
			System.out.println(ddt[0][i]);
		}
		
		int k = 0;
		for (int i = 1; i < n; i++) {
			k = i;
			for (int j = 0; j < n - i; j++) {
				ddt[i][j] = (ddt[i - 1][j + 1] - ddt[i - 1][j]) / (x[k] - x[j]);
				k++;
			}
		}
		return ddt;
	}

	public static void printDDT(float[] x, float[][] ddt) {
		float[][] ddtCopy = ddt.clone();
		System.out.println("this: " + ddt[0].length);
		/*
		System.out.println(ddtCopy[0][0]);
		System.out.println(ddtCopy[0][1]);
		System.out.println(ddtCopy[0][2]);
		System.out.println(ddtCopy[0][3]);
		System.out.println(ddtCopy[0][4]);


		System.out.println(ddtCopy[1][0]);
		System.out.println(ddtCopy[1][1]);
		System.out.println(ddtCopy[1][2]);
		System.out.println(ddtCopy[1][3]);
		System.out.println(ddtCopy[1][4]);


		System.out.println(ddtCopy[2][0]);
		System.out.println(ddtCopy[2][1]);
		System.out.println(ddtCopy[2][2]);
		System.out.println(ddtCopy[2][3]);
		System.out.println(ddtCopy[2][4]);


		System.out.println(ddtCopy[3][0]);
		System.out.println(ddtCopy[3][1]);
		System.out.println(ddtCopy[3][2]);
		System.out.println(ddtCopy[3][3]);
		System.out.println(ddtCopy[3][4]);
	    */

		System.out.println("Begein");
		for (int i = 0; i < ddtCopy[0].length-1; i++) {
			System.out.print(x[i]);
			for (int j = 0; j < ddtCopy[0].length-1; j++) {
				System.out.print("  ");
				System.out.print(ddtCopy[j][i]);
			}
			System.out.println();
		}

	}

}
