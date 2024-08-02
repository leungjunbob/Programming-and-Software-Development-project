package entities;
/**
 * This is the Martian animal class extends Entity and under entities package.
 * @author Junbo Liang 1019905
 *
 */
public class MartianAnimal extends Entity{
    /**
     * A constructor, pass the type, x, y to Entity constructor, and change its hp to 15.
     * @param type Enum type.
     * @param x X position.
     * @param y Y position.
     */
    public MartianAnimal(EntityType type, int x, int y) {
        super(type, x, y);
        hp = 15;
    }
    /**
     * Return the score of it.
     * @return 0.
     */
    @Override
    public int getScore() {
        return 0;
    }
}
