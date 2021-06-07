import java.util.Arrays;
import java.util.Scanner;

public class June72021 {
// Good morning! Here's your coding interview problem for today.

// This problem was asked by Google.

// Given an array of strictly the characters 'R', 'G', and 'B', segregate the 

// values of the array so that all the Rs come first, the Gs come second, and the Bs come last. 

// You can only swap elements of the array.

// Do this in linear time and in-place.

// For example, given the array ['G', 'B', 'R', 'R', 'B', 'R', 'G'], it should become 

// ['R', 'R', 'R', 'G', 'G', 'B', 'B'].

// this actually seems really straight forward, we can loop through the array 3 times,
// starting by swapping all R's to the front and incrementing the spot to swap them to,
// then we do all G's, then all B's it'll be in place and linear time

void solution(String[] arr){

    int i = 0;
    int rCounter = 0;
    int bCounter = 0;

    for(i = 0; i < arr.length; i++){
        System.out.println(arr[i]);
        if(arr[i].equals("R")){
            
            // swap and increment counter
            String temp = new String(arr[rCounter]);
            arr[rCounter] = "R";
            arr[i] = temp;
            rCounter++;
        }
    }

    bCounter = rCounter;
    for(i = bCounter; i < arr.length; i++){
        if(arr[i].equals("B")){
            // swap and increment counter
            String temp = new String(arr[bCounter]);
            arr[bCounter] = "B";
            arr[i] = temp;
            bCounter++;
        }
    }


    System.out.println(Arrays.toString(arr));
}

public static void main(String[] args){

    Scanner myScanner = new Scanner(System.in);

    System.out.println("Enter number of elements");
    int numElems = Integer.parseInt(myScanner.nextLine());

    System.out.println("Enter your arr line by line only using uppercase RGB");

    String arr[] = new String[numElems];
    int x;


    for(x = 0; x < numElems; x++){
        arr[x] = myScanner.nextLine();
    }

    myScanner.close();

    June72021 test = new June72021();

    test.solution(arr);
}
}
