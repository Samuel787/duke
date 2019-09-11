import Duke.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    //basic necessities
    private final String file_path = "dukeTest.txt";
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
    public void wrongUserInput_randomCommand_DukeException(){
        try{
            parserTest.parse("ilovecs2113t");
            fail(); //should not reach this line
        } catch (DukeException d){
            assertEquals("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(", d.getMessage());
        }

    }

    @Test
    public void wrongDoneUserInput_numExceedsTasks_DukeException(){
        int k = taskList.size();
        k++;
        try{
            parserTest.parse("done "+k);
            fail(); //should not reach this line
        } catch (DukeException d){
            assertEquals("     ☹ OOPS!!! Invalid done command entered. "+ k + " exceeds the number of tasks in the list.", d.getMessage());
        }
    }
}
