package Duke;

public class ListCommand extends Command{

    public ListCommand(TaskList taskList, Ui ui){
        super(taskList, null, ui);
    }

    @Override
    public String execute() {
        return ui.listTasks(taskList);
    }

}
