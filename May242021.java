import java.util.LinkedList;
import java.util.Scanner;

public class May242021 {
    
    //  Good morning! Here's your coding interview problem for today.

    // This problem was asked by Google.

    // Given two singly linked lists that intersect at some point, find the intersecting node. The lists are non-cyclical.

    // For example, given A = 3 -> 7 -> 8 -> 10 and B = 99 -> 1 -> 8 -> 10, return the node with value 8.

    // In this example, assume nodes with the same value are the exact same node objects.

    // Do this in O(M + N) time (where M and N are the lengths of the lists) and constant space.

    // this seems fairly easy,
    // however this would depend on some specifics, based on the example, I will assume
    // that wherever the lists intersect, their intersection is the remaining length of both lists
    // ie 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
    //              3 -> 4 -> 5 -> 6 -> 7
    // although if this wasn't the case we would run into scenarios where
    // one list might continue or we might have repeated elements on some other weird edge cases
    // but for now we'll rock with the example and say that if we stroll across two nodes
    // that are of the same value, it means there's an intersection and that intersection lasts 
    // the remaining length of both lists (although that doesn't necessarily have to be true I 
    // suppose because once we find a common element, we could just keep going until they're
    // not common, however the question just asks for the first common node)
    // my initial thought is that since we can assume the intersecting part 
    // is the remainder of the list, if the lists are of two different sizes,
    // we cam trim the longer list to be the same size as the smaller list by skipping
    // the first len(a) - len(b) item. Then we can compare side by side

    void solution(LinkedList a, LinkedList b){
        int diff = Math.abs(a.size() - b.size());

        if(a.size() > b.size()){
            for(int i = 0; i < a.size() - diff; i++){
                if(a.get(i + diff) == b.get(i)){
                    System.out.println("Intercepts at " + b.get(i));
                    return;
                }
            }
        }else{
            for(int i = 0; i < b.size() - diff; i++){
                if(a.get(i) == b.get(i + diff)){
                    System.out.println("Intercepts at " + a.get(i));
                    return;
                }
            }
        }

    }

    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter size of List 1");
        int l1 = Integer.parseInt(myScanner.nextLine());

        LinkedList<Integer> arr = new LinkedList<>();
        int x;

        System.out.println("Enter List 1 line by line");

        for(x = 0; x < l1; x++){
            arr.add(Integer.parseInt(myScanner.nextLine()));
        }

        System.out.println("Enter size of List 2");
        int l2 = Integer.parseInt(myScanner.nextLine());

        System.out.println("Enter List 2 line by line");
        LinkedList<Integer> arr2 = new LinkedList<>();

        for(x = 0; x < l2; x++){
            arr2.add(Integer.parseInt(myScanner.nextLine()));
        }

        
        myScanner.close();

        May242021 t = new May242021();
        t.solution(arr, arr2);

    }
}
