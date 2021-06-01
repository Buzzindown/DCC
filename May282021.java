import java.io.CharArrayReader;
import java.util.Scanner;
import java.util.Stack;

public class May282021 {
    // Good morning! Here's your coding interview problem for today.

    // This problem was asked by Facebook.
    
    // Given a string of round, curly, and square open and closing brackets, return whether the brackets 
    
    // are balanced (well-formed).
    
    // For example, given the string "([])[]({})", you should return true.
    
    // Given the string "([)]" or "((()", you should return false.

    // well, we know brackets need to be in pairs
    // so we can do someting like +1 -1 for each left/right brack, but 
    // how do we consider cases like }}{{ as this would not be balanced
    // we can say that we must start with an opening bracket 
    // ie maybe with start with a +1 {{}{}}
    // the rule for opening brackets might be that if we have a bracket,
    // and it changes our count from 0 (meaning we're not in any brackets)
    // then we check if it's an opening bracket, if it's not then it's no good
    // ie xxxxxx} {}

    Boolean solution(String brackets){
        // I havean idea, we can use a stack like structure where we push on our open brackets
        // and then when we get to a closing bracket, we check and see if it's type matches
        // the type of the bracket at the top of the stack, if it does we pop it off 
        // and continue, if it doesn't then we'vve got a mismatch and return false
        Stack<Character> stack = new Stack<Character>();

        for(int i = 0; i < brackets.length(); i++){

            Character currentCharacter = brackets.charAt(i);
            if(currentCharacter == '[' || currentCharacter == '{' || currentCharacter == '('){
                stack.push(currentCharacter);
                // push on our opening brackets
            }else{
                if(stack.size() > 0){
                    Character topStack = stack.peek();
                    if(currentCharacter == '}' && topStack == '{' || currentCharacter == ']' && topStack == '[' || 
                                                                     currentCharacter == ')' && topStack == '('){
                        stack.pop();
                        // pop off our pair
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
                
            }
        }

        if(stack.size() == 0){
            return true;
        }
        return false;
    }
    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter your string");
        String dir = myScanner.nextLine();

        myScanner.close();

        May282021 test = new May282021();

        System.out.println(test.solution(dir));
    }
}


