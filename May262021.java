import java.util.Arrays;

public class May262021 {
    // Good morning! Here's your coding interview problem for today.

    // This problem was asked by Google.
    
    // You are given an M by N matrix consisting of booleans that represents a board. 
    
    // Each True boolean represents a wall. Each False boolean represents a tile you can walk on.
    
    // Given this matrix, a start coordinate, and an end coordinate, return the minimum number of 
    
    // steps required to reach the end coordinate from the start. If there is no possible path,
    
    // then return null. You can move up, left, down, and right. You cannot move through walls. 
    
    // You cannot wrap around the edges of the board.
    
    // For example, given the following board:
    
    // [[f, f, f, f],
    // [t, t, f, t],
    // [f, f, f, f],
    // [f, f, f, f]]
    // and start = (3, 0) (bottom left) and end = (0, 0) (top left), the minimum number 
    
    // of steps required to reach the end is 7, since we would need to go through (1, 2) 
    
    // because there is a wall everywhere else on the second row.

    // seems like we can do this in a dynamic programming way using memoization
    // we can store an array of same size/shape of integers which we will use 
    // to map the space, then we can also store our values
    // when we cover a spot we mark it with the number of spaces from our original spot
    // then we recursively dive in all directions
    // until we find the spot, and when we do find the spot, we retun the value and see if its
    // smaller than any other paths

    int minPath = Integer.MAX_VALUE;
    int desX = 0;
    int desY = 0;
    int startX = 3;
    int startY = 0;
    int xlen = 4;
    int ylen = 4;

    Boolean array[][] = new Boolean[][]{
                        new Boolean[] {false, false, false, false}, 
                        new Boolean[] {true, true, false, true}, 
                        new Boolean[] {false,  false, false, false},
                        new Boolean[] {false, false, false, false}};
    int map[][] = new int[xlen][ylen];
    
    May262021() {
        for(int[] row: map){
            Arrays.fill(row,Integer.MAX_VALUE);
        }
    }

    void solution(int x, int y, int distance){

        // for(int[] row: map){
        //     System.out.println(Arrays.toString(row));
        // }
        // System.out.println("");
        // mark our current spot on th map
        // if it's unmarked
        if(distance < map[x][y] && array[x][y] == false){     
            if(x == desX && y == desY){
                if(distance < minPath){
                    minPath = distance;
                    // found the dest now we're done
                }
                return;
            }
            map[x][y] = distance;

            // within lower bounds
            if(x > 0){
                solution(x - 1, y, distance + 1);
            }
            if(x < xlen - 1){
                solution(x + 1, y, distance + 1);
            }
            if(y > 0){
                solution(x, y - 1, distance + 1);
            }
            if(y < ylen - 1){
                solution(x, y + 1, distance + 1);
            }
        }
        // if we haven't found the dest then we need to look for it
    }

    // pretty ugly brute force solution, maybe we can try implmenting something 
    // cool like djikstra or A* in the near future

    public static void main(String[] args){

        May262021 test = new May262021();

        test.solution(test.startX, test.startY, 0);

        System.out.println(test.minPath);

        for(int[] row: test.map){
            System.out.println(Arrays.toString(row));
        }
    }
    


}
