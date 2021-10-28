//This Java program demonstrates the implemntation of Trie Data structure in Java
//This Implementation only supports lowercase letters

class TrieNode{
	TrieNode [] children= new TrieNode[26];	//Children a array of TrieNode pointers 
	boolean isEnd;		//a boolean variable used to indicate the end of the current word
}


class Trie{
	TrieNode root;		//Root of our Trie

	public Trie(){
		root= new TrieNode();		//Intializing the root of our Trie
	}

	//This function insert the given word into our Trie
	public void insert(String word){
		TrieNode curr= root;//get the root and create curr node to perform the operation

		for(int i=0; i<word.length(); i++){
			char ch= word.charAt(i);

			if(curr.children[ch - 'a'] == null){
				curr.children[ch - 'a']= new TrieNode();//if it is empty put a new Trienode in the curr postion
			}

			curr= curr.children[ch -'a'];
		}
		curr.isEnd= true;		//marks the end of the current word in our Trie
	}


//Search function which searches for the word in the Trie. It returns true if the provided word exists in Trie else returns false
	public boolean search(String word){
		TrieNode curr= root;

		for(int i=0; i< word.length(); i++){
			char ch= word.charAt(i);

			if(curr.children[ch - 'a'] == null)		//We pick a char from the given string and keep moving to its children untill we reached its end
				return false;

			curr= curr.children[ch - 'a'];
		}
		return curr.isEnd;		//When we reach the even of the provided string we simply return if the end is True or False
	}


	//It returns true if there exists a word which starts with the provided word
	public boolean startsWith(String word){
		TrieNode curr= root;

		for(int i=0; i< word.length(); i++){
			char ch= word.charAt(i);

			if(curr.children[ch - 'a'] == null) 	//We pick a char from the string and keep moving to the children
				return false;

			curr= curr.children[ch -'a'];
		}
		return true;	//It the we have completely traversed the string and the above false condition is encountered that means there is a word in trie that starts with the provided string 
	}
	
	//This function is used to delete the provided word from our Trie
	public void delete(String word) {
		TrieNode curr= root;
		
		for(int i=0; i< word.length(); i++) {
			char ch= word.charAt(i);
			
			curr=curr.children[ch -'a'];
		}
		curr.isEnd= false;
	}
}



public class Trie_Implementation {
	
	public static void main(String[] args) {
		Trie t= new Trie();	//Intialzing our Trie

		t.insert("hacktoberfest"); 	//Insert a word into our Trie

		boolean isPresent= t.search("hacktoberfest"); 	//Search if the word is present in our Trie
		System.out.println("is hacktoberfest present in our Trie => "+isPresent); 		//Prints true as the word is exists

		boolean isStartsWith= t.startsWith("hack");		//Checks if any word starts with hack
		System.out.println("is there any word in our Trie that starts with hack => "+ isStartsWith);	//Prints true

		boolean isPresent2= t.search("hecktoberFest"); 
		System.out.println("is hecktoberfest present in our Trie => "+ isPresent2); //Printts false since there is no word with hecktoberfest

		t.delete("hacktoberfest"); //Delete the word from our Trie
		boolean isPresentAfterDeleting= t.search("hacktoberfest");
		System.out.println("is hacktoberfest present in our Trie => "+ isPresentAfterDeleting); //Prints false as the word has been deleted 
		
	}
}
