import java.util.Scanner;

public class May102021 {
    // Good morning! Here's your coding interview problem for today.

    // This problem was asked by Facebook.

    // Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.

    // For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.

    // You can assume that the messages are decodable. For example, '001' is not allowed.

    // definitely a combinatorics style question 
    // some things to note 
    // a letter could be represented as one digit or two
    // there are 9 digits that could be single letters, 17 that could be 2
    // so if we have the string xxxxxx
    // we could split it up x|x|x|x|x|x
    // or we could do       xx | xx | xx
    // or we could even do  x | xx | xx | x    <-- we already accounted for the side single ones in the first batch

    // if the string has an even number of characters 
    // then there are N single digit possibilities,
    // then we split it evenly into two, there are n/2 possibilities
    // then we shift the split by 1, causing use to get one digit at the start
    // and one digit at the end, which we already accounted for in our single 
    // splits so we should be able to subtract those safely 

    // if the string has an odd number of characters
    // then we still get N single digit possibilities
    // however, when we split it we can't do so evenly so we must
    // split by two with the split at the start s o xx | xx | xx | x
    // and we must also split with the shift at 1   x | xx | xx | xx
    // in which case we still subtract two posibilities that we had accoutned for in the first batch

    // well that wasn't super helpful, after reading into the q more I've decided to go another route
    // I suppose there's a way to do this recurisvely

    // we could consider recursively "chomping" away at the problem
    // by this i mean we start with the first two offsets
    // where our first letter will be xx or x
    // from there we can recurisvely shift across the string
    // each time doing two or 1 offset from the current possition
    // and we can check if the number is <= 26, for two digits,
    // if it is, we can count the combo, if it isn't then that 
    // two digit combination is impossible
    // so dowm the single offset call for a string 12345
    //                                          1 | 2345 
    //                              2 | 345                     23 | 45
    //                   3 | 45            34 | 5          4 | 5           45
    //           4 | 5                   5               5           
    //       5
    // we can stop when return an empty string as we'll have
    // "chomped" away all the ltters

   public int solution(String A){
       
       int ret = 0;
       System.out.println(A);

       // base case
       // needs to be <= 1 cause 2 shift can return ""
       // whereas 1 shift always returns 1
       if(A.length() <= 1){
           return 1;
       }
       // single shift turn abcde into bcde
       ret += solution(A.substring(1, A.length()));
       // double shift turn abcde into cde
       if(A.length() > 1){
            if(Integer.parseInt((A.substring(0, 2))) <= 26){
                ret += solution(A.substring(2, A.length()));
            }
        }

        return ret;
   }

   // will have an exponential time comp as each call splits and makes two children calls
   // might be able to do something better if we can figure out how to skip duplicates

    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);
        May102021 holder = new May102021();

        System.out.println("Enter your encoded string");
        String input = new String(myScanner.nextLine());

        System.out.println("Possible combos found: " + holder.solution(input));

    }
}
