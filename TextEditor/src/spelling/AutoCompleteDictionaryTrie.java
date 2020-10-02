package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size=0;
    

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
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		String a = word.toLowerCase();
		TrieNode curr = root;
		int i=0;
		for(Character c : a.toCharArray())
		{
			TrieNode x = curr.insert(c);
			if(x!=null) i++;
			curr = curr.getChild(c);
		}
		curr.setEndsWord(true);
		
		if(i>0) return true;
		else return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this methodTrieNode next = null;
		// don't use original variable size here, coz everytime you call size method, size variable will change 
		// instead use a variable n and return it everytime
		// printTree();  this method uses pre order traversal (DFS) in the same way we want. so I used it here
		// but this method failed coz it always used original size variable
		int n = preOrderTraversal(root);
		
		
		return n;
	}
	
	public int preOrderTraversal(TrieNode curr)  // DFS algo
	{
		if (curr == null) 
 			return 0;     // return 0 if you reach a leaf node
 		int n=0;
 		if(curr.endsWord()==true) n++;

 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) 
 		{
 			next = curr.getChild(c);
 			n+=preOrderTraversal(next);  // calling itself with 1st child as the new curr, until you reach a leaf node, 
 			                             // then going to the 2nd child and so on
 		}
 		
 		return n;
	}
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if(s.equals("")) return false;
		String a = s.toLowerCase();
		
		TrieNode curr = root;
		
		for(Character c : a.toCharArray())
		{
			
			curr = curr.getChild(c);
			if(curr==null) return false; // takes care of extra characters in wrong words, like beatsi instead of beats
		}
		if( curr.endsWord() == true ) return true;
		else return false;  // takes care of incomplete words
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
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	 TrieNode curr = root;
    	 
    	 List<String> pred = new ArrayList<String>();
    	 List<TrieNode> visit = new LinkedList<TrieNode>();
    	 
    	 String a = prefix.toLowerCase();
    	 
    	 for(Character c : a.toCharArray())
    	 {
    		 curr=curr.getChild(c);
    		 if(curr==null) return pred; // returned empty list
    	 }
    	 visit.add(curr);
    	 if( curr.endsWord()==true ) pred.add(curr.getText());  // If the prefix itself is a valid word, it is included in the list of predictions
     
    	 
    	 while( pred.size()<numCompletions )
    	 {
    		 
    		 for( Character c : curr.getValidNextCharacters() )
    		 {
    			 TrieNode next = curr.getChild(c);
    			 visit.add(next);                     // adding all first children of curr node in the to be visited list with each iteration
    			 if( next.endsWord()==true ) pred.add(next.getText());  // if this child completes a word, add it to the list of predictions
    			 //System.out.println(pred);
    			 if( pred.size()>=numCompletions ) break;   
    		 }
    		 
    		 visit.remove(0);  // removed the first node, which was present curr
    		 if( visit.size()==0) break;  
    		 else curr = visit.get(0);     // updated curr to its next child
    		 
    	 }
    	 //System.out.println(pred);
    	 
    	 
    	 
         return pred;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		if(curr.endsWord()==true)
 		{
 			//System.out.println(curr.getText());
 		    size++;
 		}
 		
 		//size++;
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}