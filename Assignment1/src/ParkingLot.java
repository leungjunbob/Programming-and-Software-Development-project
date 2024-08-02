/**
 * This is the parking lot class, it maintains the information in this parking lot such as number of empty lot and
 * occupied. The layout of lot and some function used to initialize the lot or modify particular filed of the lot.
 *
 * @author Junbo Liang, junbol@student.unimelb.edu.au, 1019905.
 */
public class ParkingLot {
    private int length;
    private int width;
    private int emptyLots = -1;
    private int occupied = -1;
    private String[][] lot;
    // Initialize the class variables.

    public ParkingLot() {

    }

    public int getEmptyLots() {
        return emptyLots;
    }

    public int getOccupied() {
        return occupied;
    }

    public void minusEmptyLots(int num) {
        this.emptyLots -= num;
    }
    public void addEmptyLots(int num) {
        this.emptyLots += num;
    }
    public void minusOccupied(int num) {
        this.occupied -= num;
    }
    public void addOccupied(int num) {
        this.occupied += num;
    }
    // + or - number of empty lot and occupied lot.

    public void setLot(String symbol, int[] position) {
        this.lot[position[0]][position[1]] = symbol;
        // Set particular field in the lot as "C", "B" or ".".
    }

    public String getLotSymbol(int[] position) {
        return lot[position[0]][position[1]];
    }
    public String  movementResult (int[] position){
        return switch (this.lot[position[0]][position[1]]) {
            case "B", "C" -> "vehicle";
            case "-", "|" -> "wall";
            case "D" -> "door";
            case "P" -> "pillar";
            default -> "good";
        };
        // This function used to determine the next movement will collide with something or not
        // Hit car or bike return 'vehicle'; Hit wall return 'wall'; Hit D return 'door';
        // Hit Pillar return 'pillar'; Valid move return 'good';
    }
    public void initLot(int length, int width) {
        this.length = length;
        this.width = width;
        this.emptyLots = (length - 2) * (width - 2) - 1;
        this.occupied = 0;
        this.lot = new String[width][length];
        String str;
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.length; j++) {
                if (i == 1 && j == 0 || i == this.width - 2 && j == length - 1) {
                    str = "D";
                } else if (j == 0 || j == this.length - 1) {
                    str = "|";
                } else if (i == 0 || i == this.width - 1) {
                    str = "-";
                } else if (i == (this.width - 1) / 2 && j == (this.length - 1) / 2) {
                    str = "P";
                    // Find the correct index of pillar, since index start counting from 0,
                    // for example, fourth field in parking lot then its index is 3.
                } else {
                    str = ".";
                }
                this.lot[i][j] = str;
            }
        }
        // Initialized the lot and value in each particular field, calculated the number of empty lot.
    }

    public void showLot() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.length; j++) {
                System.out.print(lot[i][j]);
            }
            System.out.println();
        }
        // Print the Layout of parking lot.
    }

    public boolean validation(int length, int width){
        if (length < 5 || width < 5){
            System.out.println("ParkingLot size cannot be less than 5. Goodbye!");
            return false;
        } else {
            this.length = length;
            this.width = width;
            return true;
        }
        // This function is for checking validation of input as well as initializing the ParkingLot.
    }
}
