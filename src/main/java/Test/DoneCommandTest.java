
import Duke.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DoneCommandTest {

    //basic necessities
    private final String file_path = "dukeTest.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parserTest;
    private final String duke_indent;

    public DoneCommandTest(){
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

        parserTest = new Parser(taskList, storage, ui);
    }

    @Test
    public void successfulDone_doneLastEvent_success(){
        Task task = new Deadline("Task to be marked done", "10/12/2019");

        String msg = duke_indent+"Nice! I've marked this task as done:" + "\n";
        msg += duke_indent + "[D][✓] Task to be marked done (by: 10/12/2019)";

        try{
            new AddCommand(taskList, storage, ui, task).execute();
            assertEquals(msg ,new DoneCommand(taskList,storage, ui, taskList.size()-1).execute());
        } catch (DukeException d){
            System.out.println(d.getMessage());
            fail();
        }
    }

    @Test
    public void doneCommand_wrongFilePath_dukeException(){
        Task task = new Deadline("Task to be marked done", "10/12/2019");

        String msg = duke_indent+"Nice! I've marked this task as done:" + "\n";
        msg += duke_indent + "[D][✓] Task to be marked done (by: 10/12/2019)";

        try{
            new AddCommand(taskList, storage, ui, task).execute();
            assertEquals("",new DoneCommand(taskList,new Storage("jk/duke.txt"), ui, taskList.size()-1).execute());
            fail(); //should not reach this line
        } catch (DukeException d){
            System.out.println(d.getMessage());
            assertEquals("     ☹ OOPS!!! Could not update task in hard disk right now :-(", d.getMessage());
        }
    }
}
