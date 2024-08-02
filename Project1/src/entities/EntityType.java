package entities;
/**
 * This is the Enum EntityType, which assign which type of entity belong to which subclass inherit from Entity. The
 * type is all in capital and their symbol was assigned with them.
 * @author Junbo Liang 1019905
 *
 */
public enum EntityType {
    COW("C"),
    DOG("D"),
    GOAT("G"),
    SHEEP("S"),
    HEEBIE("H"),
    JEEBIE("J"),
    ROSE("R"),
    LILY("L"),
    EUCALYPTUS("E"),
    ROCK("@"),
    MINERAL("*"),
    APPLE("A"),
    BANANA("B"),
    ONION("O"),
    POTATO("P"),
    TOMATO("T"),
    ROBOT("Z"),
    ROVER("X");

    private String symbol;

    /**
     * A constructor to assign the symbol to this EntityType correspondingly.
     * @param symbol A String symbol of entity.
     */
    EntityType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * A method to return the symbol of this EntityType.
     * @return A String symbol of entity.
     */
    public String getSymbol() {
        return symbol;
    }
}