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
    private Bike bike = new Bike();
    private Car car = new Car();

    // Initializing the classes.

    public static void main(String[] args) {
        ParkingLotEngine engine = new ParkingLotEngine();
        // Runs the main game loop.
        engine.startProgram(args);

    }

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
            // Print out the title text and go to main menu.
        }
    }

    private void menu(String status) {
        if (status.equals("main")) {
            if (parkingLot.getEmptyLots() == -1) {
                System.out.println("Empty Lots: [None] | Occupied: [None]");
            } else {
                System.out.println("Empty Lots: " + parkingLot.getEmptyLots() +
                        " | Occupied: " + parkingLot.getOccupied());
            }
            // Depend on whether the ParkingLot is initialized or not, print [None] or int.

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

    private void parkingFunction() {
        System.out.println("To park a vehicle provide the details.");
        if (parkingLot.getOccupied() <= 0) {
            System.out.println("No vehicle checked in the parking lot, taking you back to main menu");
            menu("main");
            return;
        }
        // Case when no vehicle checked in, printout messages and direct to main menu.
        boolean valid = true;
        String vehicleType = "";
        do {
            if (!valid) {
                System.out.println("The vehicle mentioned is not parked in the parking lot.");
            }
            vehicleType = inputVehicleType();
            if (isBike(vehicleType)) {
                valid = bike.isParking();
            } else {
                valid = car.isParking();
            }
        } while (!valid);
        // Loop when the type of vehicle had not checked in.

        String movement = "";
        while (!movement.equals("q")) {
            parkingLot.showLot();
            movement = inputMovement();
            int[] position = {1, 0};
            if (isBike(vehicleType)) {
                int[] previousPosition = bike.getCurrentPosition();
                switch (movement) {
                    case "w" -> bike.setCurrentRow("-");
                    case "s" -> bike.setCurrentRow("+");
                    case "a" -> bike.setCurrentColumn("-");
                    case "d" -> bike.setCurrentColumn("+");
                    case "q" -> {
                        menu("main");
                        return;
                    }
                    default -> {
                    }
                }
                // Depend on the movement inputted, move the bike position or return to main menu.

                position = bike.getCurrentPosition();
                switch (parkingLot.movementResult(position)) {
                    case "good" -> {
                        parkingLot.setLot("B", position);
                        if (!parkingLot.getLotSymbol(previousPosition).equals("D")) {
                            parkingLot.setLot(".", previousPosition);
                        }
                        // If the previous position is not the entry gate, will replace it as ".".
                    }
                    // If a valid movement, modify the layout of ParkingLot.
                    case "door" -> {
                        System.out.println("You cannot exit the parking lot without checkout.");
                        bike.setCurrentPosition(previousPosition);
                    }
                    case "vehicle" -> {
                        System.out.printf("You have hit a %s, there will be a damage fee!\n",
                                parkingLot.movementResult(position));
                        bike.addNumberOfHit(1);
                        bike.setCurrentPosition(previousPosition);
                    }
                    case "pillar", "wall" -> {
                        System.out.printf("You have hit the %s, there will be a damage fee!\n",
                                parkingLot.movementResult(position));
                        bike.addNumberOfHit(1);
                        bike.setCurrentPosition(previousPosition);
                    }
                    // When hit a wall or pillar or vehicle, printout warning and add the number of hit.
                    default -> {
                    }
                }

            } else {
                int[] previousPosition = car.getCurrentPosition();
                switch (movement) {
                    case "w" -> car.setCurrentRow("-");
                    case "s" -> car.setCurrentRow("+");
                    case "a" -> car.setCurrentColumn("-");
                    case "d" -> car.setCurrentColumn("+");
                    case "q" -> {
                        menu("main");
                        return;
                    }
                    default -> {
                    }
                }
                // Depend on the movement inputted, move the bike position or return to main menu.

                position = car.getCurrentPosition();
                switch (parkingLot.movementResult(position)) {
                    case "good" -> {
                        parkingLot.setLot("C", position);
                        if (!parkingLot.getLotSymbol(previousPosition).equals("D")) {
                            parkingLot.setLot(".", previousPosition);
                        }
                        // If the previous position is not the entry gate, will replace it as ".".
                    }
                    // If a valid movement, modify the layout of ParkingLot.
                    case "door" -> {
                        System.out.println("You cannot exit the parking lot without checkout.");
                        car.setCurrentPosition(previousPosition);
                    }
                    case "vehicle" -> {
                        System.out.printf("You have hit a %s, there will be a damage fee!\n",
                                parkingLot.movementResult(position));
                        car.addNumberOfHit(1);
                        car.setCurrentPosition(previousPosition);
                    }
                    case "pillar", "wall" -> {
                        System.out.printf("You have hit the %s, there will be a damage fee!\n",
                                parkingLot.movementResult(position));
                        car.addNumberOfHit(1);
                        car.setCurrentPosition(previousPosition);
                    }
                    // When hit a wall or pillar or vehicle, printout warning and add the number of hit.
                    default -> {
                    }
                }
            }
        }
    }

    private boolean isBike(String vehicleType) {
        return vehicleType.equals("bike") || vehicleType.equals("Bike");
        // To determine the vehicleType is bike or not, if yes then return true otherwise false.
    }

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
        // This function is to loop until obtain desire movement command.
    }

    private void checkOutFunction() {
        if (parkingLot.getOccupied() <= 0) {
            System.out.println("Invalid command! The parking is empty. Taking you back to main menu.");
            menu("main");
            return;
        }
        // When no car parked or ParkingLot not initialized, printout message and direct to main menu.

        System.out.println("Please enter your vehicle details");
        String vehicleType;
        boolean parking;
        do {
            vehicleType = inputVehicleType();
            if (isBike(vehicleType)) {
                parking = bike.isParking();
            } else {
                parking = car.isParking();
            }
            if (!parking) {
                System.out.println("The selected vehicle type is not present in the parking lot.");
            }
        } while (!parking);
        // loop to get the vehicleType that is checked in.

        int[] time = inputTime("exit");
        System.out.println("Please verify your details.");
        if (isBike(vehicleType)) {
            bike.setTotalHour(time);
            System.out.println("Total number of hours: " + bike.getTotalHour());
            System.out.println("Total number of hits:" + bike.getTotalNumberOfHit());
            System.out.println("Vehicle Type: Bike");
            System.out.println("Regn Id: " + bike.getRengId());
            System.out.printf("Total Parking Fee: $%.1f\n", bike.getTotalParkingFee());
        } else {
            car.setTotalHour(time);
            System.out.println("Total number of hours: " + car.getTotalHour());
            System.out.println("Total number of hits:" + car.getTotalNumberOfHit());
            System.out.println("Vehicle Type: Car");
            System.out.println("Regn Id: " + car.getRengId());
            System.out.printf("Total Parking Fee: $%.1f\n", car.getTotalParkingFee());
        }
        // Depend on the vehicle type, display the check-out messages.

        String command = "";
        boolean valid = true;
        do {
            if (!valid) {
                System.out.println("You cannot checkout your vehicle. Please accept the fee by pressing Y" +
                        " or type menu to return to main menu and park your vehicle.");
            } else {
                System.out.println("Type Y to accept the fee or menu to return to main menu");
            }
            System.out.print("> ");
            command = scanner.nextLine();
            valid = command.equals("Y") || command.equals("menu");
        } while (!valid);
        // Loop until obtained "Y" or "menu".

        if (command.equals("Y")) {
            System.out.println("Thank you for visiting Java Parking Lot. See you next time!");
            parkingLot.minusOccupied(1);
            parkingLot.addEmptyLots(1);
            if (isBike(vehicleType)) {
                parkingLot.setLot(".", bike.getCurrentPosition());
                bike = new Bike();
            } else {
                parkingLot.setLot(".", car.getCurrentPosition());
                car = new Car();
            }
        }
        menu("main");
        /*
        If the input is "Y" then print out messages and initialized the vehicle and remove from ParkingLot,
        then go back to main menu. If the input is "menu", then also go back to main menu.
        */
    }

    private int[] inputTime(String state) {
        String timeOfInput = "";
        String[] time;
        int hour = -1, minute = -1;
        boolean valid = false;
        do {
            if (valid) {
                System.out.println("Incorrect time format, please enter time in HH:mm format again!");
            }
            System.out.printf("> Time of %s: ", state);
            timeOfInput = scanner.nextLine();
            if (timeOfInput.length() == 5) {
                time = timeOfInput.split(":");
                hour = Integer.parseInt(time[0]);
                minute = Integer.parseInt(time[1]);
            }
            valid = timeOfInput.charAt(2) != ':' || hour < 0 || hour > 23 || minute < 0 || minute > 59;
        } while (valid);
        return new int[]{hour, minute};
        // This function is used to valid the inputted time and return the hour and minute together as an array.
    }

    private String inputVehicleType() {
        String vehicleType = "";
        do {
            if (!(vehicleType.equals(""))) {
                System.out.println("Invalid detail, please enter detail again!");
            }
            System.out.print("> Vehicle Type: ");
            vehicleType = scanner.nextLine();
        } while (!(vehicleType.equals("car") || vehicleType.equals("bike")
                || vehicleType.equals("Car") || vehicleType.equals("Bike")));
        return vehicleType;
        // Loop to input the valid vehicle type.
    }

    private void checkInFunction() {
        if (parkingLot.getOccupied() == -1) {
            System.out.println("The parking lot hasn't been initialised. " +
                    "Please set up a space for the parking lot. Taking you back to main menu.");
            menu("main");
            return;
            // If not initialized then print out messages and direct to main menu.

        } else if (car.getHour() != -1 && bike.getHour() != -1) {
            System.out.println("The parking already has a car and a bike parked." +
                    " Please come back later. Taking you back to main menu.");
            menu("main");
            return;
            // If there are a car and a bike checked in, then print out messages and direct to main menu.
        }

        System.out.println("Please enter the vehicle details");
        String vehicleType = inputVehicleType();
        if (isBike(vehicleType)) {
            if (bike.getHour() != -1) {
                System.out.println("The parking already has a bike parked." +
                        " Please come back later. Taking you back to main menu.");
                menu("main");
                return;
            }
            //If there is a bike checked in, print out messages and direct to main menu.

        } else {
            if (car.getHour() != -1) {
                System.out.println("The parking already has a car parked." +
                        " Please come back later. Taking you back to main menu.");
                menu("main");
                return;
            }
            //If there is a car checked in, print out messages and direct to main menu.
        }

        String regnId = "";
        do {
            if (!regnId.equals("")) {
                System.out.println("Invalid detail, please enter detail again!");
            }
            System.out.print("> Regn Id: ");
            regnId = scanner.nextLine();
        } while (regnId.length() != 6);
        // Loop to get valid length of 6 regnId.

        System.out.print("> Vehicle Model: ");
        String vehicleModel = scanner.nextLine();

        System.out.print("> Vehicle Colour: ");
        String vehicleColour = scanner.nextLine();

        int[] time = inputTime("entry");
        int hour = time[0];
        int minute = time[1];
        if (isBike(vehicleType)) {
            bike = new Bike(regnId, vehicleModel, vehicleColour, hour, minute, true);
        } else {
            car = new Car(regnId, vehicleModel, vehicleColour, hour, minute, true);
        }
        // Depend on the type of vehicle, set the instances value by calling the constructor.
        parkingLot.minusEmptyLots(1);
        parkingLot.addOccupied(1);
        menu("main");
        // Edit the EmptyLots and Occupied, direct to main menu.
    }

    private void parkingLotMenu() {
        System.out.println("Type 'init' to initialise the parking space");
        System.out.println("Type 'view' to view the layout of the parking space");
        System.out.print("Type 'menu' to return to the main menu\n> ");

        String command = scanner.nextLine();
        switch (command) {
            case "menu" -> menu("main");
            case "init" -> {
                if (parkingLot.getOccupied() <= 0) {
                    System.out.print("Please enter the length of the parking lot.\n> ");
                    int length = Integer.parseInt(scanner.nextLine());
                    while (length < 5) {
                        System.out.print("The length of the parking lot cannot be less than 5. Please re-enter.\n> ");
                        length = Integer.parseInt(scanner.nextLine());
                    }
                    System.out.print("Please enter the width of the parking lot.\n> ");
                    int width = Integer.parseInt(scanner.nextLine());
                    while (width < 5) {
                        System.out.print("The width of the parking lot cannot be less than 5. Please re-enter.\n> ");
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

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
        // A helper function to determine the String is purely numeric or not, return boolean.
    }

}
