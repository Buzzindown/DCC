import java.util.Arrays;
import java.util.Scanner;

public class June32021 {
// Good morning! Here's your coding interview problem for today.

// This problem was asked by Google.

// The edit distance between two strings refers to the minimum number of character 

// insertions, deletions, and substitutions required to change one string to the other.

// For example, the edit distance between “kitten” and “sitting” is three: substitute the “k” for “s”, 

// substitute the “e” for “i”, and append a “g”.

// Given two strings, compute the edit distance between them.

// so we have 3 actions which are insert, delete, and substitute
// and we want to take one word amd transform it into the other word
// in the minimal amount of moves, surely there's a logical way 
// to apply these moves

// maybe we go through and we see which character's are lined up,
// if there's any. From there we can substitue the mismatched ones and 
// delete any excess ones. Although what if we come across a situation
// where it's easier to just delete the first element than to 
// replace all the other elements and delete the end :(

// maybe we shift along and see how many chars we can get to line up
// the most chars is the shift we keep, even then we're alredy doing
// n^2 comparisons which is badddd

// this is kind of tough because we're able to find an answer
// but how do we know it's the minimal amount of moves

// deletion costs the same as substitution, but deletion
// could shift over an entire array and save us lots of substitution
// like maybe we have elevator --> selevato
// by our ,ethod we go 0 sub, 1 sub, 2 sub, 3 sub ... sub the whole array
// whereas if we just deleted s and appended r we did it in 2 moves

// perhaps shifting around is our best move, even though it's slow as all be
// when we shift the number of moves will just be a matter of deletions 
// to get that shift so we'll take the #of matched characters - 
// (length of og string - length of cur string) 
// to evaluate if the shift is really better, ccause if we shift 
// and match 4 characters but have to delete half the string that's not great,
// whereas maybe we only match 3 characters but we only delete 1 character

// same goes for inserting in the middle of an array tho, i'm probably
// overthinking this

// their
// aire

// the thing is that delete may require an insertion as well which is a dumb thing to do
// cause we could have just substituted

// ideal move is delete the t, h, replace e with a, insert e at the end that's 4 moves
// replace t with a, replace h with i, replace e with r, replace i with e, delete r that's 5 moves

// maybe we look for letters that match and store them somewhere
// ie their doesn't contain a, but it contains i and r, as well as e, but we don't know about their indexes
// so maybe we store the highest index that we can't go before

// ie check indexOf(a), bad .. indexOf(i) = 3, indexOf(r) = 4, indexOf(e) < 4 so it's not gonna do anything
// for us because we would have to delete/insert or sub

// how the heck, dp solution below, turns out we do have to calculate every single case

int solution(String a, String b){
    int x = a.length();
    int y = b.length();

    int[][] arr = new int[y][x];

    int i;
    int j;

    for(i = 0; i < x; i++){
        for(j = 0; j < y; j++){
            arr[j][i] = -1;
        }
    }

    for(i = 0; i < x; i++){
        arr[0][i] = i;
    }

    for(j = 0; j < y; j++){
        arr[j][0] = j;
    }

    for(i = 1; i < y; i++){
        for(j = 1; j < x; j++){
            if(a.charAt(j-1) == b.charAt(i-1)){
                arr[i][j] = arr[i-1][j-1];
            }else{
                arr[i][j] = Math.min(arr[i-1][j] + 1,arr[i][j-1] + 1);
                arr[i][j] = Math.min(arr[i][j], arr[i-1][j-1] + 1);
            }
        }
    }
    
    // for(i = 0; i < x; i++){
    //     for(j = 0; j < y; j++){
    //         System.out.print(" " + arr[j][i] + " ");
    //     }
    //     System.out.println();
    // }
    return arr[y-1][x-1];
}

public static void main(String[] args){

    Scanner myScanner = new Scanner(System.in);

    System.out.println("Enter String A");
    String a = myScanner.nextLine();

    System.out.println("Enter String B");

    String b = myScanner.nextLine();

    June32021 test = new June32021();

    System.out.println("Edit distance: " + test.solution(a, b));
}

}
