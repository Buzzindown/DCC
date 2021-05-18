import java.util.Random;
import java.util.Scanner;

public class May182021 {

    // This problem was asked by Facebook.

    // Given a stream of elements too large to store in memory, pick a random element from the stream with uniform probability.
    
    // Interesting problem, the big problem to overcome with this issue
    // seems to be that we need to be able to pick with uniform probability,
    // However, we're unable to really store any information about the stream in memory
    // If we could store the stream in memory in an array, we could just generate
    // an index number randomly (and in a uniform manner), it seems like we're able to
    // comb over the stream atleast once (we have to be in order to make some selection right)
    // so as long as we can feed the entire stream through func A and then again through func B,
    // we should be able to do something fairly easily
    // normally randomizer functions for an array pull out a value for an index but since we're 
    // not able to index our stream we need to do something else that we can later use to 
    // decide what value to select, we can still assosciate values with each stream element,
    // we just can't access the stream elements by index, so what if we gave each element
    // in the stream a random number (like we would for any other type of sort), and then
    // we keep track of the lowest random number that occured (this will be the one we want to pick)
    // but since we can't index it, we need to pass the stream through another function,
    // which knows the random number and it will check each stream element for that number,
    // and since we know it exists, whenever it finds it - we choose that element, 
    // better yet we can use a randomized float which will be a smaller range and more random
    // than if we used ints

    // if we were unable to tack on a value to the stream, which it's unclear whether we can or not,
    // we could produce a parallel stream for our random values and just do a side by side comparison
    // to grab the elements whenever we're selecting, but for now let's assume we can freely assosciate
    // and keep a value tied to our stream


    Object solution(Object[] stream){
        Random randy = new Random();
        NStream ns[] = new NStream[stream.length];
        float lowest = 1;
        int x = 0;
        
        for(x = 0; x < stream.length; x++){
            ns[x] = new NStream();
            ns[x].element = stream[x];
            ns[x].random = randy.nextFloat();

            if(ns[x].random < lowest){
                lowest = ns[x].random;
            }
        }

        return (this.selection(ns, lowest));
    }

    Object selection(NStream[] stream, float selection){
        int x = 0;
        for(x = 0; x < stream.length; x++){
            if(stream[x].random == selection){
                return stream[x].element;
            }
        }

        return null;
    }

    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter number of elements");
        int numElems = Integer.parseInt(myScanner.nextLine());

        System.out.println("Enter your *stream* line by line");

        String arr[] = new String[numElems];
        int x;


        for(x = 0; x < numElems; x++){
            arr[x] = myScanner.nextLine();
        }

        myScanner.close();

        May182021 test = new May182021();

        
        Object o = test.solution(arr);

        System.out.println(o.toString());
    }
   
}

class NStream{
    Object element;
    float random;

    NStream(){
        this.element = null;
        this.random = 0;
    }

    NStream(Object o, float rando){
        this.element = o;
        this.random = rando;
    }
}
