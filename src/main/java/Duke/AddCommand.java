package Duke;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(TaskList taskList, Storage storage, Ui ui, Task task){
        super(taskList, storage, ui);
        this.task = task;
    }

    public String execute() throws DukeException{
        storage.dukeAddTask(task);
        taskList.add(task);
        return ui.addedMessage(task, taskList.size());
    }
}
