public class DeleteCommand extends Command {

    private int taskNum;
    public DeleteCommand(TaskList taskList, Storage storage, Ui ui, int taskNum){
        super(taskList, storage, ui);
        this.taskNum = taskNum;
    }

    public void execute() throws DukeException{
        storage.dukeDeleteTask(taskNum);
        ui.deletedMessage(taskList.get(taskNum), taskList.size()-1);
        taskList.delete(taskNum);
    }

}
