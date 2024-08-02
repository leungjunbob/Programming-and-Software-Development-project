package entities;
/**
 * This is the Space rover class extends Entity and under entities package.
 * @author Junbo Liang 1019905
 *
 */
public class SpaceRover extends Entity {
    /**
     * A constructor, pass the type, x, y to Entity constructor.
     * @param type Enum type.
     * @param x X position.
     * @param y Y position.
     */
    public SpaceRover(EntityType type, int x, int y) {
        super(type, x, y);
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
