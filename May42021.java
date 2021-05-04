import java.util.Scanner;
import java.util.HashSet;

public class May42021 {
        // Good morning! Here's your coding interview problem for today.

        // This problem was recently asked by Google.

        // Given a list of numbers and a number k, return whether any two numbers from the list add up to k.

        // For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.

        // Bonus: Can you do this in one pass?
    public static void main(String[] args){

        // grab all our data

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter number to sum to");
        int sum = Integer.parseInt(myScanner.nextLine());

        System.out.println("Enter number of elements");
        int numElems = Integer.parseInt(myScanner.nextLine());

        System.out.println("Enter your array of numbers line by line");

        int arr[] = new int[numElems];
        int x;
        int y;


        for(x = 0; x < numElems; x++){
            arr[x] = Integer.parseInt(myScanner.nextLine());
        }

        myScanner.close();

        // first solution
        // time complexity will be n^2
        // Space complexity will be n

        // for(x = 0; x < numElems; x++){
        //     for(y = x; y < numElems; y++){
        //         if(arr[x] + arr[y] == sum){
        //             System.out.println("Found a sum -> " + arr[x] + " + " + arr[y] + " = " + sum);
        //             return;
        //         }
        //     }
        // }

        // Better solution
        // we can build a set that contains the difference between our curr
        // val and the sum so we can look for the counterpart of the part
        // then as we build the set we can check if a counterpart is contained
        HashSet<Integer> map = new HashSet<Integer>();
        for(x = 0; x < numElems; x++){
            // get our difference
            y = sum-arr[x];
            if(map.contains(y)){
                System.out.println("Found a sum -> " + y + " + " + (sum-y) + " = " + sum);
                return;
            }
            // if we can't find a counterpart we just add
            map.add(arr[x]);
        }
        System.out.println("Match not found");
    }
}