package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;
	


    public DictionaryBST() {
        dict = new TreeSet<>();
    }

	
    
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there).
     *
     * O (log(n))
     *
     */
    public boolean addWord(String word) {
        return dict.add(word.toLowerCase());
    }


    /** Return the number of words in the dictionary */
    public int size() {
        return dict.size();
    }

    // O (log(n))
    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        return dict.contains(s.toLowerCase());
    }

}
