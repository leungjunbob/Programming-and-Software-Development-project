package entities;
/**
 * This is the Entity class under entities package, it has many inheritance class.
 * @author Junbo Liang 1019905
 *
 */

public abstract class Entity{
    private EntityType type;
    private String symbol;
    private int x;
    private int y;
    protected int hp = 10;
    // Initial hp set to 10.

    /**
     * This method return the remaining hp of this entity.
     * @return Int hp.
     */
    public int getHp (){
        return hp;
    }

    /**
     * This method add or reduce the hp of this entity.
     * @param number A positive or negative int
     */
    public void modifyHp(int number){
        hp += number;
    }

    /**
     * A constructor, when creating an entity need to pass in the type under EntityType and its x, y position.
     * @param type Enum type.
     * @param x X position.
     * @param y Y position.
     */
    public Entity(EntityType type, int x, int y) {
        this.type = type;
        this.symbol = type.getSymbol();
        this.x = x;
        this.y = y;
    }

    /**
     * This method will return the symbol of this entity.
     * @return A String of symbol, such as "A", "B" etc.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * This method return the x position of the entity.
     * @return Int x.
     */
    public int getX() {
        return x;
    }

    /**
     * This method return the x position of the entity.
     * @param x Int x
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * This method return the y position of the entity.
     * @return Int y.
     */
    public int getY() {
        return y;
    }
    /**
     * This method return the x position of the entity.
     * @param y Int y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * A method to return the habitat score of entity.
     * @return Int score
     */
    public abstract int getScore();

    /**
     * This method to return the type of entity as String.
     * @return String entity's type.
     */
    public String getType(){
        return this.type.toString();
    }
}
