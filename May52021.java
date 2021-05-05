import java.util.Scanner;
import java.util.Arrays;

public class May52021 {
        // Good morning! Here's your coding interview problem for today.

        // This problem was asked by Uber.
        
        // Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
        
        // For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
        
        // Follow-up: what if you can't use division?
    public static void main(String[] args){

        // grab all our data

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter number of elements");
        int numElems = Integer.parseInt(myScanner.nextLine());

        System.out.println("Enter your array of numbers line by line");

        int arr[] = new int[numElems];
        int solArr[] = new int[numElems];
        int x;
        int y;
        int z;


        for(x = 0; x < numElems; x++){
            arr[x] = Integer.parseInt(myScanner.nextLine());
        }

        myScanner.close();

        // sooooo It looks like the naive sol will be 
        // just looping a bunch of times and skipping the current element
        // let's try that

        // first solution

        // for(x = 0; x < numElems; x++){
        //     z = 1;
        //     for(y = 0; y < numElems; y++){
        //         if(y != x){
        //             z *= arr[y];
        //         }
        //     }
        //     solArr[x] = z;
        // }

        // time complexity will be n^2
        // space complexity will be n

        // now for a better solution what could we do...
        // probably something mathy
        // The follow-up kinda gave me a hint to this, we could take the product of the array at the start
        // then we could step through the array and divide the product by the current element and copy it to the new arr
        // should be linear time complexity lets try it 

        // int product = 1;

        // for(x = 0; x < numElems; x++){
        //     product *= arr[x];
        // }

        // for(x = 0; x < numElems; x++){
        //     solArr[x] = product/arr[x];
        // }

        // much nicer solution
        // time complexity will be n
        // space complexity will be n

        // now how do we do this without dividing
        // There's probably a clever mathy way to do this
        // suppose we had 3, 4, 5 how can we do this in one pass
        // we can keep track of the product by looping and storing a new array but that doesn't help w/ the no division
        // if we offset the array by 1 element front to back and back to front (maintaining the same size and excluding 1 element)
        // we can make two arrays in opposite directions and find their products
        // this will avoid multiplying the current element by itself

        // if we had 3, 4, 5 
        // we could make an array which is the product of all the previous elements
        // {1, 1*3, 1*3*4} then we can do the same thing backwards
        // {4*5*1,5*1, 1} if we multiply the elements
        // {4*5,3*5,3*4} this should work

        int product = 1;
        int forwardArr[] = new int[numElems];
        int backwardArr[] = new int[numElems];

        for(x = 0; x < numElems; x++){
            forwardArr[x] = 1;
            backwardArr[x] = 1;
        }
        // build first arr
        for(x = 1; x < numElems; x++){
            forwardArr[x] *= arr[x-1] * product;
            product *= arr[x-1];
        }
        //build second arr
        product = 1;
        for(x = numElems-2; x >= 0; x--){
            backwardArr[x] = arr[x+1] * product;
            product *= arr[x+1];
        }

        for(x = 0; x < numElems; x++){
            solArr[x] = forwardArr[x] * backwardArr[x];
        }

        // time complexity is n
        // space complexity is n
        // much harder to consider without division
            
        System.out.println("Original Array\n" + Arrays.toString(arr));
        System.out.println("Solved Array\n" + Arrays.toString(solArr));
    }
}