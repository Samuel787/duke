package Test;
import Duke.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {

    /**
     *  All commands are executed with
     *  valid command paramaters.
     *
     *  This is strongly enforced with exception catching in
     *  the Parser class.
     *
     *  The Parser class serves to be a filter that filters out
     *  all invalid user requests before initializing them into
     *  commands.
     *
     *  Hence, the purpose of this CommandTest class is merely
     *  to ensure that correct commands are generated.
     *
     * */

    //basic necessities
    private final String file_path = "data/dukeTest.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parserTest;
    private final String duke_indent;

    public AddCommandTest(){
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
    public void addCommand_todoTasks_success(){
        Task task = new ToDo("eat");
        try{
            assertEquals(correctAddMessage(task, taskList), new AddCommand(taskList, storage, ui, task).execute());
        } catch (DukeException d){
            fail(); //test should not fail
        }
    }

    @Test
    public void addCommand_eventTasks_success(){
        Task task = new Event("Study CS2113t", "13/09/2019 1600-1800");
        try{
            assertEquals(correctAddMessage(task, taskList), new AddCommand(taskList, storage, ui, task).execute());
        } catch (DukeException d){
            fail(); //test should not fail
        }
    }

    @Test
    public void addCommand_deadlineTasks_success(){
        Task task = new Deadline("Finish Duke", "12/09/2019 2359");
        try{
            assertEquals(correctAddMessage(task, taskList), new AddCommand(taskList, storage, ui, task).execute());
        } catch (DukeException d){
            fail();
        }
    }

    private String correctAddMessage(Task task, TaskList taskList){
        int numTasks = taskList.size() + 1;
        String msg = duke_indent + "Got it. I've added this task:" + "\n";
        msg += duke_indent + "  " +task + "\n";
        msg += duke_indent + "Now you have "+numTasks+" tasks in the list.";
        return msg;
    }

    @Test
    public void addCommand_wrongDataFile_dukeException(){
        Task task = new ToDo("Wrong Data File");
        try{
            assertEquals("", new AddCommand(taskList, new Storage("src/duke/dataTest.txt"), ui, task).execute());
            fail(); //should not reach this line
        } catch (DukeException d){
            assertEquals("     ☹ OOPS!!! Due to some file writing error, this task isn't added :-(", d.getMessage());
        }
    }
}
