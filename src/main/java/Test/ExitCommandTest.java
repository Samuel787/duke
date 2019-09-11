
import Duke.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {

    //basic necessities
    private final String file_path = "dukeTest.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private final String duke_indent;

    public ExitCommandTest(){
        this.duke_indent = "     ";
        ui = new Ui();
        storage = new Storage(file_path);
        try{
            taskList = new TaskList(storage.load());
        }catch(DukeException d){
            ui.showLine();
            ui.showError(d);
            ui.showLine();
            taskList = new TaskList();
        }
    }

    @Test
    public void exitCommand_default_successfulExitMessage(){
        String msg = duke_indent + "Bye. Hope to see you again soon!";
        assertEquals(msg, new ExitCommand(ui).execute());
    }

}
