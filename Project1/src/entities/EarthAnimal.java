package entities;
/**
 * This is the EarthAnimal class extends Entity and under entities package.
 * @author Junbo Liang 1019905
 *
 */
public class EarthAnimal extends Entity{

    /**
     * A constructor, pass the type, x, y to Entity constructor.
     * @param type Enum type.
     * @param x X position.
     * @param y Y position.
     */
    public EarthAnimal(EntityType type, int x, int y) {
        super(type, x, y);
    }
    /**
     * Return the score of it.
     * @return 5.
     */
    @Override
    public int getScore() {
        return 5;
    }

}
