package Duke;

/**
 *  The command abstract class is used as a super class for all command types
 *  It primarily contains the TaskList containing all the tasks for easy access
 *  It also contains a Storage Object for duke to interact with the data file when necessary
 *  It also contains a Ui Object to help Duke interact with the user
 */
public abstract class Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;


    /**
     * Contains a reference to the current TaskList, Storage and Ui classes
     * that aids in the implementation and execution of the commands.
     * @param taskList a reference to the current TaskList used by the program
     * @param storage a reference to the current Storage used by the program to access and update the date file
     * @param ui a reference to the current Ui to interact with the user.
     */
    public Command(TaskList taskList, Storage storage, Ui ui){
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * This method contains the implementation instructions for each command
     * @return the message that was printed to the terminal to the user upon execution in String data type
     * @throws DukeException any duke specific exceptions that were thrown during the execution of the program
     */
    abstract public String execute() throws DukeException;

    /**
     * To inform the main Duke class whether to terminate the program or not
     * @return true to exit and false to continue running the program. By default, this method
     * will return false. To return true, this method must be overridden in the subclass.
     */
    public boolean isExit(){
        return false;
    }
}
