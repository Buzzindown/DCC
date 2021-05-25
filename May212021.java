import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class May212021 {
    
    // Given an array of integers and a number k, where 1 <= k <= length of the array, 
    
    // compute the maximum values of each subarray of length k.

    // For example, given array = [10, 5, 2, 7, 8, 7] and k = 3, we should get: [10, 7, 8, 8], since:
    
    // 10 = max(10, 5, 2)
    // 7 = max(5, 2, 7)
    // 8 = max(2, 7, 8)
    // 8 = max(7, 8, 7)
    // Do this in O(n) time and O(k) space. You can modify the input array 
    
    // in-place and you do not need to store the results. You can simply print them out as you compute them.

    // This seems like a very straightforward question, iirc this is a "sliding window"
    // style of question, in which the window is the subarray and we have to shift or "slide"
    // it over the full arrau 

    // the goto solution is to just get the max of the k elements and shift along
    // in which if you had an array of size n, and the value was k, you would shift it n-k times,
    // and you would check k values each time
    // so it would be (n-k)*k, which seems bad, but if we think about it, the higher k is
    // the less shifting we need to do, just on more numbers so it's not exactly a n^2 relationmship

    // nonetheless it's not exactly optimal, so we know we need to look at this in chunks
    // of size k, we also know that we're only shifting by 1, and so the max 
    // of one array could very well be the max of the next array
    // let's look at the example [10,5,2,7,8] k = 3
    // maybe we can do some replacing, there's lots to keep track of here
    // like if we look at 10, that's our highest value for now
    // then we look at 5, that's not higher
    // then we look at 2, that's not higher so 10 wins for the first subarray
    // however we can't overwrite 5 with 10 because 10 doesn't
    // exist in the second subarray , but as soon as we get to 5, we can start looking at the 
    // next k elements and see if it's higher, at the same time the second element
    // of the subarray can overwrite every value in that array if it's higher 
    // because all of those elements would be in the next array
    // so for [10,5,2,7,8] k = 3, we need to keep track of a few things
    // we keep track of i and i + 1,
    // so first thing we say 10 is our highest value
    // then we say 5 is the leading highest value for the next array
    // 10 and 5 move together
    // first, 5 check's the next element which is 2, 5 is greater than 2 so we replace 2 with 5
    // we also move 10 over !! I think we can come get the array values after (maybe?) let's see where this goes
    // 10 is greater than 5 so it's still our choice for the first array, we can overwrite the 5 for now 
    // so we get [10,10,5,7,8] !! for the first index,  perhaps something we need to keep track of is when
    // these values are no longer good, ie 10 is only valid for the first k indexes, although maybe not
    // because we're covering up lower values
    // let's keep going
    // 5 checks it's against 7, 7 is higher so we leave 7
    // 10 scoots up and checks itself against 10, it is greater so it replaces 5, but no 10 must stop, and our next highest value is
    // 7, but 7 could have occured at the beginning of the second array, at which point it also can't continue
    // Maybe, everytime we find a new high we add k to a counter and subtract everytime we shift, the counter
    // will represent how long that number is valid for, then when it hits 0, we take the next value we find and repeat
    // we still have the issue of the next number possibly being over stretched by the front pointer
    // what if we did the counter approach with only one pointer, i'm still worried about a situation
    // where we cover up a max value for an array further down the line
    // suppose [10, 5, 2, 7, 8, 7] and k = 3
    // start with 10, 10 is a valid max until index 2
    // compare 10 with 5, 10 > 5, 5 gets replaced with 10
    // compare 10 with 2, 10 is > 2, replace 2 with 10, 10 is no longer valid
    // next vakue is 7, we don't compare 7 to itself, 7 is also now valid till index 5 (EoA)
    // compare 7 to 8, 7 < 8, so we choose 8, 8 is valid till inex 6 which is past the array
    // we don't compare 8 to itself, next we compare 8 to 7, 8 > 7, we replace 7 with 8
    // our final array looks like [10,10,10,7,8,8]
    // the last k values will represent our array
    // so we get 8, 8, 7, 10 which is correct, maybe this will work
    // but what if we had [10, 9, 2, 7, 8, 7] and k = 3
    // 10 > 9 so 9 get's covered up, but 9 is the highest value of the second array
    // which is wrong :(
    // The thing about these questions is that the array's only change by one element,
    // but the location of the max is what messes everything up, because it could exist
    // in up to k of the subarrays, or it could only exist in one 
    // I'm forgetting that we can store some data here too
    // perhaps this is similar to yesterday's problem
    // [10, 9, 2, 7, 8, 7] 
    //  right now we can say that our max is 10, then we say our second highest is 9, third highest is 2,
    //  we shift forward, 10 is 

    // After a few hours, I looked up the solution, and it seems the issue was not using 
    // the space I was given, the proper solution implements a deque
    // or a double ended queue. In which we store only indexes that we care about
    // then as we check a new subarray, we pop off any of the old elements that we no longer need
    // because they're still valid but smaller than the current element we're looking at


    void solution(Integer[] arr, int k){

        Deque<Integer> d = new LinkedList();

        // this is where we set up for our first sub array
        // we are collecting all of our valid values here
        // ie if we had [10,11,5]
        // 10 will get popped off in this section
        // because 11 exists in the same subarray and is higher value
        for(int i = 0; i < k; i++){
            if(d.size() > 0){
                while(arr[i] >= arr[d.peekLast()]){
                    d.pop();
                }
            }
            d.add(i);
        }

        // this section is where we do something similar
        // for the rest of the array
        
        for(int i = k; i < arr.length; i++){
            // first thing is to print off our previous subarray's chosen value
            // which will be the head of the deque
            System.out.println(arr[d.peekFirst()]);
            // then we start popping off any values that were invalidated 
            // by their index being out of the subbarray
            while(d.size() > 0 && d.peekFirst() <= i - k){
                d.pollFirst();
            }
            // next we pop off any values who are invalidated by simply 
            // being less than the current value, we can pop these off because
            // we know they exist in our subarray but our current value
            // will last longer than they will and it's a higher value
            while(d.size() > 0 && arr[i] >= arr[d.peekLast()]){
                d.pollLast();
            }
            // finally we push on our value
            d.add(i);
        }

        System.out.println(arr[d.peekFirst()]);
    }

    // this is a pretty cool solution after the fact, very clever use of a deque for this unique situation

    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter size of subarray");
        int k = Integer.parseInt(myScanner.nextLine());

        System.out.println("Enter number of elements");
        int numElems = Integer.parseInt(myScanner.nextLine());

        System.out.println("Enter your array of numbers line by line");

        Integer arr[] = new Integer[numElems];
        int x;

        for(x = 0; x < numElems; x++){
            arr[x] = Integer.parseInt(myScanner.nextLine());
        }

        myScanner.close();

        May212021 t = new May212021();
        t.solution(arr, k);

    }


}
