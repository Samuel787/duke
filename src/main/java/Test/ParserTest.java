package Test;

import Duke.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    
    //basic necessities
    private final String file_path = "data/dukeTest.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parserTest;
    private final String duke_indent;

    public ParserTest(){
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
    public void dummyTest(){
        try{
            Task task = new ToDo("eat");
            int numTasks = taskList.size() + 1;
            String msg = duke_indent + "Got it. I've added this task:" + "\n";
            msg += duke_indent + "  " +task + "\n";
            msg += duke_indent + "Now you have "+numTasks+" tasks in the list.";

            assertEquals(msg, parserTest.parse("todo eat").execute());
        } catch (Exception e){
            fail(); //test should not fail
            //assertEquals("", e.getMessage());
        }

    }
}
