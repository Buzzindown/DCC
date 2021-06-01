import java.util.Scanner;

public class June12021 {
    // This problem was asked by Amazon.

    // Run-length encoding is a fast and simple method of encoding strings. 
    
    // The basic idea is to represent repeated successive characters as a single count and character.
    
    // For example, the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".
    
    // Implement run-length encoding and decoding. You can assume the string to be encoded 
    
    // have no digits and consists solely of alphabetic characters. You can assume the string to be decoded is valid. 
    
    // this seems okay, for encoding we can loop over the string and store the current character
    // + how many times it occurs, then we can append the number + character to a new string
    // for decoding we basically gothe other way, taking the integer and iterating it,
    // appending x  amount of the i + 1 characters to a new string

    String encode(String plain){
        
        StringBuilder sb = new StringBuilder();

        char curChar = plain.charAt(0);
        int iterations = 0;
        for(int i = 0; i < plain.length(); i++){
            if(curChar == plain.charAt(i)){
                // just add to our num
                iterations++;
            }else{
                sb.append(Integer.toString(iterations) + curChar);
                curChar = plain.charAt(i);
                iterations = 0;
                i--;
            }
        }

        if(iterations > 0){
            sb.append(Integer.toString(iterations) + curChar);
        }

        return sb.toString();
    }

    String decode(String encoded){

        StringBuilder sb = new StringBuilder();

        char curChar;
        int iterations = 0;

        for(int i =0; i < encoded.length() - 1; i+=2){
            curChar = encoded.charAt(i+1);
            iterations = Integer.parseInt(Character.toString(encoded.charAt(i)));

            while(iterations > 0){
                sb.append(curChar);
                iterations--;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter your string");
        String dir = myScanner.nextLine();

        System.out.println("Enter 1 for encode, 2 for decode");
        int choice = Integer.parseInt(myScanner.nextLine());
        myScanner.close();

        June12021 test = new June12021();

        if(choice == 1){
            System.out.println("Encoded " + test.encode(dir));
            System.out.println("Decoded " + test.decode(test.encode((dir))));
        }else{
            System.out.println("Decoded " + test.decode(dir));
            System.out.println("Encoded " + test.encode(test.decode(dir)));
        }
    }

}
