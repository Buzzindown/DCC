import java.util.Scanner;

public class June22021 {

    // You are given an array of non-negative integers that represents a two-dimensional 
    
    // elevation map where each element is unit-width wall and the integer is the height. 
    
    // Suppose it will rain and all spots between two walls get filled up.

    // Compute how many units of water remain trapped on the map in O(N) time and O(1) space.
    
    // For example, given the input [2, 1, 2], we can hold 1 unit of water in the middle.
    
    // Given the input [3, 0, 1, 3, 0, 5], we can hold 3 units in the first index, 2 in the second, 
    
    // and 3 in the fourth index (we cannot hold 5 since it would run off to the left), 
    
    // so we can trap 8 units of water. 

    //Intersting problem, pretty much need to do it in one loop + no extra space
    // we can start off with our literal edge cases where say we had [3,5,2,...]
    // we can't hold any water in index 0 because there's nothing to hold it to theleft
    // so we kind of need to consider the problem as two peaks and keep track of how much
    // area we can fill between the two peaks
    //we can start with 0 and 1, and shift them over until i + 1 < i, so that we can find
    // our first peak, then we should store the peaks height for calculating how much
    // water we can fill in the rest of the space, then we just move our right peak over
    // until we find a new peak, then we repeat

    // so for [3, 0, 1, 3, 0, 5, 4, 4, 6, 3]
    // we mark 0 and 1, check if 1 < 0, it is so we've found a peak
    // our current peak height becomes 3, and we can add 3-[1] to our area,
    // which is 3-0, so current area is 3-0.
    // move the right post over to [2], check if 2 < 0, it is
    // so we add 3-[2] to our area which is 3-1, area is now 5
    // then we move our post over again we check if 3 < 0, it is not
    // so we move 0 to 3 and the posts are back together, we store the height as 3
    // then we move our right post over and check if 4 < 3, it is so
    // we add 3 - [4] which is 3-0 to our area, area is now 8,
    // them we me move our right post to [5], check if if 5 < 3 it's not
    // so we move both posts to 5
    // this seems like it will work

    // this is good but it doesnt cover the case where we have 
    // [3,5,3,2,3,2]
    // we would miss the puddle that goes between 3 and 2

    // well that's not gonna work, how should we do this
    // what if we did something similar from the right side and found 
    // some difference in the area

    // for the example above, if we started at 2, we would go to 3, then to 2
    // 2 is less than 3 so we mve the left pole amd add tp tje area whih is 1
    // then we check 3 < 3 which is false so we move the right pole to the 3,
    // then we check 3 and 5, move both poles to 5, then over to 3 and we get 1
    // if we went from the left we would get 0 

    // what about something like this
    // 

    // what if instead we found a common peak for left/right peaked pools this makes it easier for sure
    // if we find the peak of the whole map then we're basically filling up 
    // the space to the left and right of it 
    // we can keep track of our highest point on the left since we KNOW that we're
    // peaked to the right, very similar to what I've done already
    // then we just take a difference between whatever the edge of our current pool is
    // minus our current elemenet
    // then we pretty well do the same thing from the other side

    // [4,6,5,12,4,2,3,5,2]
    // here let's say we find the max for the arr, which is 12
    // we do the left side first
    // start with [0] and [1], 4-6 = -2, and 6 > 4 so 6 is our new left edge
    // 6-5 = 1, 6 < 5

    int solution(int[] map){

        System.out.println("Solution");
        int lPost = 0;
        int rPost = 0;
        int area = 0;
        boolean done = false;
        int pool = 0;

        while(done == false){
            
            if(lPost == rPost){
                // if we put them together than shift them
                rPost++;
            }

         //   System.out.println("Lpost " + lPost + " Rpost " + rPost);
            // [7,8,4,2,3,5,2]
            // if we're in a hole
            if(map[rPost] < map[lPost]){
                // height of the bottom of a hole
                area += map[lPost] - map[rPost];
                pool += map[lPost] - map[rPost];
                rPost++;

            }else{
                // if we're not in a hole, we can shift post the posts
                // left post was the highest we go to, so we know we can go anywehere
                // between  that and our current r post and still have a poo;
                lPost = rPost;
                pool = 0;
            }
            // reached the end
            if(rPost >= map.length - 1){
                // check if we end on a high edge
                if(rPost < map.length){
                    if(map[rPost] >= map[lPost]){
                        pool = 0;
                    }
                }
                done = true;
            }
        }

        // if there was any water left over we remove it
        area = area - pool;

        return area;
    }
    
    int properSolution(int[] arr){

        int arrMax = Integer.MIN_VALUE;
        int maxIndex = 0;
        int area = 0;

        // get our central peak
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > arrMax){
                arrMax = arr[i];
                maxIndex = i;
            }
        }

        int lMax = arr[0];
        // do our left side 
        for(int i = 1; i < maxIndex; i++){
            if(arr[i] <= lMax){
                area += lMax - arr[i];
            }
            if(arr[i] > lMax){
                lMax = arr[i];
            }
        }

        int rMax = arr[arr.length - 1];
        // right side 
        for(int i = arr.length - 2; i > maxIndex; i--){
           // System.out.println("Max " + rMax + " verse " + arr[i]);
            if(arr[i] <= rMax){
                area += rMax - arr[i];
            }
            if(arr[i] > rMax){
                rMax = arr[i];
            }
        }

        return area;
    }
    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter number of elements");
        int numElems = Integer.parseInt(myScanner.nextLine());

        System.out.println("Enter your array of numbers line by line");

        int arr[] = new int[numElems];
        int x;

        for(x = 0; x < numElems; x++){
            arr[x] = Integer.parseInt(myScanner.nextLine());
        }

        myScanner.close();

        June22021 t = new June22021();
        System.out.println("Area " + t.properSolution(arr));

    }
}
