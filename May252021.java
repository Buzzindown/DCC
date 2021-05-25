import java.util.HashSet;
import java.util.Scanner;

public class May252021 {
    // Good morning! Here's your coding interview problem for today.

    // This problem was asked by Microsoft.
    
    // Given a dictionary of words and a string made up of those words (no spaces), 
    
    // return the original sentence in a list. If there is more than one possible reconstruction, 
    
    // return any of them. If there is no possible reconstruction, then return null.
    
    // For example, given the set of words 'quick', 'brown', 'the', 'fox', and the string 
    
    // "thequickbrownfox", you should return ['the', 'quick', 'brown', 'fox'].
    
    // Given the set of words 'bed', 'bath', 'bedbath', 'and', 'beyond', and the string 
    
    // "bedbathandbeyond", return either ['bed', 'bath', 'and', 'beyond] or ['bedbath', 'and', 'beyond'].

    // seems like an interesting problem. The brute force method right away might be something like,
    // we iterate over our dictionary and compare the word(i) to the first z characters
    // of the string, and if they match, we print our word(i) and shift up our search of the main string
    // by the length of i
    // this would be n * m where n is the size of the dictionary and m is the average word length 

    // how can we do better? 

    // Maybe we put our dictionary into a hashset, then we iterate over each character and see if the set
    // contains the sub string from the start -> ith letter, from there we can move up our beginning
    // to the ith letter and ccontinue iterating/searching. This should be better because .contains()
    // and .remove() have expected O(1) complexities if the collision rate is low, which it should be
    // this would be n * (1) where n is the length of our main string

    // we could probably do better still, however we'll see how this works out

    void solution(HashSet<String> dict, String mainString){
        System.out.println("--- Word found ---");
        // have to use stringbuilder for o(1) append, in c 
        // this wouldn't be a problem, but since java uses arrays that are 
        // resized and copied when appending, it becomes a o(n) operation
        StringBuilder subStr = new StringBuilder();
        subStr.ensureCapacity(mainString.length());

        for(int i = 0; i < mainString.length(); i++){
            subStr.append(mainString.charAt(i));
            if(dict.contains(subStr.toString())){
                System.out.println(subStr.toString());
                // we may be able to do this without having to remove, if a string cannot be a substring
                // of a later dict element, ie say we had bedbathbetbath, with 'bed' 'bath' 'bedbath'
                // we would have issues if we didnt remove bed or bath as they would get searched first
                // but if we can guarantee that event doesn't occur then we don't need to remove
                dict.remove(subStr.toString());
                subStr.setLength(0);
            }
        }
    }

    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter main string");
        String str = myScanner.nextLine();

        System.out.println("Enter number of elements");
        int numElems = Integer.parseInt(myScanner.nextLine());

        System.out.println("Enter your dictionary line by line");

        HashSet<String> set = new HashSet<>();
        int x;

        for(x = 0; x < numElems; x++){
            set.add(myScanner.nextLine());
        }

        myScanner.close();

        May252021 t = new May252021();
        t.solution(set, str);
    }
}
