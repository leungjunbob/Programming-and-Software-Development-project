/**
 * This is the UnknownEntityException class, will occur when the unknown symbol found in the map.
 * @author Junbo Liang 1019905
 *
 */
public class UnknownEntityException extends Exception{
    /**
     * A Constructor.
     */
    public UnknownEntityException() {
        super("An unknown item found in Martian land. Aborting mission.");
    }
}
