package Duke;

/**
 *  Delete Command, when executed, deletes a task, if it exists, from the data file
 *  and the current TaskList. It then notifies the user the result of the command
 */
public class DeleteCommand extends Command {

    private int taskNum;

    /**
     * The constructor initializes a Delete Command for eventual execution
     * @param taskList a reference to the current TaskList used by the program for easy access to tasks
     * @param storage a reference to the current Storage class used by the program to access and update the date file
     * @param ui a reference to the Ui Object to read and print messages to the terminal to interact with the user
     * @param taskNum the number of the task to delete. Task numbers are assigned in ascending order (by 0) upon task creation. The number starts from 0
     */
    public DeleteCommand(TaskList taskList, Storage storage, Ui ui, int taskNum){
        super(taskList, storage, ui);
        this.taskNum = taskNum;
    }


    /**
     * This method deletes the task from the data file and the current TaskList used by the program. It then prints a message to the user
     * to notify him of the successful deletion of the task.
     * @return a String containing the message printed to the user after the execution of this command
     * @throws DukeException is thrown when there is error accessing, reading or writing to the data file
     */
    public String execute() throws DukeException{
        storage.dukeDeleteTask(taskNum);
        String msg = ui.deletedMessage(taskList.get(taskNum), taskList.size()-1);
        taskList.delete(taskNum);
        return msg;
    }

}
