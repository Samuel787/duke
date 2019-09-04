public class ListCommand extends Command{

    public ListCommand(TaskList taskList, Ui ui){
        super(taskList, null, ui);
    }

    @Override
    public void execute() {
        ui.listTasks(taskList);
    }

}
