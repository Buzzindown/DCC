import java.util.ArrayList;

public class June142021 {
    // This problem was asked by Facebook.

    // Given an unordered list of flights taken by someone, each represented as (origin, destination) pairs, 
    
    // and a starting airport, compute the person's itinerary. If no such itinerary exists, return null. 
    
    // If there are multiple possible itineraries, return the lexicographically smallest one. All flights must be used in the itinerary.
    
    // For example, given the list of flights [('SFO', 'HKO'), ('YYZ', 'SFO'), ('YUL', 'YYZ'), ('HKO', 'ORD')] 
    
    // and starting airport 'YUL', you should return the list ['YUL', 'YYZ', 'SFO', 'HKO', 'ORD'].
    
    // Given the list of flights [('SFO', 'COM'), ('COM', 'YYZ')] and starting airport 'COM', 
    
    // you should return null.
    
    // Given the list of flights [('A', 'B'), ('A', 'C'), ('B', 'C'), ('C', 'A')] and starting airport 'A', 
    
    // you should return the list ['A', 'B', 'C', 'A', 'C'] even though ['A', 'C', 'A', 'B', 'C'] is also a valid itinerary. 
    
    // However, the first one is lexicographically smaller.

    // so all flights need to be used, and we don't need to make it a loop or anything like that, 
    // so perhaps this will be some sort of recursive solution because we can build at any point off of our previous
    // answer and assign a new addition. OR we could iterate over the list until it's empty, and we'll 
    // select a choice that works and then if we find any other's that work we'll keep the lexicographically smallest one
    // where could we run into issues though, like if we chose a path and then it didn't work out
    // maybe we have ab, bc, cd, db, be
    // then we have an issue if we choose ab -> be because we've locked ourselves out 
    // so maybe we do go with recursion and then we can explore all of our possibilities
    // like we give each call a origin point (which we can do off the start) and we pass it our set of 
    // pairs, then we can test them out, sort of like the n queens problem from earlier
    ArrayList<flight> choice = new ArrayList<>();
    

    void solution(ArrayList<flight> list, ArrayList<flight> curSol, Character origin){
        if(list.size() == 1){
            // if the last element matches we have a good choice
            if(list.get(0).origin == origin){
                curSol.add(list.get(0));
                // if we haven't made a choice yet we can copy over to it
                if(this.choice.size() == 0){
                    for(int j = 0; j < curSol.size(); j++){
                        this.choice.add(curSol.get(j));
                    }
                }else{
                    // if we have made a choice, then we need to pick the
                    // better solution
                    for(int i = 0; i < curSol.size(); i++){
                        // if we find a spot where we're not lexicographically first we will return
                        if(curSol.get(i).origin >= this.choice.get(i).origin){
                            curSol.remove(curSol.size() - 1);
                            return;
                        }
                    }
                    // if we didn't return we should copy
                    for(int j = 0; j < curSol.size(); j++){
                        this.choice.set(j,curSol.get(j));
                     }

                }
                curSol.remove(curSol.size() - 1);
            }
            return;
        }
        // 
        for(int i = 0; i < list.size(); i++){
            // iterate over and see if we have one that matches, if we do we build with it
            flight temp = list.get(i);
            int location  = curSol.size();
            if(list.get(i).origin == origin){
                // take out the path we took
                list.remove(i);
                // add that thing we removed to our current solution
                curSol.add(temp);
                // recurse

                solution(list, curSol, temp.destination);
                // restore our lists
                curSol.remove(location);
                list.add(temp);
            }
        }
    }

    public static void main(String[] args) {
        
        June142021 test = new June142021();

        ArrayList<flight> test2 = new ArrayList<>();
        ArrayList<flight> test3 = new ArrayList<>();

        flight a = new flight('a','b');
        flight d = new flight('d', 'b');
        flight b = new flight('b','c');
        flight e = new flight('b', 'e');
        flight c = new flight('c','d');
        flight k = new flight('e', 'a');
        flight g = new flight('a', 'c');


        test2.add(a);
        test2.add(d);
        test2.add(k);
        test2.add(b);
        test2.add(e);
        test2.add(c);
        test2.add(g);

        test.solution(test2, test3, 'a');

        for(flight f : test.choice){
            System.out.println(f.origin + " --> " + f.destination);
        }
    }

}

class flight{
    Character origin;
    Character destination;

    public flight(Character a, Character b){
        this.origin = a;
        this.destination = b;
    }
}

 