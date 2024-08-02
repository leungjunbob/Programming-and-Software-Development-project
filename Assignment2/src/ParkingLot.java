/**
 * This is the parking lot class, it maintains the information in this parking lot such as number of empty lot and
 * occupied. The layout of lot and some methods used to initialize the lot or modify particular filed of the lot.
 *
 * @author Junbo Liang, junbol@student.unimelb.edu.au, 1019905.
 */

import java.util.ArrayList;

public class ParkingLot {
    private int length;
    private int width;
    private int emptyLots = -1;
    private int occupied = -1;
    private String[][] lot;
    private String[][] defaultLot;
    private int lotOfBike;
    private int lotOfCar;
    private int lotOfTruck;
    private ArrayList<Vehicle> vehicleArrayList = new ArrayList<Vehicle>();

    // Initialize the class variables.

    /**
     * A constructor.
     */
    public ParkingLot() {

    }

    /**
     * This method will add the input vehicle that already checkout into arraylist.
     *
     * @param vehicle Vehicle.
     */
    public void add(Vehicle vehicle) {
        vehicleArrayList.add(vehicle);
        // This method will add the input vehicle into array list, and used for printing out the parking fee log.
    }

    /**
     * This method will print put the parking fee log if there are car checked out or print out no record.
     */
    public void printHistoryRecord() {
        if (vehicleArrayList.size() == 0) {
            System.out.println("No records found!");
        } else {
            for (Vehicle vehicle : vehicleArrayList) {
                System.out.printf("|%14s", vehicle.getVehicleType());
                System.out.printf("|%17s", vehicle.getRengId());
                System.out.printf("|%s", vehicle.getEntryDate().describe());
                System.out.printf("|%s", vehicle.getExitDate().describe());
                System.out.printf("|%13.1f|\n", vehicle.getParkingFee());
            }
        }
        // This method will print put the parking fee log if there are car checked out or print out no record.
    }

    /**
     * A getter to get the number of empty lots.
     *
     * @return Empty lots.
     */
    public int getEmptyLots() {
        return emptyLots;
    }

    /**
     * A getter to get the number of occupied lots.
     *
     * @return Occupied lots.
     */
    public int getOccupied() {
        return occupied;
    }

    /**
     * A getter to get the length of parking lot.
     *
     * @return Length of parking lot.
     */
    public int getLength() {
        return length;
    }

    /**
     * A getter to get the width of parking lot.
     *
     * @return Width of parking lot.
     */
    public int getWidth() {
        return width;
    }

    /**
     * A getter to get the default lot(Without any vehicle on it).
     *
     * @return Default lot.
     */
    public String[][] getDefaultLot() {
        return defaultLot;
    }

    /**
     * A method to minus a num in number of empty lot.
     *
     * @param num Number need to minus.
     */
    public void minusEmptyLots(int num) {
        this.emptyLots -= num;
    }

    /**
     * A method to add a num in number of empty lot.
     *
     * @param num Number need to add.
     */
    public void addEmptyLots(int num) {
        this.emptyLots += num;
    }

    /**
     * A method to minus a num in number of Occupied lot.
     *
     * @param num Number need to minus.
     */
    public void minusOccupied(int num) {
        this.occupied -= num;
    }

    /**
     * A method to add a num in number of occupied lot.
     *
     * @param num Number need to add.
     */
    public void addOccupied(int num) {
        this.occupied += num;
    }
    // + or - number of empty lot and occupied lot.

    /**
     * A method to set particular field in the lot as different symbol according to the inputted String.
     *
     * @param symbol   Symbol shown on the parking lot. One of {"C", "B", "M", "T", "~", ".", "D"}.
     * @param position 2D array of position({x, y}).
     */
    public void setLot(String symbol, int[] position) {
        this.lot[position[0]][position[1]] = symbol;
        // Set particular field in the lot as different symbol according to the inputted parameter.
    }

    /**
     * This method used to determine the next movement will collide with something or not.
     *
     * @param position    2D array of position({x, y}).
     * @param vehicleType Type of vehicle.
     * @return Hit vehicle return 'vehicle'; Hit "-" or "|" return 'wall'; Hit "D" return 'door';
     * Hit "P" return 'pillar'; Invalid attempt lot return 'invalid'; Valid movement return 'good';
     */
    public String movementResult(int[] position, String vehicleType) {
        String result;
        switch (this.lot[position[0]][position[1]]) {
            case "B", "C", "M", "T" -> result = "vehicle";
            case "-", "|" -> result = "wall";
            case "D" -> result = "door";
            case "P" -> result = "pillar";
            case "." -> {
                switch (vehicleType) {
                    case "Truck" -> result = position[1] != 1 ? "invalid" : "good";
                    case "Bike", "Motorbike" -> result = position[1] < this.length - 3 ? "invalid" : "good";
                    default -> result = "good";
                }
                // When the vehicle move to a lot, need to verify this lot is reserved for it or not.
            }
            default -> result = "good";
        }
        return result;
        // This method used to determine the next movement will collide with something or not
        // Hit vehicle return 'vehicle'; Hit wall return 'wall'; Hit D return 'door';
        // Hit Pillar return 'pillar'; Invalid attempt lot return 'invalid'; Valid move return 'good';
    }

    /**
     * This method returns the number of lots that are reserved for this particular vehicle.
     *
     * @param vehicleType Type of Vehicle.
     * @return Number of reserved lots for particular vehicle.
     */
    public int getLotOfVehicle(String vehicleType) {
        switch (vehicleType.toLowerCase()) {
            case "bike", "motorbike" -> {
                return lotOfBike;
            }
            case "car" -> {
                return lotOfCar;
            }
            case "truck" -> {
                return lotOfTruck;
            }
        }
        // This method returns the number of lots that are reserved for this particular vehicle.
        return 0;
    }

    /**
     * This method increase or decrease the number of reserved lots for bike.
     *
     * @param sign "+": increase the number of lot of bike; other: decrease the number of lot of bike.
     */
    public void modifyLotOfBike(char sign) {
        if (sign == '+') {
            this.lotOfBike++;
        } else {
            this.lotOfBike--;
        }
    }

    /**
     * This method increase or decrease the number of reserved lots for car.
     *
     * @param sign "+": increase the number of lot of car; other: decrease the number of lot of car.
     */
    public void modifyLotOfCar(char sign) {
        if (sign == '+') {
            this.lotOfCar++;
        } else {
            this.lotOfCar--;
        }
    }

    /**
     * This method increase or decrease the number of reserved lots for truck.
     *
     * @param sign "+": increase the number of lot of truck; other: decrease the number of lot of truck.
     */
    public void modifyLotOfTruck(char sign) {
        if (sign == '+') {
            this.lotOfTruck++;
        } else {
            this.lotOfTruck--;
        }
    }
    // These methods increase or decrease the number of reserved lots for each vehicle.

    /**
     * This method initialized the parking lot. Stores the design of parking lot in String type 2D array.
     *
     * @param length Length of the parking lot.
     * @param width  Width of the parking lot.
     */
    public void initLot(int length, int width) {
        final int rowOfNonLot = 6;
        final int columnOfWall = 2;
        final int numberOfTwo = 2;
        this.length = length;
        this.width = width;
        this.emptyLots = (width - rowOfNonLot) *
                ((length - columnOfWall) / numberOfTwo + (length - columnOfWall) % numberOfTwo);
        this.occupied = 0;
        this.lotOfBike = this.width - rowOfNonLot;
        this.lotOfTruck = this.width - rowOfNonLot;
        // Only one column of lots is reserved for bike, motorbike and truck, since first three and last three rows are
        // walls, pillars or driveways, so need to minus 6.
        this.lotOfCar = this.emptyLots;
        this.lot = new String[width][length];
        this.defaultLot = new String[width][length];
        // Initialized the lot that need to display and the default lot for reference when modifying the lot symbol.
        String str;
        for (int i = 0; i < this.width; i++) {
            this.lot[i][0] = "|";
            this.lot[i][this.length - 1] = "|";
            this.defaultLot[i][0] = "|";
            this.defaultLot[i][this.length - 1] = "|";
        }
        this.lot[1][0] = "D";
        this.lot[this.width - 2][this.length - 1] = "D";
        this.defaultLot[1][0] = "D";
        this.defaultLot[this.width - 2][this.length - 1] = "D";
        // Initialized the first column and last column.

        for (int i = 0; i < 2; i++) {
            for (int j = 1; j < this.length - 1; j++) {
                if (i == 0) {
                    str = "-";
                } else {
                    str = "~";
                }
                this.lot[i][j] = str;
                this.lot[this.width - i - 1][j] = str;
                this.defaultLot[i][j] = str;
                this.defaultLot[this.width - i - 1][j] = str;
            }
        }
        // Initialized the first two rows and last two rows.
        for (int i = 2; i < this.width - 2; i++) {
            for (int j = 1; j < this.length - 1; j++) {
                if (j % 2 != 0) {
                    if (i == 2 || i == this.width - 3) {
                        str = "P";
                    } else {
                        str = ".";
                    }
                    // Only for third and last third row, between the driveway is pillar, other rows just lot.
                } else {
                    str = "~";
                }
                // This is the driveway.
                this.lot[i][j] = str;
                this.defaultLot[i][j] = str;
            }
        }
        // Initialize the rest of parking lot.
    }

    /**
     * This method displays the design of parking lot.
     */
    public void showLot() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.length; j++) {
                System.out.print(lot[i][j]);
            }
            System.out.println();
        }
        // Print the Layout of parking lot.
    }

    /**
     * This method checks the inputted length and width are valid or not.
     *
     * @param length Length of the parking lot.
     * @param width  Width of the parking lot.
     * @return Valid: true; Not valid: false.
     */
    public boolean validation(int length, int width) {
        final int minimumSize = 7;
        if (length < minimumSize || width < minimumSize) {
            System.out.println("ParkingLot size cannot be less than 7. Goodbye!");
            return false;
        } else {
            initLot(length, width);
            return true;
        }
        // This function is for checking validation of input as well as initializing the ParkingLot.
    }

}
