import java.util.Scanner;

public class June92021 {
    // Good morning! Here's your coding interview problem for today.
    
    // This problem was asked by Google.
    
    // The power set of a set is the set of all its subsets. Write a function 
    
    // that, given a set, generates its power set.
    
    // For example, given the set {1, 2, 3}, it should return {
        
    // {}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}}.
    
    // You may also use a list or array to represent a set.  

    // well, we know that the side is 2^n where n is the number of elements,
    // so perhaps we could make an array and use dp with memoization,
    // ie [1][2][3]
    //    [1,2][2.3
    //      [1,2,3]
    

    void solution(Integer[] arr){
        double size = Math.pow(2, arr.length);
        int realSize = (int)size;

        for(int i = 0; i < realSize; i++){
            for(int j = 0; j < realSize; j++){
                if((i & (1 << j)) > 0){
                    System.out.print (arr[j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);
    
        System.out.println("Enter number of elements");
        int numElems = Integer.parseInt(myScanner.nextLine());
    
        System.out.println("Enter your numbers line by line");
    
        Integer arr[] = new Integer[numElems];
        int x;
    
    
        for(x = 0; x < numElems; x++){
            arr[x] = Integer.parseInt(myScanner.nextLine());
        }
    
        myScanner.close();
    
        June92021 test = new June92021();
    
        test.solution(arr);
    }
}
