package Duke;

/**
 *  AddCommand, when executed, adds a Task to the data file, the current TaskList
 *  and notifies user that the task has been added by printing out an
 *  added message to the terminal
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * This constructor prepares an AddCommand for execution by getting a reference
     * to the current TaskList, Storage, Ui and the Task to be added
     * @param taskList a reference to the current TaskList containing all the tasks
     * @param storage a reference to the current storage class to update the task in the data file
     * @param ui a reference to the current ui instance to notify user that the task has been added upon execution
     * @param task the task to be added to Duke
     */
    public AddCommand(TaskList taskList, Storage storage, Ui ui, Task task){
        super(taskList, storage, ui);
        this.task = task;
    }

    /**
     * Upon execution, this method would firstly update the data file with the task.
     * Then, it would add the task to the current TaskList that's running with the program for easy access.
     * Then it prints to the terminal to notify the user that the task has been added upon successful execution.
     *
     * @return the message that was printed to the user
     * @throws DukeException if there was an error when writing to the data file
     */
    public String execute() throws DukeException{
        storage.dukeAddTask(task);
        taskList.add(task);
        return ui.addedMessage(task, taskList.size());
    }
}
