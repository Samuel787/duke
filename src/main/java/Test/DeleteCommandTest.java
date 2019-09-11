
import Duke.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {

    //basic necessities
    private final String file_path = "dukeTest.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parserTest;
    private final String duke_indent;

    public DeleteCommandTest(){
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
    public void deleteCommand_deleteExistingTask_success(){
        Task task = new ToDo("Task to be deleted");
        String msg = duke_indent + "Noted. I've removed this task:" + "\n";
        msg += duke_indent + task + "\n";
        msg += duke_indent + "Now you have " + taskList.size() + " tasks in the list.";
        try{
            new AddCommand(taskList, storage, ui, task).execute();
            String k =  new DeleteCommand(taskList, storage, ui, taskList.size()-1).execute();
            assertEquals(msg,k);
        } catch(DukeException d){
            System.out.println(d.getMessage());
            fail();
        }
    }

    @Test
    public void deleteCommand_wrongFilePath_dukeException(){
        Task task = new ToDo("Task to be deleted");
        String msg = duke_indent + "Noted. I've removed this task:" + "\n";
        msg += duke_indent + task + "\n";
        msg += duke_indent + "Now you have " + taskList.size() + " tasks in the list.";
        try{
            new AddCommand(taskList, storage, ui, task).execute();
            assertEquals("",new DeleteCommand(taskList,new Storage("jk/duke.txt"), ui, taskList.size()-1).execute());
            fail(); //should not reach this line
        } catch(DukeException d){
            assertEquals("     ☹ OOPS!!! Could not update task in hard disk right now :-(", d.getMessage());
        }
    }
}
