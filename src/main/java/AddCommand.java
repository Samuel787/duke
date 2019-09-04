public class AddCommand extends Command {

    private Task task;

    public AddCommand(TaskList taskList, Storage storage, Ui ui, Task task){
        super(taskList, storage, ui);
        this.task = task;
    }

    public void execute() throws DukeException{
        storage.dukeAddTask(task);
        taskList.add(task);
        ui.addedMessage(task, taskList.size());
    }
}
