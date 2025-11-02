import java.util.Random;

public class Game {

    private  int miss;
    private  int totalMiss;
    private  int totalHit;
    private  int shipID = 1;
    private  int strike;
    // Index 1–5 correspond to ship IDs 1–5. Index 0 is unused.
    private int[] shipHealth = new int[6];


    public enum Orientation {
        VERTICAL,
        HORIZONTAL;
    }


    private int[] ships = {5, 4, 3, 3, 2};

    boolean isValidPlacement; //used to determine valid placement of ship , will be reset and used for
    // each ship in do while

    private int[][] board = new int[10][10];

    //generator
    static Random rand = new Random();

    //returns a random vert or horiz enum
    public  Game.Orientation getOrientation() {
        if (rand.nextBoolean()) {
            return Orientation.VERTICAL;
        } else {
            return Orientation.HORIZONTAL;
        }
    }

    private  int row;
    private  int col;

    public  int getRandomRow() {
        row = rand.nextInt(10); // 1 to 10
        return row;
    }

    public  int getRandomCol() {
        col = rand.nextInt(10);  // 1 to 10
        return col;
    }

    public  void addMiss() {
        miss++;

    }

    public  void addTotalMiss() {
        totalMiss++;

    }
    public  void addTotalHit() {
        totalHit++;
    }

    public  void addStrike() {
        strike++;
    }


    public  int getMiss() {
        return miss;
    }



    public  int getTotalMiss() {
        return totalMiss;
    }

    public  int getTotalHit() {
        return totalHit;
    }

    public  void resetCounter() {
        miss = 0;

    }

    public  int getStrike() {
        return strike;
    }

    public  int getShipHealth(int shipID) {
        return shipHealth[shipID];
    }






    public  void decreaseShipHealth(int shipId) {
        if (shipHealth[shipId] > 0) {
            shipHealth[shipId]--;
        }
    }

    public  boolean strikedOut(){

        if (strike == 3){
            return true;
        }
        return false;
    }

    public  boolean hitAllShips(){
        if (totalHit == 17){
            return true;
        } else
            return false;
    }


    public  void setShips() {

        for (int i = 0; i < ships.length; i++) {
            int currentShip = ships[i];

            //make sure the index of ship is 1-5 not 0-4
            shipHealth[i+1] = currentShip ;
            Orientation orientation = getOrientation();

            int row, col;
            boolean valid;

            // find a random valid starting position
            do {
                row = getRandomRow();
                col = getRandomCol();
                valid = true;

                if (orientation == Orientation.HORIZONTAL) {
                    if (col + currentShip > 10) valid = false; // check bounds
                    else {
                        for (int k = 0; k < currentShip; k++) {
                            if (board[row][col + k] != 0) { // check overlap
                                valid = false;
                                break;
                            }
                        }
                    }
                } else { // VERTICAL
                    if (row + currentShip > 10) valid = false; // check bounds
                    else {
                        for (int k = 0; k < currentShip; k++) {
                            if (board[row + k][col] != 0) { // check overlap
                                valid = false;
                                break;
                            }
                        }
                    }
                }
            } while (!valid);

            // place the ship
            for (int k = 0; k < currentShip; k++) {
                if (orientation == Orientation.HORIZONTAL) {
                    board[row][col + k] = shipID ;
                } else { // VERTICAL
                    board[row + k][col] = shipID ;
                }
            }
            shipID ++;
        }




        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println(); // move to next row
        }
    }

    public  int[][] getShips(){
        return board;
    }




}
