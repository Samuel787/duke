package Duke;

/**
 * Duke is the entry point of the program.
 * It initializes a TaskList, Storage and Ui which will be used throughout the execution of the program.
 * It will continuously run and interact with the user until the user decides to
 * exit him by typing "bye"
 *
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor initializes a TaskList, Storage and Ui Object which will be used
     * throughout the execution of the program
     * @param file_path the relative path to the data file. This is required to initialize
     *                  the Storage class so that it can interact with the data file
     */
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

    /**
     * This method drives Duke. It first, takes in a user input and then evaluates it
     * to a command if the input is valid. After which, it executes the commnad. In this way
     * duke can interact with the program
     */
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
