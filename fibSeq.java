import java.util.Arrays;

public class fibSeq {
    int index;
    Long[] fib;

    fibSeq(int index){
        this.index = index;
        this.fib = new Long[index + 1];
    }

    Long solution(int accIndex){
        if(accIndex == 0){
            this.fib[0] = 0L;
        }

        if(accIndex == 1){
            this.fib[accIndex] = 1L;
        }

        if(this.fib[accIndex] == null){
            this.fib[accIndex] = solution(accIndex - 1) + solution(accIndex - 2);
        }

        return this.fib[accIndex];
        
    }

    public static void main(String[] args){

        fibSeq temp = new fibSeq(50);
        System.out.println(temp.solution(50));
       // System.out.println(Arrays.toString(temp.fib));
    }
}
