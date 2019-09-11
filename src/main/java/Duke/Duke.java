package Duke;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String file_path){
        ui = new Ui();
        storage = new Storage(file_path);
        try{
            taskList = new TaskList(storage.load());
        } catch (DukeException d){
            ui.showLine();
            ui.showError(d);
            ui.showLine();
            taskList = new TaskList();

        }
    }

    public void run(){
        ui.showWelcome();
        Parser parser = new Parser(taskList, storage, ui);
        boolean isExit = false;
        while(!isExit){
            try{
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute();
                isExit = c.isExit();
            } catch (DukeException d){
                ui.showError(d);
            } finally{
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
