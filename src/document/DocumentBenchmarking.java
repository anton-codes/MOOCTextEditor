package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** A class for timing the EfficientDocument and BasicDocument classes
 *
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */

public class DocumentBenchmarking {


	public static void main(String [] args) {

	    // Run each test more than once to get bigger numbers and less noise.
	    int trials = 100;

	    // The text to test on
	    String textfile = "data/warAndPeace.txt";

	    // The amount of characters to increment each step
		int increment = 20000;

		// The number of steps to run.
		int numSteps = 20;

		// THe number of characters to start with.
		int start = 50000;

		for (int numToCheck = start; numToCheck < numSteps*increment + start; numToCheck += increment) {
			// numToCheck holds the number of characters that you should read from the
			// file to create both a BasicDocument and an EfficientDocument.

			System.out.print(numToCheck + "\t");

			String text = getStringFromFile(textfile, numToCheck);

			long startTime = System.nanoTime();
			for (int i = 0; i < trials; i++) {
				BasicDocument basicDocument = new BasicDocument(text);
				basicDocument.getFleschScore();
			}
			long endTime = System.nanoTime();


			System.out.print((endTime-startTime) / 100000000.0 + "\t");

			startTime = System.nanoTime();
			for (int i = 0; i < trials ; i++) {
				EfficientDocument efficientDocument = new EfficientDocument(text);
				efficientDocument.getFleschScore();
			}
			endTime = System.nanoTime();


			System.out.println((endTime-startTime) / 100000000.0);

		}

	}

	/** Get a specified number of characters from a text file
	 *
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with the appropriate number of characters
	 */
	public static String getStringFromFile(String filename, int numChars) {

		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e)
		{
		  System.out.println(e);
		  System.exit(0);
		}


		return s.toString();
	}

}
