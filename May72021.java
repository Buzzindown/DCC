import java.util.Scanner;

public class May72021 {
    // Good morning! Here's your coding interview problem for today.

    // This problem was asked by Stripe.
    
    // Given an array of integers, find the first missing positive integer in linear time and constant space. In other words, find the lowest positive integer that does not exist in the array. The array can contain duplicates and negative numbers as well.
    
    // For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.
    
    // You can modify the input array in-place.

    // needs to be: linear time, constant space. Ie we need to do it in-place no extern arrays
    // we can shift elements around in the array

    // How to go about this is a tough one
    // In an array of size n of the form {1,2,3....,N} 
    // There has to be a missing element somewhere between 1 and N
    // we don't need to concern ourselves with number less than 1 or greater than N,
    // so we can set these to some placeholder value
    // Also, if the array doesn't contain 1 then 1 is the obvious missing element,
    // we can check this independently because the smallest size array {1}
    // must contain 1, and if it doesn't then 1 is the missing element

    // on the other end of the range, the highest value element we can have
    // is the size of the array + 1, in an array with {1,2,3,....,N}
    // the next element would be N + 1
    // anything Greater than N + 1 or less than 1 are insignificant and can be ignored

    // Now what do we do with these properties, we've essentially
    // built an array with elements between 1 and N
    // maybe we end up with something like {-3,2,1,5,3,16,0}
    // highest possible element is n = 7
    // i have an idea, we need to mark all the bad elements with a 1 
    // so we get something like { 1, 2, 1, 5, 3, 1, 1}
    // Big brain idea, we can consider the array as a range where
    // each index is a range then as we come across elements
    // we can mark the index of the array that that element represents
    // (since they'll all be between 1 and N)
    // and we'll increase them by N, then if say we come across the element 3
    // before we come across the actual index 3, we'll acess each value with mod N
    // so that we can find the original value incase we already marked it.
    // Then, all the indexes that had corresponding elements in the original
    // array will be "marked" and will also be > N so the first unmarked 
    // index will be the missing value

    // since everything is in place space will be constant
    // and since we're only using seperate loops time will be linear
    

    public int[] sort(int[] arr, int left, int right){
        return arr;
    }

    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter number of elements");
        int numElems = Integer.parseInt(myScanner.nextLine());

        System.out.println("Enter your array of numbers line by line");

        int arr[] = new int[numElems];
        int x;
        int y = 0;
        int z = Integer.MAX_VALUE;


        for(x = 0; x < numElems; x++){
            arr[x] = Integer.parseInt(myScanner.nextLine());
        }

        myScanner.close();

        int found = 0;
        // check for 1
        for(x = 0; x < numElems; x++){
            if(arr[x] == 1){
                found = 1;
            }
        }

        if(found == 0){
            System.out.println("Missing value is 1");
            return;
        }

        // get rid of bad values and change to 1
        for(x = 0; x < numElems; x++){
            if(arr[x] < 1 || arr[x] > numElems){
                arr[x] = 1;
            }
        }

        // mark values
        for(x = 0; x < numElems; x++){
            arr[(arr[x]-1) % numElems] += numElems;
        }

        // search for missing val
        for(x = 0; x < numElems; x++){
            if(arr[x] <= numElems){
                System.out.println("Missing value is " + (x + 1));
                return;
            }
        }

      
    }
}
