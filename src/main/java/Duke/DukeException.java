package Duke;

/**
 *  This exception is used to catch exceptions specific to Duke
 */
public class DukeException extends Exception {

    /**
     * Sets the message of the exception so that the user will know what caused this exception
     * @param message the message to be printed to the user for error notification
     */
    public DukeException(String message){
        super(message);
    }
}
