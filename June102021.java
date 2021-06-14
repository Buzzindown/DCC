
import java.util.ArrayList;

public class June102021 {
    // This problem was asked by Microsoft.

    // You have an N by N board. Write a function that, given N, returns the number of 
    
    // possible arrangements of the board where N queens can be placed on the board without 
    
    // threatening each other, i.e. no two queens share the same row, column, or diagonal.

    // n queens problem, tough one 
    // there's a lot of possible solutions so this will probably be some sort 
    // of recursive solution, couldn't figure this one out for the life of me,
    // this solution us elegant and utilizes a 1d array, because we know 
    // we can only have one queen per row, hence the index of the array is representive
    // of a column, and the value is representive of a row

    int n_queens(int n, ArrayList<Integer> board){
        System.out.println(board.toString());

        if(n == board.size()){
            return 1;
        }

        int count = 0;

        for(int i = 0; i < n; i++){
            // make sure we haven't included it
            if(!board.contains(i)){
                board.add(i);
                System.out.println("!! " + i);
                if(isValid(board)){
                    count += n_queens(n,board);
                }
                board.remove(board.size()-1);
            }
        }

        return count;
    }

    Boolean isValid(ArrayList<Integer> colQ){
   // we can guarantee that we dont have the same columns 
   // because we've alreay checked that before adding, 
   // we can also guarantee we don't have the same rows 
   // because rows are index based and we're adding a new 
   // index each time
   
    int curcol = colQ.size() - 1;
    int row = colQ.get(colQ.size() - 1);

    int i = 0;
    int j = 0;
    for(i = curcol-1, j = 1; i >= 0; i--, j++)
    {
        // check if we have two of the same row || check diags
        if(row == colQ.get(i) || row+j == colQ.get(i) || row-j == colQ.get(i)) return false;
    }
    return true;
   
    }

    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<>();

        June102021 call = new June102021();

        System.out.println("Answer: " + call.n_queens(10, test));

    }
    
}
