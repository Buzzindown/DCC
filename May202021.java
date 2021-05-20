import java.util.Scanner;
import java.util.Stack;


public class May202021 {
    // This problem was asked by Google.

    // Suppose we represent our file system by a string in the following manner:
    
    // The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
    
    // dir
    //     subdir1
    //     subdir2
    //         file.ext
    // The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
    
    // The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
    
    // dir
    //     subdir1
    //         file1.ext
    //         subsubdir1
    //     subdir2
    //         subsubdir2
    //             file2.ext
    // The directory dir contains two sub-directories subdir1 and subdir2. subdir1 
    
    // contains a file file1.ext and an empty second-level sub-directory subsubdir1. 
    
    // subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
    
    // We are interested in finding the longest (number of characters) absolute path 
    
    // to a file within our file system. For example, in the second example above, the 
    
    // longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).
    
    // Given a string representing the file system in the above format, return the 
    
    // length of the longest absolute path to a file in the abstracted file system.
    
    // If there is no file in the system, return 0.

    // This is an interesting one for sure. After looking at it for a little bit I have an idea
    // My idea consists of breaking the strings down into it's lines, so we could splice on the lines
    // Then we can use the tab counts to determine if our current line is a child || same level || higher level
    // than the previous lines, ie if line 0 had 0 tabs, and line 1 had 1 tab, we can determine that 
    // line 1 is a child of line 0, then if line 2 had 1 tab as well, we can determine that it is not a child of line 1,
    // and that it is instead a child of line 0. This poses an interesting situation because I think we 
    // could use a stack to keep track of where we are since we know how many tabs there are
    // and a sub dir can't have two tabs without having two parents 
    // ie we can't have
    // dir
    // *tab**tab* subdir
    // So I think we should be able to walk through line by line, and all we should do is push
    // our line's string length onto the stack, then for the next line, we consider how many tabs we have
    // compared to the previous line, if we have less tabs (we're a higher node), then we pop off the difference in tabs + 1
    // off our stack to effectively back the stack up to the parent directory, then push our current value
    // if we have the same amount of tabs, we pop off one element because we can't be a child of it, and push our value
    // if we have more tabs it means we're a child and we can push our value onto the stack
    // we also have to keep in mind that if we don't find a file then that run is invalid, so our highest
    // value or count should only change when we come across a file
    // 
    // so like if we had 
    // 
    // dir
    //     subdir1
    //         file1.ext
    //         subsubdir1
    //     subdir2
    // at line [0] we push 3 onto the stack and the previous tab count becomes 0 because line [0] has 0 tabs
    // at line [1] we compare tabs and see that line [1] has 1 tab, 1 > 0, so we push 7 onto the stack, previous tabs become 1
    // at line [2] we compare tabs ands see that line [2] has 2 tabs, 2 > 1, so we push 9 onto the stack
    // we also notice that this line contains a file, so our new longest sub dir will be the value of the stack + # of items 
    // in the stack -1 (to account for slashes)
    // at line [3] we compare tabs are see that line [3] has 2 tabs, 2 == 2, so we pop 9 off the stack and push 10 on, previous tabs 
    // remains at 2
    // at line [4] we compare tabs and see that line [4] has 1 tab, 1 < 2, so we pop off (2-1) + 1  = 2 elements,
    // popping off 10 and 7, the stack contains only 3, and we push 7 onto it, then we hit the end of our lines array
    // so we're done and our best path was dir/subdir1/file1.ext
    // using this we could also print the string with minimal effort if we wanted to

    void solution(String Directories){
        Directories = Directories.replace("\\n", ">");
        System.out.println(Directories);
        String lines[] = Directories.split(">",0);
        Stack<Integer> words = new Stack<Integer>();
        int previousTabs = -1;
        int currentValue = 0;
        int highestValue = 0;
        int y = 0;

        for(String x: lines){
            int tempTabs = this.countTabs(x);
            System.out.println(tempTabs);
            // get our value for the stack
            x = x.replace("\\t","");
            int tempStringlen = (x.length());
            
            if(tempTabs > previousTabs){

                words.push(tempStringlen);
                currentValue += tempStringlen;

            }else if(tempTabs == previousTabs){
                // pop off old val and push new val
                if(currentValue > 0){
                    int temporary = words.pop();
                    currentValue -= temporary;
                }
                words.push(tempStringlen);
                currentValue += tempStringlen;
                
            }else{

                int temporary = (previousTabs - tempTabs) + 1;
                int t2 = 0;
                for(y = 0; y < temporary; y++){
                    t2 = words.pop();
                    currentValue -= t2;
                }

                words.push(tempStringlen);
                currentValue += tempStringlen;

            }

            previousTabs = tempTabs;

            if(x.contains(".") && (currentValue + words.size() - 1) >= highestValue){
                System.out.println(currentValue);
                System.out.println(words);
                highestValue = currentValue + words.size() - 1;
            }
            
        }
        System.out.println(highestValue);
    }

    int countTabs(String y){
        int x = 0;
        int tabs = 0;
        for(x = 1; x < y.length(); x++){
            if(y.charAt(x-1) == '\\' && y.charAt(x) == 't'){
                // if we find a tab we advance by two
                tabs++;
                x++;
            }else{
                // as soon as we hit something that isnt tabs we can return
                return tabs;
            }
        }
        return tabs;
    }
    
    // speed will be n*m where n is the number of words and n is the average length
    // space will be linear 

    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter your directory string");
        String dir = myScanner.nextLine();

        myScanner.close();

        May202021 test = new May202021();

        test.solution(dir);
    }


     
   
}
