package entities;
/**
 * This is the Rock class extends Entity and under entities package.
 * @author Junbo Liang 1019905
 *
 */

public class Rock extends Entity{
    /**
     * A constructor, pass the type, x, y to Entity constructor.
     * @param type Enum type.
     * @param x X position.
     * @param y Y position.
     */
    public Rock(EntityType type, int x, int y) {
        super(type, x, y);
    }
    /**
     * Return the score of it.
     * @return 1.
     */
    @Override
    public int getScore() {
        return 1;
    }
}
