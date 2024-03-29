package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary {

	private LinkedList<String> dict;
	


    DictionaryLL() {
        dict = new LinkedList<>();
    }



    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there).
     *
     * O (n^2)
     */
    public boolean addWord(String word) {



        return (!isWord(word)) && dict.add(word.toLowerCase());
    }


    /** Return the number of words in the dictionary */
    public int size()
    {

        return dict.size();
    }

    // O(n)
    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {

        return dict.contains(s.toLowerCase());
    }

    
}
