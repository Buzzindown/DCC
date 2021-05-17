import java.util.Scanner;
import java.util.Random;

public class May172021 {

    // This problem was asked by Google.

    // The area of a circle is defined as r^2. Estimate \pi to 3 decimal places using a Monte Carlo method.

    // Hint: The basic equation of a circle is x^2 + y^2 = r^2.

    // for pi we know that A = pi(r)^2,
    // so pi = A/r^2 
    // monte carlo problems rely on random points, so can have a unit square and a 
    // unit circle within the square, then we're gonna have a bunch of random points scattered
    // since we know the radiues of the circle we can say that 
    // pi = A/(1/2)^2
    // pi = A * 4
    // we can calculate the area as a ratio of points in the circle to all the points
    // within the square since the area of the square is 1, say that it contained 100 points,
    // and the circle contained 70 points, we could approximate that the area of the circle
    // was 1 * 70/100 or 0.7, this works the same way, we can checkwhether the points
    // are within the circle using x^2 + y^2
    void solution(double numPoints){
        double circlePoints = 0;

        Random rand = new Random();

        for(int a = 0; a < numPoints; a++){
            Double x = rand.nextDouble();
            Double y = rand.nextDouble();
            if(((x*x) + (y*y)) <= 1){
                circlePoints++;
            }
        }
        Double pi = 4 * (circlePoints/numPoints);
        System.out.println(pi);
    }


    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter number of random points");
        int numElems = Integer.parseInt(myScanner.nextLine());

        myScanner.close();

        May172021 t = new May172021();
        t.solution((double)numElems);

    }

}
