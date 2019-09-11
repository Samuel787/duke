package Duke;

public class DeleteCommand extends Command {

    private int taskNum;
    public DeleteCommand(TaskList taskList, Storage storage, Ui ui, int taskNum){
        super(taskList, storage, ui);
        this.taskNum = taskNum;
    }

    public String execute() throws DukeException{
        storage.dukeDeleteTask(taskNum);
        String msg = ui.deletedMessage(taskList.get(taskNum), taskList.size()-1);
        taskList.delete(taskNum);
        return msg;
    }

}
