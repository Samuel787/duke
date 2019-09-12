package Duke;


/**
 * This List Command, when executed, lists all the tasks known to Duke
 * in the terminal for the user.
 */
public class ListCommand extends Command{

    /**
     * The constructor initializes a List Command for eventual execution
     * @param taskList a reference to the current TaskList used by the program for easy access to tasks
     * @param ui a reference to the Ui Object to read and print messages to the terminal to interact with the user
     */
    public ListCommand(TaskList taskList, Ui ui){
        super(taskList, null, ui);
    }

    /**
     * This method nicely formats and lists all the tasks in the terminal for the user
     * @return a nicely formatted string representation of all the tasks
     */
    @Override
    public String execute() {
        return ui.listTasks(taskList);
    }

}
