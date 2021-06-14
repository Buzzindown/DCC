import java.util.concurrent.TimeUnit;

public class June112021 {

// This problem was asked by Dropbox.

// Conway's Game of Life takes place on an infinite two-dimensional board of square cells. 

// Each cell is either dead or alive, and at each tick, the following rules apply:

// Any live cell with less than two live neighbours dies.
// Any live cell with two or three live neighbours remains living.
// Any live cell with more than three live neighbours dies.
// Any dead cell with exactly three live neighbours becomes a live cell.
// A cell neighbours another cell if it is horizontally, vertically, or diagonally adjacent.

// Implement Conway's Game of Life. It should be able to be initialized with a starting list of live cell coordinates

// and the number of steps it should run for. Once initialized, it should print out the board state at each step. 

// Since it's an infinite board, print out only the relevant coordinates, i.e. from the top-leftmost live cell to bottom-rightmost live cell.

// You can represent a live cell with an asterisk (*) and a dead cell with a dot (.).

// yay CGOL , this shouldn't be too bad however it might be a bit of coding 
// we'll need a state, that is an array, we'll need a function to chec the state of each cell based on teh current state
// make a copy of that state at the start and then we can modify it based on our rules 

// finite solution, for infinite we would basically just make an expanding array grabbing our leftMost  upper and rightMost lower points 
// as the bound of our prints
    int n;
    Character arr[][];
    Character secondArr[][];

    June112021(int n){
        this.n = n;
        this.arr = new Character[n][n];
        this.secondArr = new Character[n][n];
        // need to randomize arr[][]
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                double temp = Math.random();
                if(temp >= 0.5){
                    this.arr[i][j] = '*';
                }else{
                    this.arr[i][j] = '-';
                }
            }
        }        
    }

    void solution(){
        // copy curr state to secondary state
        for(int i = 0; i < this.n; i++){
            for(int j = 0; j < this.n; j++){
                changeState(i, j);
            }
        }
 

        for(int i = 0; i < this.n; i++){
            for(int j = 0; j < this.n; j++){
                System.out.print(this.secondArr[i][j]);
            }
            System.out.println("");
        }
        
        
        
        // check if anything changed and print
        // now we need to go change our state based on our rules
        int changed = 0;
        for(int i = 0; i < this.n; i++){
            for(int j = 0; j < this.n; j++){
                if(this.arr[i][j] != this.secondArr[i][j]){
                    changed++;
                }
                this.arr[i][j] = this.secondArr[i][j];
            }
        }

        if(changed > 0){
            try{
            TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                System.out.println(e);
            }
            System.out.print("\033[H\033[2J");  
             System.out.flush(); 
            solution();
        }
    }

    void changeState(int i, int j){
        // need to count live neighbours 
        int n = this.n;
        int liveNeighbours = 0;
        if(i + 1 < n){
            if(this.arr[i + 1][j] == '*'){
                liveNeighbours ++;
            }
        }
        if(i + 1 < n && j + 1 < n){
            if(this.arr[i + 1][j + 1] == '*'){
                liveNeighbours ++;
            }
        }
        if(j + 1 < n){
            if(this.arr[i][j + 1] == '*'){
                liveNeighbours ++;
            }
        }
        if(j - 1 >= 0){
            if(this.arr[i][j - 1] == '*'){
                liveNeighbours ++;
            }
        }
        if(i - 1 >= 0){
            if(this.arr[i - 1][j] == '*'){
                liveNeighbours ++;
            }
        }
        if(i - 1 >= 0 && j - 1 >= 0){
            if(this.arr[i - 1][j - 1] == '*'){
                liveNeighbours ++;
            }
        }
        if(j - 1 >= 0){
            if(this.arr[i][j - 1] == '*'){
                liveNeighbours ++;
            }
        }
        if(i - 1 >= 0 && j + 1 < n){
            if(this.arr[i - 1][j + 1] == '*'){
                liveNeighbours ++;
            }
        }
        // Any live cell with less than two live neighbours dies.
// Any live cell with two or three live neighbours remains living.
// Any live cell with more than three live neighbours dies.
// Any dead cell with exactly three live neighbours becomes a live cell.
        if(this.arr[i][j] == '*'){
            if(liveNeighbours < 2){
                this.secondArr[i][j] = '.';
            }else if(liveNeighbours == 2 || liveNeighbours == 3){
                this.secondArr[i][j] = '*';
            }else if(liveNeighbours > 3){
                this.secondArr[i][j] = '.';
            }
        }else{
            if(liveNeighbours == 3){
                this.secondArr[i][j] = '*';
            }else{
                this.secondArr[i][j] = '.';
            }
        }

        
    }

    public static void main(String[] args) {
        June112021 test = new June112021(40);
        test.solution();
    }
}
