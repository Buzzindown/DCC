import java.util.LinkedList;

public class June42021 {
    // This problem was asked by Jane Street.

    // Suppose you are given a table of currency exchange rates, represented as a 2D array. 
    
    // Determine whether there is a possible arbitrage: that is, whether there is some sequence 
    
    // of trades you can make, starting with some amount A of any currency, so that you can end 
    
    // up with some amount greater than A of that currency.
    
    // There are no transaction costs and you can trade fractional quantities.

    // perhaps we can treat our table as an adjacency matrix with weighted edges
    // ie 0     1     2
    //  0 1.0  0.8    2
    //  1 1.2  1.0    0.7
    //  2 0.5  1.3    1.0 

    // we can start at any of our nodes and then traverse to any connected edges, 
    // we just can't go back the way we came and we want to end up back at our starting node

    // what if we used a LL to keep track of where we've been then we can ensure we don't loop
    // around to an element we've already crossed

    // void solution(LinkedList<Integer> previous, int thisCurrency, int startCurrency, int product){
    //     // check if we've traversed a cycle
    //     if(thisCurrency == startCurrency){
    //         if(product > 0){
    //             System.out.println("Arbitrage");
    //         }
    //         return;
    //     }
    //     // otherwise we want to keep searching
    //     for(int a = 0; a < matrixX; a++){
    //         LinkedList<Integer> tesst = (LinkedList) previous.clone();
    //         tesst.add(e)
    //         if(!previous.contains(a)){
    //             tesst.add(arr[thisCurrency][a])
    //             solution(tesst, a, startCurrency, product * arr[thisCurrency][a]);
    //         }
    //     }

    // took some time and couldn't come up with anything, full solution
    // uses a bellman-ford cycle algorithm to detect a negative cycle

        
}
