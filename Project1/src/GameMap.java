/**
 * This is the main helper class of the program, which stored the information of the map and a list of entity on this
 * map. It includes methods to return the information stored and methods to add or delete entity, methods to move the
 * entity in the map.
 * @author Junbo Liang 1019905
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import entities.*;

/**
 * The Class of GameMap.
 */
public class GameMap {
    private String[][] map;
    private int length;
    private int width;
    private ArrayList<Entity> entityList = new ArrayList<>();
    private ArrayList<String> printEntityList = new ArrayList<>(Arrays.asList("POTATO", "MINERAL", "SHEEP", "LILY",
            "EUCALYPTUS", "ONION", "ROSE", "APPLE", "BANANA", "COW", "GOAT", "DOG", "ROCK", "TOMATO"));
    private int score = 0;
    // Extra score when killed martian animals, collected mineral or destroyed stone, water plant or feed cattle.
    ArrayList<String> status = new ArrayList<>();

    /**
     * Empty constructor.
     */
    public GameMap() {
    }

    /**
     * A constructor required an ArrayList to set up the map.
     * @param map A String type ArrayList which is the rows from map file.
     */
    public GameMap(ArrayList<String> map) {
        this.width = map.size();
        this.length = map.get(0).length();
        this.map = new String[width][length];
        for (int i = 0; i < map.size(); i++){
            char[] characters = map.get(i).toCharArray();
            for (int j = 0; j < characters.length; j++){
                this.map[i][j] = String.valueOf(characters[j]);
            }
        }
        // Read the map from rows of file, store in the String[][] map symbol by symbol.
    }

    /**
     * Method to return the whole EntityList in this game.
     * @return An Entity type ArrayList.
     */
    public ArrayList<Entity> getEntityList() {
        return entityList;
    }

    /**
     * This method return the result of movement of the entity, will throw an exception when reaching the boundary.
     * @param x x position.
     * @param y y position.
     * @return The symbol in map[y][x]
     * @throws InvalidLocationException If the symbol is "#" which means reaching the boundary of map.
     */
    public String movementResult(int x, int y) throws InvalidLocationException{
        String symbol = this.map[y][x];
        if (symbol.equals("#")){
            throw new InvalidLocationException();
        }
        return symbol;
    }

    /**
     * This method handles the movement process of the entity. By setting the new position on map to this entity's
     * symbol and its previous position as ".".
     * @param symbol String symbol of this entity.
     * @param prevX Previous x position.
     * @param prevY Previous y position.
     * @param newX New x position.
     * @param newY New y position.
     */
    public void moveEntity(String symbol, int prevX, int prevY, int newX, int newY){
        this.map[newY][newX] = symbol;
        this.map[prevY][prevX] = ".";
    }

    /**
     * This method will print the layout of Martian land.
     */
    public void printMap(){
        System.out.println("Here is a layout of Martian land.");
        System.out.println();
        for (int i = 0; i < width; i++){
            for (String symbol : map[i]){
                System.out.print(symbol);
            }
            System.out.println();
        }
        System.out.println();

    }

    /**
     * This method will run after the GameMap is constructed with the contents stored in map file. When travel through
     * the whole map, add all the entities into the EntityList except the rock.
     */
    public void initEntity(){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < map[i].length; j++){
                if (!map[i][j].equals("@")){
                    addEntity(map[i][j], j, i);
                }
            }
        }
        calScore();
    }

    /**
     * This method will add up all the score of entities in the EntityList, and the extra score.
     * @return The total current habitat score.
     */
    public int calScore(){
        int result = 0;
        for (Entity entity : entityList){
            result += entity.getScore();
        }
        return result + this.score;
    }

    /**
     * This method will change the extra score of the habitat.
     * @param number A positive or a negative number to indicate adding or deducting score.
     */
    public void modifyScore(int number){
        this.score += number;
    }

    /**
     * This method will delete the select entity from the EntityList, by its symbol and position.
     * @param symbol A String to indicate which type of entity.
     * @param x Entity's x position.
     * @param y Entity's y position.
     */
    public void deleteEntity(String symbol, int x, int y){
        for (int i = 0; i < entityList.size(); i++){
            Entity entity = entityList.get(i);
            if(entity.getSymbol().equals(symbol) && entity.getX() == x && entity.getY() == y){
                entityList.remove(i);
                return;
            }
        }
    }

    /**
     * This method will return a dog as an Entity.
     * @param x Dog's x position.
     * @param y Dog's y position.
     * @return The selected dog as Entity in the given position.
     */
    public Entity getDog(int x, int y){
        for (Entity entity : entityList){
            if(entity.getX() == x && entity.getY() == y && entity.getType().equals("DOG")){
                return entity;
            }
        }
        return null;
    }

    /**
     * This method will return a Map.
     * @return String[][] type of map.
     */
    public String[][] getMap() {
        return map;
    }

    /**
     * This method will return a boolean to indicate is empty or not in the given x, y position.
     * @param x X position.
     * @param y Y position.
     * @return A boolean whether this space is empty or not.
     */
    public boolean isEmpty(int x, int y){
        return map[y][x].equals(".");
    }

    /**
     * This method will change the symbol of target position of map to the pass in symbol.
     * @param symbol A String symbol of the entity or ".".
     * @param x X position.
     * @param y Y position.
     */
    public void modifyMap(String symbol, int x, int y){
        map[y][x] = symbol;
    }

    /**
     * This method will return the current status of this game.
     * @return A String type ArrayList of entities and the corresponding number.
     */
    public ArrayList<String> getStatus(){
        return this.status;
    }

    /**
     * This method will scan through the whole EntityList and add the entity and the corresponding number to the Hashmap
     * According to the order in printEntityList to add those entities and the numbers into ArrayList status.
     */
    public void computeStatus(){
        Map<String, Integer> entityNumber = new HashMap<>();
        ArrayList<String> status = new ArrayList<>();
        for (Entity entity : entityList){
            entityNumber.put(entity.getType(), entityNumber.getOrDefault(entity.getType(), 0) + 1);
        }
        for(String entity : printEntityList) {
            if (entityNumber.getOrDefault(entity, 0) > 0) {
                status.add(String.format("%s = %d", entity, entityNumber.get(entity)));
            }
        }
        this.status = status;
    }

    /**
     * This method will print the Habitability Status line by line.
     */
    public void printStatus(){
        System.out.println("Habitability Status\n" +
                "======================");
        computeStatus();
        for (String row : status){
            System.out.println(row);
        }
        System.out.println();
    }

    /**
     * This method will create and add the entity into the EntityList. Symbol indicates which type of entity it is and
     * x and y indicate its position.
     * @param symbol A string to indicate which symbol it is.
     * @param x Entity's x position.
     * @param y Entity's y position.
     */
    public void addEntity(String symbol, int x, int y){
        switch (symbol) {
            case "A", "APPLE" -> {
                entityList.add(new Plant(EntityType.APPLE, x, y));
            }
            case "B", "BANANA" -> {
                entityList.add(new Plant(EntityType.BANANA, x, y));
            }
            case "O", "ONION" -> {
                entityList.add(new Plant(EntityType.ONION, x, y));
            }
            case "P", "POTATO" -> {
                entityList.add(new Plant(EntityType.POTATO, x, y));
            }
            case "T", "TOMATO" -> {
                entityList.add(new Plant(EntityType.TOMATO, x, y));
            }
            case "R", "ROSE" -> {
                entityList.add(new Plant(EntityType.ROSE, x, y));
            }
            case "L", "LILY" -> {
                entityList.add(new Plant(EntityType.LILY, x, y));
            }
            case "E", "EUCALYPTUS" -> {
                entityList.add(new Plant(EntityType.EUCALYPTUS, x, y));
            }
            case "C", "COW" -> {
                entityList.add(new EarthAnimal(EntityType.COW, x, y));
            }
            case "D", "DOG" -> {
                entityList.add(new EarthAnimal(EntityType.DOG, x, y));
            }
            case "G", "GOAT" -> {
                entityList.add(new EarthAnimal(EntityType.GOAT, x, y));
            }
            case "S", "SHEEP" -> {
                entityList.add(new EarthAnimal(EntityType.SHEEP, x, y));
            }
            case "J", "JEEBIE" -> {
                entityList.add(new MartianAnimal(EntityType.JEEBIE, x, y));
            }
            case "H", "HEEBIE" -> {
                entityList.add(new MartianAnimal(EntityType.HEEBIE, x, y));
            }
            case "@", "ROCK" -> {
                entityList.add(new Rock(EntityType.ROCK, x, y));
            }
            case "*", "MINERAL" -> {
                entityList.add(new Mineral(EntityType.MINERAL, x, y));
            }
            case "Z" -> {
                entityList.add(new SpaceRobot(EntityType.ROBOT, x, y));
            }
            case "X" -> {
                entityList.add(new SpaceRover(EntityType.ROVER, x, y));
            }
        }
    }
}
