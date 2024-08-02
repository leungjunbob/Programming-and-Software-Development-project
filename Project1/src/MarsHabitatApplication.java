/**
 * This is the main class of the Mars habitat program. It includes several menus for different purpose, and several
 * helper methods to achieve the operations and updating the status of entities. And methods to read and write maps and
 * log docs, if some situation happen those methods are able to throw the error and take further action on it.
 * @author Junbo Liang 1019905
 *
 */

import entities.Entity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class is the entry point of your code. This class controls the flow of control for Mission Mars
 */
public class MarsHabitatApplication{
    private Scanner scanner = new Scanner(System.in);
    private GameMap gameMap = new GameMap();
    private ArrayList<Entity> entityList = new ArrayList<>();
    private String logDirection = "resources/habitability.log";

    /**
     * This main method, when the program is executed this method will run first and print the welcome message and direct
     * to the starter method.
     * @param args The input from cmd.
     */
    public static void main(String[] args) {
        MarsHabitatApplication habitat = new MarsHabitatApplication();
        habitat.displayMessage();
        habitat.startProgram(args);
    }

    /**
     * This is the starter method, if there is a destination inputted in cmd, it will go to the open file method
     * directly, otherwise will print the message to ask user input, whether load the default map or manually input the
     * destination of the map file.
     * @param args The input from cmd pass from main method.
     */
    private void startProgram(String[] args){
        String fileDirection = "resources/default.in";
        if (args.length > 0) {
            fileDirection = args[1];
            if (args.length > 2){
                this.logDirection = args[3];
            }
        } else {
            System.out.print("Please enter\n" +
                    "[1] to load Martian map from a file\n" +
                    "[2] to load default Martian map\n" +
                    "> ");
            String input = validInput(new String[]{"1", "2"});
            if (input.equals("1")){
                System.out.print("Enter a file name to setup Martian Land Map\n" +
                        "> ");
                fileDirection = scanner.nextLine();
            }
        }
        openFile(fileDirection);

    }

    /**
     * This is the main menu, will print the message to give user ideas what to input, and base on the user direct to
     * corresponding method.
     */
    private void mainMenu(){
        System.out.print("Please enter\n" +
                "[1] to move Space Robot\n" +
                "[2] to move Space Rover\n" +
                "[3] to move Martian animals\n" +
                "[4] to print the current habitability stats\n" +
                "[5] to print the old habitability stats\n" +
                "[6] to exit\n" +
                "> ");
        String input = validInput(new String[]{"1", "2", "3", "4", "5", "6"});
        switch (input) {
            case "1" -> selectEntityMenu("Space Robot");
            case "2" -> selectEntityMenu("Space Rover");
            case "3" -> selectEntityMenu("Martian animal");
            case "4" -> {
                gameMap.printStatus();
                System.out.printf("Total Habitability Score: %d\n", gameMap.calScore());
                mainMenu();
            }
            case "5" -> printLog();
            case "6" -> {
                exitProgram();
            }
        }
    }

    /**
     *  When user input "5" in the main menu, will be directed to this method. This method will try to open the log
     *  file if it is existed, otherwise will throw the error message.
     */
    private void printLog(){
        try{
            File file = new File(logDirection);
            Scanner fileScanner = new Scanner(file);
            int count = 0;
            while (fileScanner.hasNextLine()){
                String row = fileScanner.nextLine();
                if (row.equals("==START==")){
                    count++;
                    System.out.printf("Program Run :%d\n", count);
                    System.out.println("Habitability Status\n" +
                            "======================");
                }
                if (!row.equals("==START==") && (!row.equals("==END=="))){
                    System.out.println(row);
                }
            }
            // If a line of input is "==START==", then the following line until "==END==" is the status for this run.
        } catch (FileNotFoundException e) {
            System.out.println("Log is not existed");
        }
        mainMenu();
    }

    /**
     * When user input "6" in the main menu, will be directed to this method. This method will ask the user to input the
     * destination to save the map and the log. It will print out the corresponding message when the exception is
     * throws.
     */
    private void exitProgram(){
        System.out.print("Enter a filename for saving Martian Land Map\n> ");
        String[][] map = gameMap.getMap();
        String fileName = scanner.nextLine();
        ArrayList<String> status = gameMap.getStatus();
        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            for (String[] row : map){
                for(String symbol : row){
                    writer.write(symbol);
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException ignored){
            if (fileName.isEmpty()){
                System.out.println("Cannot create file for Martian Land Map.");
            } else {
                System.out.println("Cannot write Martian Land Map to the file.");
            }
        }
        try{
            FileWriter fileWriter = new FileWriter("resources/habitability.log", true);
            fileWriter.write("==START==\n");
            for (String row : status){
                fileWriter.write(row + "\n");
            }
            fileWriter.write("\n");
            String formattedString = String.format("Total Habitability Score: %d\n", gameMap.calScore());
            fileWriter.write(formattedString);
            fileWriter.write("==END==\n");
            fileWriter.close();
        } catch (IOException ignored){
            System.out.println("Cannot write Habitability Status Log in a file.");
        }
        System.out.println("Terminating the mission for now. See you next time.");
    }

    /**
     * This method will return an ArrayList contained all the Entities that specified in the input String.
     * @param type A String to determine which entity is looking for, robot, rover or martian animals.
     * @return An ArrayList with all the selected entities stored in it.
     */
    private ArrayList<Entity> findEntityList(String type){
        this.entityList = gameMap.getEntityList();
        ArrayList<Entity> selectEntityList = new ArrayList<>();
        for (Entity entity : this.entityList){
            if (type.equals("Martian animal")){
                if (entity.getType().equals("HEEBIE") || entity.getType().equals("JEEBIE")){
                    selectEntityList.add(entity);
                }
            } else {
                if (entity.getType().equals(type)) {
                    selectEntityList.add(entity);
                }
            }
        }
        return selectEntityList;
    }

    /**
     * This method will run when user input "1", "2" or "3" in the main menu, according the inputted String type, this
     * method will obtain the list of target entities and printout them for user to select which one want to move.
     * @param type A String to specify which type of entity.
     */
    private void selectEntityMenu(String type){
        ArrayList<Entity> selectEntityList = new ArrayList<>();
        switch (type) {
            case "Space Robot" -> {
                selectEntityList.addAll(findEntityList("ROBOT"));
            }
            case "Space Rover" -> {
                selectEntityList.addAll(findEntityList("ROVER"));
            }
            case "Martian animal" -> {
                selectEntityList.addAll(findEntityList("Martian animal"));
            }
        }
        if (selectEntityList.size() > 0) {
            System.out.printf("There are %d %s found. Select\n", selectEntityList.size(), type);
            String[] option = new String[selectEntityList.size()];
            for (int i = 0; i < selectEntityList.size(); i++) {
                Entity entity = selectEntityList.get(i);
                option[i] = Integer.toString(i + 1);
                if (!type.equals("Martian animal")) {
                    System.out.printf("[%d] for %s at position (%d, %d)\n",
                            i + 1, type, entity.getX(), entity.getY());
                } else {
                    System.out.printf("[%d] for %s at position (%d, %d)\n",
                            i + 1, entity.getType(), entity.getX(), entity.getY());
                }
            }
            System.out.print("> ");
            String input = validInput(option);
            moveMenu(selectEntityList.get(Integer.parseInt(input) - 1));
        } else {
            System.out.printf("No %s found to move.\n", type);
            mainMenu();
        }
    }

    /**
     * This method print the message to guide user can move this entity in following direction. If "1" to "8" entered
     * then will run the method to determine this is a valid move or not. If "0" then go back to main menu.
     * @param entity An Entity type to indicate which entity is going to move
     */
    private void moveMenu(Entity entity){
        String type = entity.getType();
        String printType = null;
        switch (type) {
            case "ROBOT" -> printType = "SpaceRobot";

            case "ROVER" -> printType = "SpaceRover";
            case "JEEBIE", "HEEBIE" -> printType = "MartianAnimal";
        }
        System.out.printf("%s can move in following directions\n" +
                "[1] to move north.\n" +
                "[2] to move west.\n" +
                "[3] to move east.\n" +
                "[4] to move south.\n" +
                "[5] to move north-west.\n" +
                "[6] to move south-west.\n" +
                "[7] to move north-east.\n" +
                "[8] to move south-east.\n" +
                "[0] to go back to main menu\n" +
                "Please enter a direction.\n" +
                "> ", printType);

        String input = validInput(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8"});
        if (input.equals("0")){
            mainMenu();
        } else{
            moveEntity(entity, input);
            moveMenu(entity);
        }
    }

    /**
     * This is method to obtain the desired input, if user enter other input then will print the question message and
     * ask for input again.
     * @param list The list included the desired input.
     * @param print The print message when unexpected input received.
     * @return A valid input which one from the list.
     */
    private String validInput(String[] list, String print){
        boolean state = false;
        String input = null;
        while (!state){
            System.out.println(print);
            System.out.print("> ");
            input = scanner.nextLine();
            for (String s : list) {
                if (input.equals(s)) {
                    state = true;
                }
            }
            if(!state){
                System.out.println("Invalid Command.");
            }
        }
        return input;
    }

    /**
     * This is method to obtain the desired input, if user enter other input then only print the invalid command without
     * repeating the question again.
     * @param list The list included the desired input.
     * @return A valid input which one from the list.
     */
    private String validInput(String[] list){
        boolean state = false;
        String input = null;
        while (!state){
            input = scanner.nextLine();
            for (String s : list) {
                if (input.equals(s)) {
                    state = true;
                }
            }
            if(!state){
                System.out.print("Invalid Command.\n" +
                        "> ");
            }
        }
        return input;
    }

    /**
     * The method to handle the movement of entity. According to the direction inputted to modify the x and y location
     * of the entity. If the movement is not valid then will print the error message and bring the entity back to its
     * previous position. Depend on the type of entity, when they encounter different entity on the map will run the
     * corresponding helper method to handle to further actions.
     * @param entity An Entity type to indicate the selected entity.
     * @param direction The direction inputted by user.
     */
    private void moveEntity(Entity entity, String direction){
        int x = entity.getX();
        int y = entity.getY();
        switch (direction) {
            case "1" -> {
                entity.setY(y - 1);
            }
            case "2" -> {
                entity.setX(x - 1);
            }
            case "3" -> {
                entity.setX(x + 1);
            }
            case "4" -> {
                entity.setY(y + 1);
            }
            case "5" -> {
                entity.setY(y - 1);
                entity.setX(x - 1);
            }
            case "6" -> {
                entity.setY(y + 1);
                entity.setX(x - 1);
            }
            case "7" -> {
                entity.setY(y - 1);
                entity.setX(x + 1);
            }
            case "8" -> {
                entity.setY(y + 1);
                entity.setX(x + 1);
            }
        }
        try{
            String symbol = gameMap.movementResult(entity.getX(), entity.getY());
            boolean move = false;
            // If move is false then will bring the entity back to its previous position.
            switch (symbol) {
                case "P", "T", "O", "R", "L", "A", "B", "E" -> {
                    if (entity.getType().equals("ROBOT")) {
                        robotAction("water the plant", symbol, entity.getX(), entity.getY());
                    } else if (entity.getType().equals("JEEBIE") || entity.getType().equals("HEEBIE")){
                        System.out.println("The plantation are eaten by the martian animals.");
                        entity.modifyHp(2);
                        move = true;
                        gameMap.deleteEntity(symbol, entity.getX(), entity.getY());
                    } else {
                        System.out.println("You cannot move to this location.");
                    }
                }
                case "C", "G", "S", "D" -> {
                    if (entity.getType().equals("ROBOT")) {
                        robotAction("feed the animals", symbol, entity.getX(), entity.getY());
                    } else if (entity.getType().equals("JEEBIE") || entity.getType().equals("HEEBIE")) {
                        if (symbol.equals("D")){
                            if (!dogFight(entity)){
                                System.out.println("Dog died\n");
                                gameMap.modifyMap(".", entity.getX(), entity.getY());
                                gameMap.deleteEntity("D", entity.getX(), entity.getY());
                            } else {
                                System.out.println("Martian Animal died\n");
                                gameMap.deleteEntity(entity.getSymbol(), entity.getX(), entity.getY());
                                gameMap.modifyMap(".", x, y);
                                gameMap.modifyScore(7);
                            }
                        } else {
                            System.out.println("The cattle are killed by the martian animals.");
                            gameMap.deleteEntity(symbol, entity.getX(), entity.getY());
                            entity.modifyHp(2);
                            move = true;
                        }
                    } else {
                        System.out.println("You cannot move to this location.");
                    }
                }
                case "@", "*" -> {
                    if (entity.getType().equals("ROVER")) {
                        if (symbol.equals("@")) {
                            System.out.println("We found a plain rock, Rover will destroy it now.");
                            gameMap.modifyScore(1);
                        } else {
                            System.out.println("We found a mineral, Rover will collect it now.");
                            gameMap.modifyScore(2);
                        }
                        move = true;
                    } else {
                        System.out.println("You cannot move to this location.");
                    }
                }
                case "." -> move = true;
                default -> {
                    move = false;
                    System.out.println("You cannot move to this location.");
                }
            }
            if (move){
                gameMap.moveEntity(entity.getSymbol(), x, y, entity.getX(), entity.getY());
                if (entity.getType().equals("ROBOT") && gameMap.isEmpty(entity.getX() - 1, entity.getY())){
                    robotMenu(entity.getX() - 1, entity.getY());
                }
                // If the select entity is robot, then check the left position of it, empty then can take further action
            } else {
                entity.setX(x);
                entity.setY(y);
            }
            gameMap.printMap();

        } catch (InvalidLocationException e){
            System.out.println(e.getMessage());
            entity.setX(x);
            entity.setY(y);
            // The case when move to the boundary.
        }

    }

    /**
     * This method will run after a robot move to a new place and the left space of it is empty. Allow the robot to
     * plant the plant or rear the cattle.
     * @param x The robot's x position - 1, which is on the left of the robot.
     * @param y The robot's y position.
     */
    private void robotMenu(int x, int y){
        System.out.print("Please select\n" +
                "[1] to plant a tree\n" +
                "[2] to rear cattle\n" +
                "[0] to go back to previous menu\n" +
                "> ");
        String input = validInput(new String[]{"0", "1", "2"});
        switch (input) {
            case "0" -> {
            }
            case "1" -> {
                if (gameMap.isEmpty(x, y)) {
                    robotAction("Plant", "", x, y);
                }
            }
            case "2" -> {
                if (gameMap.isEmpty(x, y)) {
                    robotAction("Rear", "", x, y);
                }
            }
        }
    }

    /**
     * This method will run when the martian animal meet a dog. If one of them hp drops below 0 then will end the fight
     * and return the boolean to indicate who win.
     * @param entity The selected martian animal.
     * @return A boolean to indicate which entity loses.
     */
    private boolean dogFight(Entity entity){
        Entity dog = gameMap.getDog(entity.getX(), entity.getY());
        System.out.println("Martian animal and Dog has entered a fight");
        int hp = dog.getHp();
        Boolean dogRound = false;
        // Ture will indicate that it is dog attacking turn.
        while (hp > 0){
            if (dogRound){
                hp = entity.getHp();
                System.out.printf("Dog attacked Martian Animal." +
                        " Martian Animal's health reduced by 2, Present Health: %d\n", hp - 2);
                entity.modifyHp(-2);
                hp = entity.getHp();
            } else {
                hp = dog.getHp();
                System.out.printf("Martian Animal attacked dog." +
                        " Health of dog reduced by 2, Present Health: %d\n", hp - 2);
                dog.modifyHp(-2);
                hp = dog.getHp();
            }
            dogRound = !dogRound;
            // Change to other entity's turn.
        }
        return !dogRound;
        // If dogRound was true then dog lose, since the boolean is flipped after each round.
    }

    /**
     * The method for robot to do action on water plant, feed animal or plant the plant or rear the cattle.
     * @param type A String to determine which kind of action is going to take.
     * @param symbol A String to indicate which plant or cattle is going to add.
     * @param x The x position of new entity is going to be.
     * @param y The y position of new entity is going to be.
     */
    private void robotAction(String type, String symbol, int x, int y){
        String print;
        String input;
        switch (type) {
            case "water the plant" -> {
                print = "Do you want to water the plant?Enter Y for yes, N for No";
                input = validInput(new String[]{"Y", "N"}, print);
                if (input.equals("Y")) {
                    System.out.println("You watered a plant. It will grow");
                    gameMap.modifyScore(1);
                }
            }
            case "feed the animals" -> {
                print = "Do you want to feed the animals?Enter Y for yes, N for No";
                input = validInput(new String[]{"Y", "N"}, print);
                if (input.equals("Y")) {
                    System.out.println("You have fed the cattle. It will grow");
                    gameMap.modifyScore(1);
                    if (symbol.equals("D")){
                        Entity dog = gameMap.getDog(x, y);
                        dog.modifyHp(1);
                    }
                }
            }
            case "Plant" ->{
                System.out.println("Let's Plant something\n" +
                        "[1] to plant a potato\n" +
                        "[2] to plant a tomato\n" +
                        "[3] to plant an onion\n" +
                        "[4] to plant an apple tree\n" +
                        "[5] to plant a banana tree\n" +
                        "[6] to plant a lily\n" +
                        "[7] to plant a rose\n" +
                        "[8] to plant a eucalyptus tree\n" +
                        "[0] to go back to previous menu");
                input = validInput(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8"});
                switch (input){
                    case "1" -> {
                        symbol = "P";
                        System.out.println("A Potato has been planted.");
                    }
                    case "2" -> {
                        symbol = "T";
                        System.out.println("A Tomato has been planted.");
                    }
                    case "3" -> {
                        symbol = "O";
                        System.out.println("An Onion has been planted.");
                    }
                    case "4" -> {
                        symbol = "A";
                        System.out.println("An Apple has been planted.");
                    }
                    case "5" -> {
                        symbol = "B";
                        System.out.println("A Banana has been planted.");
                    }
                    case "6" -> {
                        symbol = "L";
                        System.out.println("A Lily has been planted.");
                    }
                    case "7" -> {
                        symbol = "R";
                        System.out.println("A Rose has been planted.");
                    }
                    case "8" -> {
                        symbol = "E";
                        System.out.println("An Eucalyptus has been planted.");
                    }
                    case "0" -> {
                        return;
                    }
                }
                gameMap.addEntity(symbol, x, y);
                gameMap.modifyMap(symbol, x, y);
                robotMenu(x, y);
            }
            case "Rear" -> {
                System.out.println("Let's add some cattle\n" +
                        "[1] to add a goat\n" +
                        "[2] to add a sheep\n" +
                        "[3] to add cow\n" +
                        "[4] to add a dog\n" +
                        "[0] to go back to previous menu");
                input = validInput(new String[] {"0", "1", "2", "3", "4"});
                switch (input){
                    case "1" -> {
                        symbol = "G";
                        System.out.println("A Goat has been added.");
                    }
                    case "2" -> {
                        symbol = "S";
                        System.out.println("A Sheep has been added.");
                    }
                    case "3" -> {
                        symbol = "C";
                        System.out.println("A Cow has been added.");
                    }
                    case "4" -> {
                        symbol = "D";
                        System.out.println("A Dog has been added.");
                    }
                    case "0" -> {
                        return;
                    }
                }
                gameMap.addEntity(symbol, x, y);
                gameMap.modifyMap(symbol, x, y);
                robotMenu(x, y);
            }
        }


    }

    /**
     * This method will run when loading the map, if the map contains other symbol or wrong in the format or cannot find
     * the file, will throw the exception.
     * @param fileDirection A String of destination of the file.
     */
    private void openFile(String fileDirection) {
        try {
            File file = new File(fileDirection);
            Scanner fileScanner = new Scanner(file);
            validFile(fileScanner);
            fileScanner.close();
            gameMap.printMap();
            gameMap.initEntity();
            gameMap.printStatus();
            System.out.printf("Total Habitability Score: %d\n", gameMap.calScore());
            mainMenu();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found, aborting mission.");
        } catch (InvalidFileException | UnknownEntityException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method will valid the contents of the file.
     * @param fileScanner A scanner which can read all the contents from the file.
     * @throws InvalidFileException If the number of Character in each row not match will throw this exception.
     * @throws UnknownEntityException If an unknown symbol found will throw the error.
     */
    private void validFile(Scanner fileScanner) throws InvalidFileException, UnknownEntityException{
        ArrayList<String> tempMap = new ArrayList<>();
        int rowLength = -1;
        while (fileScanner.hasNextLine()){
            String row = fileScanner.nextLine();
            if (rowLength == -1 || row.length() == rowLength){
                rowLength = row.length();
                tempMap.add(row);
            } else {
                fileScanner.close();
                throw new InvalidFileException();
            }
        }
        ArrayList<Character> checkList = new ArrayList<>(Arrays.asList('.', '#', 'Z', 'X', 'H', 'J', 'P', 'T', 'O',
                'R', 'L', 'A', 'B', 'E', 'C', 'G', 'S', 'D', '@', '*'));
        for (String row : tempMap){
            for (char c : row.toCharArray()){
                if (!checkList.contains(c)){
                    fileScanner.close();
                    throw new UnknownEntityException();
                }
            }
        }
        this.gameMap = new GameMap(tempMap);
    }


    /**
     * This method prints the starting welcome message. Do not change this code
     */
    private void displayMessage() {
        System.out.println("         __\n" +
                " _(\\    |@@|\n" +
                "(__/\\__ \\--/ __\n" +
                "   \\___|----|  |   __\n" +
                "       \\ }{ /\\ )_ / _\\\n" +
                "       /\\__/\\ \\__O (_COMP90041\n" +
                "      (--/\\--)    \\__/\n" +
                "      _)(  )(_\n" +
                "     `---''---`");
        System.out.println(
                " /$$      /$$ /$$                    /$$                           /$$      /$$                              \n" +
                        "| $$$    /$$$|__/                   |__/                          | $$$    /$$$                              \n" +
                        "| $$$$  /$$$$ /$$  /$$$$$$$ /$$$$$$$ /$$  /$$$$$$  /$$$$$$$       | $$$$  /$$$$  /$$$$$$   /$$$$$$   /$$$$$$$\n" +
                        "| $$ $$/$$ $$| $$ /$$_____//$$_____/| $$ /$$__  $$| $$__  $$      | $$ $$/$$ $$ |____  $$ /$$__  $$ /$$_____/\n" +
                        "| $$  $$$| $$| $$|  $$$$$$|  $$$$$$ | $$| $$  \\ $$| $$  \\ $$      | $$  $$$| $$  /$$$$$$$| $$  \\__/|  $$$$$$ \n" +
                        "| $$\\  $ | $$| $$ \\____  $$\\____  $$| $$| $$  | $$| $$  | $$      | $$\\  $ | $$ /$$__  $$| $$       \\____  $$\n" +
                        "| $$ \\/  | $$| $$ /$$$$$$$//$$$$$$$/| $$|  $$$$$$/| $$  | $$      | $$ \\/  | $$|  $$$$$$$| $$       /$$$$$$$/\n" +
                        "|__/     |__/|__/|_______/|_______/ |__/ \\______/ |__/  |__/      |__/     |__/ \\_______/|__/      |_______/ ");

        System.out.println();
    }
}
