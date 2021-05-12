import java.util.Scanner;

public class May122021 {
    // Given a list of integers, write a function that returns the 
    
    // largest sum of non-adjacent numbers. Numbers can be 0 or negative.

    // For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] 
    
    // should return 10, since we pick 5 and 5.

    // Follow-up: Can you do this in O(N) time and constant space?

    // looks like the optimal solution will be in place, however
    // unfotunately I thought the question read the largest non-adjacent pair
    // and wasted a bunch of time, now to restart. 
    // random idea, what if we assigned each element avalue based on it's
    // positive increase in the sum, minus the potential positive sum that
    // the two adjacent values could have had. I'm not sure if this is helpful,
    // but it just crossed my mind, from there we choose the highest value non adjacent 
    // elements
    // for [2,4,6,2,5] we would get a value array like
    //     [-2,-4,0,-9,3] the highest non adjacent values would be 3, 0 , -2 which corresponds to 5, 6, 2 = 13
    // maybe this is good.... how do we find the best non adjacent values tho, maybe we filter it again? 
    // no I dont think that would do any good. what if we start at the highest. Let's see if it works for another example
    // [5,1,1,5] give us
    // [4,-5,-5,4] which is good the top 2 values are what we want but how do we know to select them.. 
    // maybe this isn't the way.. finding out which of those values to take or keep 
    // if we do that, we need to maximize the array again, which hasn't really done anything for us,
    // considering that's what we were trying to do in the first place
    // what if we tried a more dynamic route and we stored some data to help us out
    // looking more into the problem we don't need the whole array to start working out we just need
    // a continuing portion of the array
    // let's say we had 2,4,6,2,5,
    // we could start with just 2, and say for now we'll take it,
    // then we look at 4 and we either take 4 or we take 2, for now ... we take 4
    // because that's the higher value, then we get to 6 and we see that we can either have
    // 6 + 2 or we can keep the 4 we took, so we choose 6 + 2 of course, 
    // then we look at 2, and we could either have 2 + 4 or we could keep our 6
    // it's all the same so we take the 2 + 4, then we get to look at 5
    // and we could either have 5 + 6 or we could have 2, we want the 5 + 6.
    // so we really only care about part of the array and don't need access to the full thing
    // we should store the values of our previous selections in array for us to maximize this
    // like for this example we choose 2,
    // we store 2 in an array, we get to 4, well we're gonna chosoe 4 over 2 so our current value
    // is 4. then when we get to 6, we decide to select 6 and 2, so we unselect 4 and our current value becomes 8

    int solution(int[] arr){
        int values[] = new int[arr.length];
        int x = 0;

        for(x = 0; x < arr.length; x++){
            values[x] = 0;
        }

        if(arr.length == 1){
            return arr[0];
        }

        if(arr.length == 2){
            if(arr[0] >= arr[1]){
                return arr[0];
            }else{
                return arr[1];
            }
        }

        values[0] = arr[0];
        if(arr[0] >= arr[1]){
            values[1] = arr[0];
        }else{
            values[1] = arr[1];
        }

        for(x = 2; x < arr.length; x++){
            // do not take the current element
            int temp1 = values[x-1];
            // take the current element and drop the previous one
            int temp2 = values[x-2] + arr[x];
            if(temp1 >= temp2){
                values[x] = temp1;
            }else{
                values[x] = temp2;
            }
        }

        return values[values.length - 1];
    }

    // this will be in linear time

    // this eems good now how do we do it in constant space .... 

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

        May122021 t = new May122021();
        System.out.println(t.solution(arr));

    }
}
