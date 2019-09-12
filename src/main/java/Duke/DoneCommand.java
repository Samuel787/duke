package Duke;

/**
 *  The Done Command, when executed, marks a task as done
 */
public class DoneCommand extends Command {

    private int task_num;

    /**
     * The constructor initializes a Done Command for eventual execution
     * @param taskList a reference to the current TaskList used by the program for easy access to tasks
     * @param storage a reference to the current Storage class used by the program to access and update the date file
     * @param ui a reference to the Ui Object to read and print messages to the terminal to interact with the user
     * @param task_num the number of the task to mark as done. Task numbers are assigned in ascending order (by 0) upon task creation. The number starts from 0
     */
    public DoneCommand(TaskList taskList, Storage storage, Ui ui, int task_num){
        super(taskList, storage, ui);
        this.task_num = task_num;
    }

    /**
     * This method marks the task as done in the data file and the current TaskList that's used
     * by the program
     * @return a String containing the message printed to the user after the execution of this command
     * @throws DukeException is thrown when there is error updating the data file
     */
    public String execute() throws DukeException{
        storage.dukeDoneTask(task_num);
        taskList.setDone(task_num);
        return ui.markDoneMessage(taskList.get(task_num));
    }
}
