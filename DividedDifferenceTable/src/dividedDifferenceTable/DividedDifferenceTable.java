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
		if(x.length > 40) {
			System.out.println("data needs to contain x values less than 40");
			System.exit(0);
		}
		// first entry in each column in the divided-difference table
		float[] ddtFirst = getFirstEntry(x, y);
		// divided difference table
		float[][] ddt = makeDDT(x, y);
		printDDT(x, ddt);
		printInterpolation(x, ddtFirst);
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
		float[][] ddt = new float[n][n + 1];
		// System.out.println("n: " + n);
		for (int i = 0; i < n; i++) {
			ddt[0][i] = fx[i];
			// System.out.println(ddt[0][i]);
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

		System.out.println("Divided Difference Table is: ");
		// print top of the table.
		// x, f[], f[ , ], f[ , , ],,,
		System.out.print("x(i)\t");
		for (int i = 0; i < ddt[0].length - 1; i++) {
			System.out.print("y" + i + "(i)\t");
		}
		System.out.println(" ");

		// print the table
		for (int i = 0; i < ddtCopy[0].length - 1; i++) {
			System.out.print(x[i] + "\t");
			for (int j = 0; j < ddtCopy[0].length - 1 - i; j++) {
				System.out.printf("%.2f\t", ddtCopy[j][i]);
			}
			System.out.println();
		}
	}

	public static void printInterpolation(float[] x, float[] ddtFirst) {
		float[] xs = x.clone();
		float[] ddt = ddtFirst.clone();
		// (x-x0),(x-x0)(x-x1),,,
		String[] xInterpol = new String[xs.length];
		for (int i = 0; i < xInterpol.length - 1; i++) {
			xInterpol[i] = "(x-" + xs[i] + ")";
			if (i >= 1) {
				xInterpol[i] = xInterpol[i - 1] + xInterpol[i];
			}
		}

		// print the interpolation
		System.out.println("Interpolation polynomial is:  ");
		System.out.print(ddt[0] + " + ");
		for (int i = 0; i < xs.length-1; i++) {
			System.out.printf("%.3f%s",ddt[i + 1],xInterpol[i]);
			//System.out.printf("%.3f", ddt[i + 1]);
			//System.out.printf("%s", xInterpol[i]);
			//System.out.print(ddt[i + 1] + xInterpol[i]);
			if(i < xs.length-2) {
				System.out.print(" + ");
			}
		}

	}

}
