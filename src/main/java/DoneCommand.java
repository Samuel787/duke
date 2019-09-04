public class DoneCommand extends Command {

    private int task_num;

    public DoneCommand(TaskList taskList, Storage storage, Ui ui, int task_num){
        super(taskList, storage, ui);
        this.task_num = task_num;
    }

    public void execute() throws DukeException{
        storage.dukeDoneTask(task_num);
        taskList.setDone(task_num);
        ui.markDoneMessage(taskList.get(task_num));
    }
}