import java.util.Arrays;
import java.util.Scanner;

public class may312021 {
    // Good morning! Here's your coding interview problem for today.

    // This problem was asked by Palantir.
    
    // Write an algorithm to justify text. Given a sequence of words and an integer 
    
    // line length k, return a list of strings which represents each line, fully justified.
    
    // More specifically, you should have as many words as possible in each line. There 
    
    //should be at least one space between each word. Pad extra spaces when necessary 
    
    //so that each line has exactly length k. Spaces should be distributed as equally as possible, 
    
    //  with the extra spaces, if any, distributed starting from the left.
    
    // If you can only fit one word on a line, then you should pad the right-hand side with spaces.
    
    // Each word is guaranteed not to be longer than k.

    // this looks a little tricky, it says we should  have as many words as possible on each line
    // so we can start by seeing how many words we can fit less than k,
    // something like while(possibleLength <=k){
    //                          possibleLength += strlen + 
    // from there we can decide how many words will go on our line
    // then we just have to worry about spacing

    void solution(String[] words, int L){
        // determine strings for our line
        // main word loop
        int i = 0;
        int j = 0;
        int k = 0;
        int m = 0;
        int currLength = 0;
        int numWords = 0;
        int startpos = 0;
        
        for(i = 0; i < words.length; i++){
            if(currLength + words[i].length() + 1 <= L){
                currLength += words[i].length();
                numWords++;
                if(numWords > 1){
                    currLength++;
                }
            }
            
            if(currLength + words[i].length() + 1 > L || i == words.length-1){
                // pushed over by this word[i]
                // now we need to go around appending stuff

                int buckets[];
                
                if(numWords > 1){
                    buckets = new int[numWords-1];
                    Arrays.fill(buckets,1);
                }else{
                    buckets = new int[1];
                    buckets[0] = 0;
                }
                int spaces = L - currLength;

                // System.out.println("strlen " + currLength + " numWords " + numWords + " spaces " + spaces);
                
                j = 0;
                while(spaces > 0){
                    // end of arr loop back
                    if(j == buckets.length){
                        j = 0;
                    }
                    buckets[j]++;
                    j++;
                    spaces--;
                }
                // build our line
                StringBuilder sb = new StringBuilder();
                m = 0;
                for(k = startpos; k < startpos + numWords; k++){
                    sb.append(words[k]);
                    // append all our spaces
                    if(m < buckets.length){
                        while(buckets[m] > 0){
                            sb.append(' ');
                            buckets[m]--;
                        }
                      }
                    m++;
                }
                // we should have our line now
                System.out.println(sb + "!!");
                // fill all our buckets up as evenly as possible

                currLength = 0;
                numWords = 0;
                startpos = i + 1;

            }

        }

    }

    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter number of elements");
        int numElems = Integer.parseInt(myScanner.nextLine());

        System.out.println("Enter line length");
        int length = Integer.parseInt(myScanner.nextLine());

        System.out.println("Enter your words line by line");

        String arr[] = new String[numElems];
        int x;


        for(x = 0; x < numElems; x++){
            arr[x] = myScanner.nextLine();
        }

        myScanner.close();

        may312021 test = new may312021();

        
        test.solution(arr,length);

    }
}
