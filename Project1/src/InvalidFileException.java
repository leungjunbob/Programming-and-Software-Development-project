/**
 * This is the InvalidFileException class, will occur when the number of elements in each row does match.
 * @author Junbo Liang 1019905
 *
 */
public class InvalidFileException extends Exception{
    /**
     * A Constructor.
     */
    public InvalidFileException() {
        super("Invalid file content, Aborting mission.");
    }
}
