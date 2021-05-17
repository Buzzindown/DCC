import java.lang.Thread;

public class May132021 {
    // Good morning! Here's your coding interview problem for today.

    // This problem was asked by Apple.
    
    // Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.

    // this seems pretty straight forward
    // we just need to find a way to pass a funcction to a method
    // then call a sleep then execute the function

    // the question is a little unclear but this seems to do the trick,
    // passing an already existing function would be quite a trick in java

    // perhaps this is more of a question of multithreading ? 

    public void Scheduler(JobExceute task, int n){
        try{
            Thread.sleep(n);
            task.task("params");
        }catch (Exception e){
            System.out.println("Something went wrong");
        }
        

    }
    public static void main(String[] args){

        May132021 test = new May132021();
        JobExceute func = new JobExceute();
        test.Scheduler(func, 10000);

    }
}

interface job{
    // function prototype
    public void task(String parameter);
}

class JobExceute implements job{

    // actual function
    public void task(String parameter){
        System.out.println("executing a function");
    }
}