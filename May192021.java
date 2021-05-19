import java.util.Scanner;
import java.util.HashMap;

public class May192021 {
    
    // This problem was asked by Twitter.
    
    // You run an e-commerce website and want to record the last N order ids in a log. 
    
    // Implement a data structure to accomplish this, with the following API:
    
    // record(order_id): adds the order_id to the log get_last(i): gets the ith last element from the log. 
    
    // i is guaranteed to be smaller than or equal to N. You should be as efficient with time and space as possible.

    // this question is clearly just asking us to select the right data structure for the job
    // There's many things we could use, let's see what we need
    // dynamic storage: have to be able to add orders into the log whenever and 
    // we don't want to use a extra large fixed size because that's wasteful of memory
    // a quick insertion
    // a quick indexed retrieval
    // need to be able to maintain the order of the id's 

    // My first thought is a linked list, it's dynamic, insertion is O(1), however 
    // searching and retrieving is O(n) which isn't great. It does maintain insertion
    // order which is nice and a Linked list would be very simple
    // to implement however I think we can do better with the search

    // Let's consider other ds', We could try and do something like binary search tree
    // and just have a keyval pair for each node where the order of insertion is the key
    // that's still not great though because insertion can still require O(h) where
    // h is the heigh of the tree, and possibly O(n) and same with searching 

    // we can do better with a self balancing bst or an AVL, this would be much
    // tougher to implement than a linked list but it would give us O(logn) 
    // retrieval and insertion which is a better balance than the linked list

    // my final though would be to use a Hashmap, specifically the built in java one
    // we can use the insertion order as the key and the id as the value, then we 
    // can just keep track of how many insertions there are to get the ith last 
    // order. I like this because Most of the time hashmap is O(1) for insertion/retrieval
    // since jdk (8 ?), hashmap buckets have been converted from lists to tree's
    // so in the case we have a horrendous hash function and get all the collisions, 
    // we still get O(logn) retrieval/insertion, and the rest of the time we're doing O(1)
    // I believe space will be O(n) for pretty well all the data structures  we could choose

    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter number of elements");
        int numElems = Integer.parseInt(myScanner.nextLine());

        System.out.println("Enter your *stream* line by line");

        Integer arr[] = new Integer[numElems];
        int x;

        for(x = 0; x < numElems; x++){
            arr[x] = Integer.parseInt(myScanner.nextLine());
        }

        myScanner.close();

        log records = new log();

        for (Integer i : arr) {
            records.record(i);
        }

        System.out.println(records.retrieve(5));
    }


}

class log{
    int numOrders;
    HashMap<Integer,Integer> log;

    log(){
        numOrders = 0;
        log = new HashMap<>();
    }

    void record(Integer orderID){
        // insert into hashmap
        log.put(numOrders,orderID);
        numOrders++;
    }

    int retrieve(Integer ith){
        int id = log.get(numOrders-ith);
        return id;
    }

}