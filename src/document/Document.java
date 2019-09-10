package document;

/** 
 * A class that represents a text document
 * @author UC San Diego Intermediate Programming MOOC team
 */
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;

	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	protected Document(String text)
	{
		this.text = text;
	}

	/** Returns the tokens that match the regex pattern from the document
	 * text string.
	 * @param pattern A regular expression string specifying the
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);

		while (m.find()) {
			tokens.add(m.group());
		}

		return tokens;
	}

	/** This is a helper function that returns the number of syllables
	 * in a word.  You should write this and use it in your
	 * BasicDocument class.
	 *
	 * You will probably NOT need to add a countWords or a countSentences
	 * method here.  The reason we put countSyllables here because we'll
	 * use it again next week when we implement the EfficientDocument class.
	 *
	 * For reasons of efficiency you should not create Matcher or Pattern
	 * objects inside this method. Just use a loop to loop through the
	 * characters in the string and write your own logic for counting
	 * syllables.
	 *
	 * @param word  The word to count the syllables in
	 * @return The number of syllables in the given word, according to
	 * this rule: Each contiguous sequence of one or more vowels is a syllable,
	 *       with the following exception: a lone "e" at the end of a word
	 *       is not considered a syllable unless the word has no other syllables.
	 *       You should consider y a vowel.
	 */
	protected int countSyllables(String word) {

		//  A, E, I, O
		int syllables = 0;
		String vowels = "AEOIUY";

		// If the word consists of only one vowel - return 1
		// If it's onr letter but now a vowel - return 0
		if (word.length() == 1)
			if (vowels.contains(Character.toString(word.charAt(0)).toUpperCase()))
				return 1;
			else return 0;

		// If word if more than one letter long
		for (int i = 0; i < word.length() ; i++) {

			char thisChar = Character.toUpperCase(word.charAt(i));

			// When iterating over the final letter
			if (i + 1 == word.length()){
				// If the word ends with an 'e'
				if (thisChar == 'E') {
					if (syllables == 0)
						return 1;
					// If ends with an 'e', but the previous letter was a vowel
					else if(vowels.contains(Character.toString(word.charAt(i-1)).toUpperCase()))
						return syllables + 1;
					else return syllables;
				}
				// If ends with any other vowel - increment syllables and return
				if (vowels.contains(Character.toString(thisChar)))
					return syllables+1;
				// Final return (if the word ends with a non-vowel)
				return syllables;
			}

			// Increment syllables if current letter is a vowel followed by non-vowel
			if (vowels.contains(Character.toString(thisChar)) && !vowels.contains(Character.toString(word.charAt(i+1)).toUpperCase())) {
				syllables++;
				//Because next char is not a vowel
				i++;
			}
		}

	    return syllables;
	}
	
	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability score of this document */
	public double getFleschScore()
	{
		double wordCount = (double) getNumWords();
	    return 206.835 - (1.015 * ((wordCount) / getNumSentences())) - (84.6 * (((double) getNumSyllables()) / wordCount));

	}
	
	
	
}
