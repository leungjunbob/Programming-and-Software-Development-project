import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is main class, executed overall program from this class. It included the menus, important function like
 * check-in, check-out and parking function, as well as some helper function to obtain the valid input.
 *
 * @author Junbo Liang, junbol@student.unimelb.edu.au, 1019905.
 */
public class ParkingLotEngine {
    private Scanner scanner = new Scanner(System.in);
    private ParkingLot parkingLot = new ParkingLot();
    private ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();

    // Initializing the classes.

    /**
     * This is the main method to start the game.
     * @param args
     */
    public static void main(String[] args) {
        ParkingLotEngine engine = new ParkingLotEngine();
        // Runs the main game loop.
        engine.startProgram(args);
    }

    /**
     * This is the method run at the beginning right after main method, it checks whether there are input
     * from commandline and whether the input is valid or not.
     * @param args
     */
    private void startProgram(String[] args) {
        boolean valid = true;
        if (args.length > 0 && isNumeric(args[0])) {
            int length = Integer.parseInt(args[0]);
            int width = Integer.parseInt(args[1]);
            valid = parkingLot.validation(length, width);
        }
        // Check whether there are input from commandline and whether the input is valid.
        if (valid) {
            displayWelcomeText();
            menu("main");
        }
        // Printout the title text and go to main menu.
    }

    /**
     * This is the menu method, it depends on the input command, direct to main menu or help menu,
     * print out different result.
     * @param status
     */
    private void menu(String status) {
        if (status.equals("main")) {
            System.out.print("Empty Lots: ");
            if (parkingLot.getEmptyLots() <= 0) {
                System.out.print("[None]");
            } else {
                System.out.print(parkingLot.getEmptyLots());
            }
            System.out.print(" | Occupied: ");
            if (parkingLot.getOccupied() < 0) {
                System.out.println("[None]");
            } else {
                System.out.println(parkingLot.getOccupied());
            }

            System.out.println("Please enter a command to continue.");
            System.out.println("Type 'help' to learn how to get started.");
            // Printout of the main menu.

        } else {
            System.out.println("\nType 'commands' to list all the available commands");
            System.out.println("Type 'menu' to return to the main menu");
            // Printout of the help menu.
        }

        System.out.print("> ");
        String command = scanner.nextLine();
        switch (command) {
            case "help" -> menu("second");
            case "commands" -> {
                System.out.println();
                System.out.println("help: shows you list of commands that you can use.");
                System.out.println("parkinglot: initialise the space for parking lot or view the layout of parking lot.");
                System.out.println("checkin: add your car details while entering the parking lot.");
                System.out.println("park: park your car to one of the empty spot.");
                System.out.println("checkout: view the parking fee while exiting the parking lot.");
                System.out.println("parkingfeelog: view the transaction log for parking lot.");
                System.out.println("exit: To exit the program.");
                menu("second");
            }
            case "menu" -> {
                System.out.println();
                menu("main");
            }
            case "exit" -> System.out.println("Good bye from the Java Parking Lot! See you next time!");
            case "parkinglot" -> parkingLotMenu();
            case "checkin" -> checkInFunction();
            case "checkout" -> checkOutFunction();
            case "park" -> parkingFunction();
            case "parkingfeelog" -> parkingFeeLog(parkingLot);
            default -> {
                System.out.println("Command not found!");
                if (status.equals("main")) {
                    menu("main");
                } else {
                    menu("second");
                }
                // Depend on the current status, go back to main menu or help menu
            }
        }
        // Depend on the input command, direct to different function or print out different result
    }

    /**
     * This method will run once a vehicle hit something,
     * printout the message and bring the vehicle to previous position.
     * @param vehicle By this parameter will print out different messages and add the number of hit to this vehicle.
     * @param previousPosition After handled the collisions, set the vehicle to its previous position.
     */
    private void hitManagement(Vehicle vehicle, int[] previousPosition) {
        if (vehicle.getVehicleType().equals("Bike")) {
            System.out.println("!");
        } else {
            System.out.println(", there will be a damage fee!");
            vehicle.addNumberOfHit(1);
        }
        vehicle.setPosition(previousPosition);
        // This method will run once a vehicle hit something,
        // printout the message and bring the vehicle to previous position.
    }

    /**
     * This method will loop until obtain a rengId that is 6 digits long, and return it.
     * @return return the 6 digits rengID.
     */

    private String getRengId() {
        final int numberSix = 6;
        boolean valid = false;
        String rengId = "";
        while (!valid) {
            System.out.print("> Regn Id: ");
            rengId = scanner.nextLine();
            if (rengId.length() == numberSix) {
                valid = true;
            }
            if (!valid) {
                System.out.println("Invalid detail, please enter detail again!");
            }
        }
        return rengId;
        //This method will loop until obtain a rengId that is 6 digits long, and return it.
    }

    /**
     * This method used the inputted rengId to find matched vehicle that is in list(checkin already).
     * @param rengId The inputted correct format 6 digits rengID.
     * @return find matched vehicle then return the index of this vehicle in list, otherwise return -1.
     */
    private int findVehicleIndex(String rengId) {
        for (int i = 0; i < this.vehicleList.size(); i++) {
            if (vehicleList.get(i).getRengId().equals(rengId)) {
                return i;
            }
        }
        return -1;
        //This method used the inputted rengId to find matched vehicle that is in list(checkin already),
        // if success then return the index of this vehicle in list otherwise return -1.
    }

    /**
     * This is the parking method, it checks is there any vehicle checked in, otherwise printout message and direct
     * to main menu. A loop to obtain the correct rengID that is checked in. A loop of movement function, depends on
     * the movement inputted, move the bike position or return to main menu when a "q" is inputted. A part that depends
     * on the movement result, switch to execute different batches.
     */
    private void parkingFunction() {
        System.out.println("To park a vehicle provide the details.");
        if (parkingLot.getOccupied() <= 0) {
            System.out.println("No vehicle checked in the parking lot, taking you back to main menu");
            menu("main");
            return;
        }
        // Case when no vehicle checked in, printout messages and direct to main menu.

        String rengId = "";
        Vehicle vehicle = null;
        int vehicleIndex;
        boolean valid = false;
        while (!valid) {
            rengId = getRengId();
            vehicleIndex = findVehicleIndex(rengId);
            if (vehicleIndex == -1) {
                System.out.println("The vehicle mentioned is not parked in the parking lot.");
            } else {
                vehicle = this.vehicleList.get(vehicleIndex);
                valid = true;
            }
        }
        // Loop until find out a vehicle that is in the vehicle list.

        String movement = "";
        String[][] defaultLot = parkingLot.getDefaultLot();
        while (!movement.equals("q")) {
            parkingLot.showLot();
            movement = inputMovement();
            int[] position;
            int[] previousPosition = vehicle.getPosition();
            switch (movement) {
                case "w" -> vehicle.setCurrentRow("-");
                case "s" -> vehicle.setCurrentRow("+");
                case "a" -> vehicle.setCurrentColumn("-");
                case "d" -> vehicle.setCurrentColumn("+");
                case "q" -> {
                    menu("main");
                    return;
                }
                default -> {
                }
            }
            // Depend on the movement inputted, move the bike position or return to main menu.

            position = vehicle.getPosition();
            switch (parkingLot.movementResult(position, vehicle.getVehicleType())) {
                case "good" -> {
                    parkingLot.setLot(vehicle.getSymbol(), position);
                    parkingLot.setLot(defaultLot[previousPosition[0]][previousPosition[1]], previousPosition);
                }
                // If a valid movement, modify the layout of ParkingLot.
                case "invalid" -> {
                    if (vehicle.getVehicleType().equals("Truck")) {
                        System.out.println("You cannot park a truck in the parking lot anywhere except the" +
                                " parking spots near the entry.");
                    } else {
                        System.out.println("You cannot park a bike or motorbike in the parking lot anywhere" +
                                " except the parking spots near the exit.");
                    }
                    vehicle.setPosition(previousPosition);
                }
                // When the vehicle go to not wrong lots, printout warning.
                case "door" -> {
                    System.out.println("You cannot exit the parking lot without checkout.");
                    vehicle.setPosition(previousPosition);
                }
                case "vehicle" -> {
                    System.out.printf("You have hit a %s",
                            parkingLot.movementResult(position, vehicle.getVehicleType()));
                    hitManagement(vehicle, previousPosition);
                }
                case "pillar", "wall" -> {
                    System.out.printf("You have hit the %s",
                            parkingLot.movementResult(position, vehicle.getVehicleType()));
                    hitManagement(vehicle, previousPosition);
                }
                // When hit a wall or pillar or vehicle, printout warning and add the number of hit.
                default -> {
                }
            }
        }
    }

    /**
     * This method is to loop until obtain desire movement command.
     * @return the correct inputted, must be one of {"w", "s", "a", "d", "q"}.
     */
    private String inputMovement() {
        String movement = "";
        boolean valid = true;
        do {
            if (!valid) {
                System.out.println("Invalid command!");
            }
            System.out.print("Type w/s/a/d to move the vehicle to up/down/left/right or else press q to exit.\n> ");
            movement = scanner.nextLine();
            valid = movement.equals("w") || movement.equals("s") ||
                    movement.equals("a") || movement.equals("d") || movement.equals("q");
        } while (!valid);
        return movement;
        // This method is to loop until obtain desire movement command.
    }

    /**
     * This is the checkout function, it checks whether there is vehicle checkin or not, otherwise direct back to main
     * menu. A loop to obtain a correct rengID that the corresponding vehicle exist or not. A part to check the
     * position of this vehicle is in front of the checkout door or not. A loop to obtain the correct checkout datetime
     * which needs to be in correct yyyy-mm-dd format and behind the checkin datetime. A part to display the information
     * including the vehicle's detail, total fee, total number of hits, etc. A loop to obtain correct respond either "Y"
     * to check out or menu to discard the checkout and return to main menu.
     *
     */
    private void checkOutFunction() {
        if (parkingLot.getOccupied() <= 0) {
            System.out.println("Invalid command! The parking is empty. Taking you back to main menu.");
            menu("main");
            return;
        }
        // When no car parked or ParkingLot not initialized, printout message and direct to main menu.

        System.out.println("Please enter your vehicle details");
        String rengId = "";
        Vehicle vehicle;
        rengId = getRengId();
        int vehicleIndex = findVehicleIndex(rengId);
        if (vehicleIndex == -1){
            System.out.println("The selected vehicle type is not present in the parking lot." +
                    " Taking you back to main menu");
            menu("main");
            return;
        } else {
            vehicle = this.vehicleList.get(vehicleIndex);
        }
        // To get the vehicleType that is checked in, otherwise bring back to main menu.

        int[] position = vehicle.getPosition();
        int x = position[0];
        int y = position[1];
        if (!(x == parkingLot.getWidth() - 2 && y == parkingLot.getLength() - 2
                || x == 1 && y == 1)) {
            System.out.println("The selected vehicle type is not at the checkout door." +
                    " Please proceed to checkout door. Taking you back to main menu.");
            menu("main");
            return;
        }
        // The vehicle only can check out right on the left of the exit door, otherwise back to main menu.

        boolean valid = false;
        while (!valid) {
            int[] date = inputDate("exit");
            int[] time = inputTime("exit");
            Date exitDate = new Date(date[0], date[1], date[2], time[0], time[1]);
            valid = validCheckoutDatetime(vehicle.getEntryDate(), exitDate);
            if (!valid) {
                System.out.println("Checkout datetime cannot be less than checkin datetime for the vehicle." +
                        " Please re-enter.");
            } else {
                vehicle.setExitDate(exitDate);
            }
        }
        // Loop until a valid exit data is entered, that is exitDateTime need to be after entryDateTime.

        vehicle.calculateTotalHour(vehicle.getExitDate());
        System.out.println("Please verify your details.");
        System.out.println("Total number of hours: " + vehicle.getTotalHour());
        if (vehicle.getTotalOvernightParking() > 0) {
            System.out.println("Total number of overnight parking: " + vehicle.getTotalOvernightParking());
        }
        // Display this message only when this vehicle has collision.
        System.out.println("Total number of hits:" + vehicle.getTotalNumberOfHit());
        System.out.println("Vehicle Type: " + vehicle.getVehicleType());
        System.out.println("Regn Id: " + vehicle.getRengId());
        System.out.printf("Total Parking Fee: $%.1f\n", vehicle.calculateParkingFee(vehicle.getExitDate()));
        // Display the check-out messages.

        String command = "";
        System.out.println("Type Y to accept the fee or menu to return to main menu");
        valid = false;
        while (!valid) {
            System.out.print("> ");
            command = scanner.nextLine();
            valid = command.equals("Y") || command.equals("menu");
            if (!valid) {
                System.out.println("You cannot checkout your vehicle. Please accept the fee by pressing Y" +
                        " or type menu to return to main menu and park your vehicle.");
            }
            // Loop until obtained "Y" or "menu".
        }
        if (command.equals("Y")) {
            System.out.println("Thank you for visiting Java Parking Lot. See you next time!");
            modifyLots(vehicle.getVehicleType(), '+');
            parkingLot.minusOccupied(1);
            parkingLot.addEmptyLots(1);
            parkingLot.add(vehicle);
            parkingLot.setLot("~", vehicle.getPosition());
            this.vehicleList.remove(vehicleIndex);
        }
        menu("main");
        /*
        If the input is "Y" then print out messages and remove the vehicle from arraylist and remove from
        ParkingLot, then go back to main menu. If the input is "menu", then also go back to main menu.
        */
    }

    /**
     * This method checks the year first, the follow by the month, day, hour, minute. Whether the inputted exit datetime
     * is after entry datetime or not.
     * @param entry The entry datetime in Class Date.
     * @param exit The exit datetime in Class Date.
     * @return A boolean, if exit datetime is after entry datetime then return true, otherwise false.
     */
    private boolean validCheckoutDatetime(Date entry, Date exit) {
        return entry.getYear() <= exit.getYear() &&
                (entry.getYear() != exit.getYear() || entry.getMonth() <= exit.getMonth()) &&
                (entry.getYear() != exit.getYear() || entry.getMonth() != exit.getMonth()
                        || entry.getDay() <= exit.getDay()) &&
                (entry.getYear() != exit.getYear() || entry.getMonth() != exit.getMonth()
                        || entry.getDay() != exit.getDay() || entry.getHour() <= exit.getHour()) &&
                (entry.getYear() != exit.getYear() || entry.getMonth() != exit.getMonth()
                        || entry.getDay() != exit.getDay() || entry.getHour() != exit.getHour()
                        || entry.getMinute() <= exit.getMinute());
        // Check the year first, the follow by the month, day, hour, minute.
    }

    /**
     * This method is used to valid the inputted time and return the hour and minute together as an array.
     * @param state Either "entry" or "exit", used in printout message.
     * @return new int array with hour and minute.
     */
    private int[] inputTime(String state) {
        final int upperHour = 23;
        final int upperMinute = 59;
        final int numberOfFive = 5;
        String timeOfInput = "";
        String[] time;
        int hour = 0, minute = 0;
        boolean valid = false;
        do {
            if (valid) {
                System.out.println("Incorrect time format, please enter time in HH:mm format again!");
            }
            System.out.printf("> Time of %s: ", state);
            timeOfInput = scanner.nextLine();
            if (timeOfInput.length() == numberOfFive) {
                time = timeOfInput.split(":");
                hour = Integer.parseInt(time[0]);
                minute = Integer.parseInt(time[1]);
            }
            valid = timeOfInput.charAt(2) != ':' || hour < 0 || hour > upperHour || minute < 0 || minute > upperMinute;
        } while (valid);
        return new int[]{hour, minute};
        // This method is used to valid the inputted time and return the hour and minute together as an array.
    }

    /**
     * This method is used to valid the inputted date and return the year, month and day together as an array.
     * @param state Either "entry" or "exit", used in printout message.
     * @return new int array with year, month and day.
     */
    private int[] inputDate(String state) {
        String dateOfInput = "";
        String[] date;
        int year = -1, month = -1, day = -1;
        boolean valid = true;
        boolean valid1 = true;
        do {
            if (!valid) {
                System.out.println("Incorrect date format, please enter date in yyyy-MM-dd format again!");
            } else if (!valid1) {
                System.out.println("Incorrect date format, please enter date in yyyy-MM-dd format again" +
                        " between 1970-01-01 and 2099-12-31!");
            }
            System.out.printf("> Date of %s: ", state);
            dateOfInput = scanner.nextLine();
            if (dateOfInput.matches("\\d{4}-\\d{2}-\\d{2}")) {
                date = dateOfInput.split("-");
                year = Integer.parseInt(date[0]);
                month = Integer.parseInt(date[1]);
                day = Integer.parseInt(date[2]);
                valid = true;
                valid1 = isDateValid(year, month, day);
            } else {
                valid = false;
            }
            // This if statement only true when the format match yyyy-mm-dd.
        } while (!valid || !valid1);
        return new int[]{year, month, day};
        // This method is used to valid the inputted date and return the year, month and day together as an array.
    }

    /**
     * This method checks the date is valid or not, simply does not consider leap year and different months.
     * @param year Year(>= 1970 && <= 2099)
     * @param month Month(>= 1 && <= 12)
     * @param day Day(>= 1 && <= 31)
     * @return A boolean number whether the date is valid or not.
     */
    private boolean isDateValid(int year, int month, int day) {
        final int lowerYear = 1970;
        final int upperYear = 2099;
        final int lowerMonth = 1;
        final int upperMonth = 12;
        final int lowerDay = 1;
        final int upperDay = 31;
        if (year < lowerYear || year > upperYear) {
            return false;
        }
        if (month < lowerMonth || month > upperMonth) {
            return false;
        }
        return (day >= lowerDay && day <= upperDay);
        // This method checks the date is valid or not, simply does not consider leap year and different months.
    }

    /**
     * This method use loop to input the valid vehicle type, either one of {"car", "bike", "motorbike", "truck"}.
     * @return A String of corresponding vehicle type, all lowercase.
     */
    private String inputVehicleType() {
        String vehicleType = "";
        do {
            if (!(vehicleType.equals(""))) {
                System.out.println("Invalid detail, please enter detail again!");
            }
            System.out.print("> Vehicle Type: ");
            vehicleType = scanner.nextLine();
        } while (!(vehicleType.equalsIgnoreCase("car") || vehicleType.equalsIgnoreCase("bike")
                || vehicleType.equalsIgnoreCase("Truck") ||
                vehicleType.equalsIgnoreCase("motorbike")));
        return vehicleType.toLowerCase();
        // Loop to input the valid vehicle type.
    }

    /**
     * This method will modify the number of lots that is reserved for each kind of vehicle.
     * @param vehicleType String of vehicle type, one of {"car", "bike", "motorbike", "truck"}.
     * @param sign "+" or "-", corresponding to add one more or minus one.
     */
    private void modifyLots(String vehicleType, char sign) {
        switch (vehicleType.toLowerCase()) {
            case "car" -> parkingLot.modifyLotOfCar(sign);
            case "bike", "motorbike" -> parkingLot.modifyLotOfBike(sign);
            case "truck" -> parkingLot.modifyLotOfTruck(sign);
        }
        // This method will modify the number of lots that is reserved for each kind of vehicle.
    }

    /**
     * This is checkin function, it checks the parking lot is initialized or not, and is there any vehicle checked in,
     * if not then printout message and direct back to main menu. Obtain a vehicle type and checks whether still have
     * reserved lots or not, if not direct back to main menu. Collect the information of vehicle, vehicle type, rengID,
     * color, model, checkin datetime, convert vehicle type to first letter in capital and create instance variable
     * corresponding to vehicle type. After checked in a vehicle, minus 1 reserved lot for this type of vehicle, and
     * minus 1 empty lot and add 1 occupied.
     */
    private void checkInFunction() {
        if (parkingLot.getOccupied() == -1) {
            System.out.println("The parking lot hasn't been initialised. " +
                    "Please set up a space for the parking lot. Taking you back to main menu.");
            menu("main");
            return;
            // If not initialized then print out messages and direct to main menu.
        } else if (parkingLot.getEmptyLots() == 0) {
            System.out.println("The parking is full. Please come back later. Taking you back to main menu.");
            menu("main");
            return;
            // If there are no empty lot, then print out messages and direct to main menu.
        }

        System.out.println("Please enter the vehicle details");
        String vehicleType = inputVehicleType();
        if (parkingLot.getLotOfVehicle(vehicleType) == 0) {
            System.out.printf("Parking full for %s. Please come back later." +
                    " Taking you back to main menu.\n", vehicleType);
            menu("main");
            return;
        }
        // If there is no reserved lot left for this vehicle, then direct to main menu.

        String regnId = getRengId();

        System.out.print("> Vehicle Model: ");
        String vehicleModel = scanner.nextLine();

        System.out.print("> Vehicle Colour: ");
        String vehicleColour = scanner.nextLine();

        int[] date = inputDate("entry");
        int year = date[0];
        int month = date[1];
        int day = date[2];

        int[] time = inputTime("entry");
        int hour = time[0];
        int minute = time[1];

        Date entryDate = new Date(year, month, day, hour, minute);
        vehicleType = vehicleType.substring(0, 1).toUpperCase() + vehicleType.substring(1);
        // Convert the inputted vehicle type to first letter in capital and rest in lover case.
        switch (vehicleType.toLowerCase()) {
            case "car" -> vehicleList.add(new Car(vehicleType, regnId, vehicleModel, vehicleColour, entryDate));
            case "bike" -> vehicleList.add(new Bike(vehicleType, regnId, vehicleModel, vehicleColour, entryDate));
            case "truck" -> vehicleList.add(new Truck(vehicleType, regnId, vehicleModel, vehicleColour, entryDate));
            case "motorbike" ->
                    vehicleList.add(new Motorbike(vehicleType, regnId, vehicleModel, vehicleColour, entryDate));
        }
        // Depend on the type of vehicle, set the instances value by calling the constructor.
        modifyLots(vehicleType, '-');
        // Deduct the number of reserved lot for this vehicle by 1.
        parkingLot.minusEmptyLots(1);
        parkingLot.addOccupied(1);
        menu("main");
        // Edit the EmptyLots and Occupied, direct back to main menu.
    }

    /**
     * This is the parking lot menu, it prints messages and collect information of length and width of parking lot, if
     * either of this variable less than 7 printout message and collect the information again. It has three functions,
     * initialized the lot, view the lot and return to main menu. If there is any vehicle checked in, the parking lot
     * cannot be reinitialized. After initialized the parking lot, printout the view of parking lot.
     */
    private void parkingLotMenu() {
        System.out.println("Type 'init' to initialise the parking space");
        System.out.println("Type 'view' to view the layout of the parking space");
        System.out.print("Type 'menu' to return to the main menu\n> ");

        String command = scanner.nextLine();
        final int minimumSize = 7;
        switch (command) {
            case "menu" -> menu("main");
            case "init" -> {
                if (parkingLot.getOccupied() <= 0) {
                    System.out.print("Please enter the length of the parking lot.\n> ");
                    int length = Integer.parseInt(scanner.nextLine());
                    while (length < minimumSize) {
                        System.out.print("The length of the parking lot cannot be less than 7. Please re-enter.\n> ");
                        length = Integer.parseInt(scanner.nextLine());
                    }
                    System.out.print("Please enter the width of the parking lot.\n> ");
                    int width = Integer.parseInt(scanner.nextLine());
                    while (width < minimumSize) {
                        System.out.print("The width of the parking lot cannot be less than 7. Please re-enter.\n> ");
                        width = Integer.parseInt(scanner.nextLine());
                    }
                    // Loop until obtain valid length and width for initializing the ParkingLot.

                    System.out.println("Parking Lot Space is setup. Here is the layout -");
                    parkingLot.initLot(length, width);
                    parkingLot.showLot();
                    System.out.println("Press any key to return to parkinglot menu");
                    scanner.nextLine();

                } else {
                    System.out.println("There are vehicles in the Parking Lot," +
                            " you cannot change the space of the parking lot at the moment.");
                    // If there are vehicles in ParkingLot, print out the message.
                }
                parkingLotMenu();
            }

            case "view" -> {
                if (parkingLot.getOccupied() != -1) {
                    parkingLot.showLot();
                } else {
                    System.out.println("The parking lot is not initialised. Please run init!");
                }
                // Display the layout the ParkingLot or print out message when it is not initialized.

                System.out.println("Press any key to return to parkinglot menu");
                scanner.nextLine();
                parkingLotMenu();
            }

            default -> {
                System.out.println("Command not found!");
                parkingLotMenu();
            }
        }
    }

    /**
     * This is the display welcome text method. Run once after the program start.
     */
    private void displayWelcomeText() {

        String titleText =
                " _     _  _______  ___      _______  _______  __   __  _______ \n" +
                        "| | _ | ||       ||   |    |       ||       ||  |_|  ||       |\n" +
                        "| || || ||    ___||   |    |      _||   _   ||       ||    ___|\n" +
                        "|       ||   |___ |   |    |     |  |  | |  ||       ||   |___ \n" +
                        "|       ||    ___||   |___ |     |  |  |_|  || ||_|| ||    ___|\n" +
                        "|   _   ||   |___ |       ||     |_ |       || |\\/|| ||   |___ \n" +
                        "|__| |__||_______||_______||_______||_______||_|   |_||_______|\n" +
                        "_________________________ TO JAVA PARKING _____________________";

        System.out.println(titleText);
        System.out.println();
        /*
         *  Displays the welcome text.
         */
    }

    /**
     * A helper method to determine the String is purely numeric or not, return boolean.
     * @param str Any String.
     * @return If the inputted String is a number, return true, otherwise false.
     */
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
        // A helper method to determine the String is purely numeric or not, return boolean.
    }

    /**
     * This is a method of displaying the parking fee log, after displayed direct back to main menu.
     * @param parkingLot A parkinglot in Parkinglot Class, it contains the information of checkout vehicle.
     */
    private void parkingFeeLog(ParkingLot parkingLot) {
        System.out.println("============ Here are the Transaction logs for the Java Parking Lot =============");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("| Vehicle Type | Registration Id | Entry DateTime | Exit DateTime | Parking Fee |");
        System.out.println("---------------------------------------------------------------------------------");
        parkingLot.printHistoryRecord();
        menu("main");
    }
}

