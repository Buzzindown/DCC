import java.util.Scanner;
import java.util.HashSet;

public class May62021 {
    // Good morning! Here's your coding interview problem for today.

    // This problem was asked by Google.
    
    // Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.
    
    // For example, given the following Node class
    
    // class Node:
    //     def __init__(self, val, left=None, right=None):
    //         self.val = val
    //         self.left = left
    //         self.right = right
    // The following test should pass:
    
    // node = Node('root', Node('left', Node('left.left')), Node('right'))
    // assert deserialize(serialize(node)).left.left.val == 'left.left'

    // So we want to be able to create a tree, then be able to serialize/deserialize it
    // Part of the issue is i don't quite get what they're doing in the example.
    // It looks like They're sort of embedding function calls within the function input
    // if we break the example down the Main Node call will be value,left,right
    // The value of "node" will be root, the left node consists of 
    // "Node('Left', Node('left.left))" which will make the node to the left off root
    // be named left, and then within that we call another Node function which only consists
    // of a value and no children and it's value will be "left.left"
    // om the other side of the tree from the original function call we only have one embedded
    // node call which creates a root called right, we end up with something like this
    //                      'root'
    //                      /    \
    //                     /      \
    //                  'left'    'right'
    //                  /
    //                 /
    //             'left.left'
    // Working in java we may need the user to input null for deadend children
    // because a function with a prototype function{int a, int b, int c}
    // produces errors if called without all of the parameters function{a,b} for example
    // also not inputting parameters causes errors function(a,,) for example
    // therefore function(a,null,null) might be the best way to handle this
    // I feel something recursive may be in the works

    public String value;
    public May62021 Left;
    public May62021 Right;

    public May62021(String val, String left, String right){

        this.value = val;

        // one branch, if it's not null we should pursue it
        if(left.equals("null") == false){
            // we can start by deleting the method name
            // because our current form is May62021(val,XXXX,YYYY)
            String newString = left.substring(9, left.length()-1);
            // now we just have val,XXXX,YYYYY
            int firstCommaPos = newString.indexOf(",");

            String value = newString.substring(0, firstCommaPos);

            String leftside;
            String rightside;

            newString = new String(newString.substring(firstCommaPos+1));

            // now we have XXXX, YYYYY

            // if the XXXX isn't null we need to find the seperating comma
            if(newString.substring(0,4).equals("null") == false){
                // we want to chop off the function prefix so we're looking at a bunch of brackets
                newString = newString.substring(8);
                // then we can look for brackets
                int x;
                int index = 0;
                int counter = 0;
                for(x = 1; x < newString.length(); x++){
                    if(newString.charAt(x) == '('){
                        counter--;
                    }else if(newString.charAt(x) == ')'){
                        counter++;
                    }
                    if(counter == 1){
                        index = x;
                        break;
                    }
                }

                // the index will be the closing bracket on the function for XXXX
                // so we know where the first call ends, ie, May62021(a,b,c), YYYY
                leftside = newString.substring(0,index+1);
                rightside = newString.substring(index+2);
                String prefix = "May62021";
                // add the function back to it
                leftside = prefix.concat(leftside);
                // I think we need + 2 in order to skip the bracket and the comma
                this.Left = new May62021(value, leftside,rightside);

               
            }else{
                // if we're in here the form is 
                // null, YYYYY

                this.Left = new May62021(value,"null",newString.substring(newString.indexOf(",") + 1));
            }

        }else{
            this.Left = null;
        }

        // right subtree
        if(right.equals("null") == false){
 
            String newString = right.substring(9, right.length()-1);
            int firstCommaPos = newString.indexOf(",");

            String value = newString.substring(0, firstCommaPos);

            String leftside;
            String rightside;

            newString = new String(newString.substring(firstCommaPos+1));

            if(newString.substring(0,4).equals("null") == false){
                newString = newString.substring(8);

                int x;
                int index = 0;
                int counter = 0;
                for(x = 1; x < newString.length(); x++){
                    if(newString.charAt(x) == '('){
                        counter--;
                    }else if(newString.charAt(x) == ')'){
                        counter++;
                    }
                    if(counter == 1){
                        index = x;
                        break;
                    }
                }

                leftside = newString.substring(0,index+1);
                rightside = newString.substring(index+2);
                String prefix = "May62021";
        
                leftside = prefix.concat(leftside);

                this.Right = new May62021(value, leftside,rightside);

               
            }else{

                this.Right = new May62021(value,"null",newString.substring(newString.indexOf(",") + 1));
            }

        }else{
            this.Right = null;
        }

    };

    public void printTree(May62021 root){
        if(root != null){
            System.out.println(root.value);
            printTree(root.Left);
            printTree(root.Right);
        }
    }

    public String printTreeString(May62021 root){
        String value = root.value;
        String left;
        String right;
        if(root.Left != null){
            left = new String("," + printTreeString(root.Left) + ","); 
        }else{
            left = new String(",null,");
        }

        if(root.Right != null){
            right = new String(printTreeString(root.Right) + ")"); 
        }else{
            right = new String("null)");
        }

        String prefix = "May62021(";

        prefix = prefix.concat(value);
        prefix = prefix.concat(left);
        prefix = prefix.concat(right);

        return prefix;


        
    }



    public static void main(String[] args){

        // grab all our data

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter root node value");
        String val = myScanner.nextLine();
        System.out.println("Enter left subtree String");
        String leftSub = myScanner.nextLine();
        System.out.println("Enter right subtree String");
        String rightSub = myScanner.nextLine();


        myScanner.close();
        // put input string here

        May62021 test = new May62021(val, leftSub, rightSub);

        test.printTree(test);

        System.out.println("\n" + test.printTreeString(test));

    }
}

// This was a tough one for sure, mentally hard to figure out how to parse everything out
// then construct the tree, overall success I could definitely clean up the code and make it more readable
// however I think i'm done with this one

// example input 

// Enter root node value
// root
// Enter left subtree String
// May62021(left1,May62021(left2,May62021(left3,null,null),null),null)
// Enter right subtree String
// May62021(right1,null,May62021(Right2,null,null))