/**
 * This is the InvalidLocationException class, will occur when the select entity move to the boundary of map.
 * @author Junbo Liang 1019905
 *
 */
public class InvalidLocationException extends Exception{
    /**
     * A Constructor.
     */
    public InvalidLocationException(){
        super("Invalid Location, Boundary reached.");
    }
}
