import java.util.Scanner;
import java.util.HashSet;

public class May142021 {

    // ]Good morning! Here's your coding interview problem for today.

    // This problem was asked by Twitter.

    // Implement an autocomplete system. That is, given a query string s and a set of all possible query strings, return all strings in the set that have s as a prefix.

    // For example, given the query string de and the set of strings [dog, deer, deal], return [deer, deal].

    // Hint: Try preprocessing the dictionary into a more efficient data structure to speed up queries.
    

        // easiest way would be to see if the the string length > suffix length
        /// then see if the substring matches

        public void solution(String[] arr, String search){
            System.out.println("Matching strings");
            for(String str: arr){
                if(str.length() >= search.length()){
                    if(str.substring(0, search.length()).equals(search)){
                        System.out.println(str);
                    }
                }
            }
        }

        // will be n^m time
        // constant space
        // I've heard about tries's before but never actually used them

        // 
 
}

class Trie {
            // let's see if we can practice an implementation of one here
        // because it's search should be O(log(n)) iirc
        // A trie structure creates tree's out of our words
        // each node will have up to 26 possible cchildren for each letter of the alphabet
        // then we build the tree in such a way that each unique path down our tree
        // represents a word in our search array
        // 
        //              rootNode            with words there and their
        //                  t
        //                  |
        //                  h
        //                  |
        //                  e
        //                 / \
        //               i    r
        //              /      \
        //             r        e

        static class trieNode {
            // array of possible children
            trieNode[] children = new trieNode[26];
            boolean eow;
            // can use a hashset to keep track of which kids we have
            HashSet<Integer> kids = new HashSet<>();

            trieNode(){
                // initialize everything to null
                int i = 0;
                for(i = 0; i < 26; i++){
                    this.children[i] = null;
                }
                eow = false;
            }
        }

        static trieNode root;

        static void insert(String word){
            int level = 0;
            int length = word.length();
            int index = 0;

            trieNode start = root;

            for(level = 0; level < length; level++){
                index = word.charAt(level) - 'a';
                // if it doesn't exist already we want to insert it
                if(start.children[index] == null){
                    start.kids.add(index);
                    start.children[index] = new trieNode();
                }

                start = start.children[index];
            }


            start.eow = true;
        }

        static void search(String key){
            int level = 0;
            int length = key.length();
            int index = 0;

            trieNode start = root;

            for(level = 0; level < length; level++){

                index = key.charAt(level) - 'a';
                if(start.children[index] == null){
                    System.out.println("No matches");
                    return;
                }
                start = start.children[index];
            }

            System.out.println("Matches:");
            buildString(start, key);

        }

        static void buildString(trieNode t, String prefix){
            // base case
            if(t.eow == true){
                System.out.println(prefix);
            }
            // all of these accesses will take constant time 
            // because we used a set, 
            // At most t can have 26 children
            for(Integer i: t.kids){
                System.out.println(i + " -> " + (char)(i+97));
                String temp = new String(prefix);
                temp = prefix + (char)(i+97);
                buildString(t.children[i], temp);
            }

        }


        public static void main(String[] args){

            Scanner myScanner = new Scanner(System.in);
            
            System.out.println("Enter search key");
            String searchKey = myScanner.nextLine();
    
            System.out.println("Enter number of elements");
            int numElems = Integer.parseInt(myScanner.nextLine());
    
            System.out.println("Enter your array of strings line by line");
    
            String arr[] = new String[numElems];
            int x;
    
    
            for(x = 0; x < numElems; x++){
                arr[x] =(myScanner.nextLine()).toLowerCase();
            }
    
            myScanner.close();
    
            // May142021 t = new May142021();
      
            // t.solution(arr, searchKey);

            root = new trieNode();

            for(String a: arr){
                insert(a);
            }

            search(searchKey);
        }


        // the insert and search will have m * n, where m is the 
        // number of words and n is the average length, they're the same
        // because for each we're doing n comparison for every word, and 
        // we have m words. 
        // unfortunately we lose out one spaces significantly, we end up 
        // with a space complexiry of (26 * prefix length * numWords)
        // although we could make this smaller on average if we used
        // unique letters based on the strings we received instead of 
        // just covering the whole alphabet
}
