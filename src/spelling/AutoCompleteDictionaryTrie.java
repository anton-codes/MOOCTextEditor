package spelling;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word) {


		TrieNode node = root;
		boolean newWord = false;

		String text = word.toLowerCase();

		for (int i = 0; i < text.length() ; i++) {

			if ( node.getChild(text.charAt(i)) != null ) node = node.getChild(text.charAt(i));
			else { node.insert(text.charAt(i)); newWord = true; }

		}

		node.setEndsWord(true);
		if (newWord) size++;

	    return newWord;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size() {
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) {

		TrieNode node = root;

		for (int i = 0; i < s.length() ; i++) {
			if (node.getChild(Character.toLowerCase(s.charAt(i))) == null) return false;
			node = node.getChild(Character.toLowerCase(s.charAt(i)));
		}

		return true;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) {

		 List<String> autoCompleteList = new LinkedList<>();
		 TrieNode node = root;

		 prefix = prefix.toLowerCase();

		 for (Character c : prefix.toCharArray())
		 	if (node.getChild(c) != null) node = node.getChild(c);
		 	else return autoCompleteList;

		 Queue<TrieNode> queue = new LinkedList<>();
		 queue.add(node);

		 while (!queue.isEmpty() && autoCompleteList.size() < numCompletions) {
			node = queue.remove();
			if (node.endsWord()) autoCompleteList.add(node.getText());

			for (Character c : node.getValidNextCharacters())
				queue.add(node.getChild(c));


		 }

         return autoCompleteList;
     }

 	// For debugging
 	public void printTree() {
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr) {
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}