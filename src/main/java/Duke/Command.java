package Duke;

public abstract class Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;

    public Command(TaskList taskList, Storage storage, Ui ui){
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    abstract public String execute() throws DukeException;

    public boolean isExit(){
        return false;
    }
}
