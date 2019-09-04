import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    /**
     *  this parser will receive the user input and validates it before
     *  expressing it as a command
     * */

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Parser(TaskList taskList, Storage storage, Ui ui){
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    public Command parse(String user_input) throws DukeException{
        user_input = user_input.trim(); //remove white spaces on both sides on input

        if(user_input.equals("bye")){
            return new ExitCommand(this.ui);
        } else if(user_input.equals("list")){
            return new ListCommand(taskList, ui);
        } else if(user_input.length() >= 4 && user_input.substring(0, 4).equals("done")){
            return parseDone(user_input);
        } else if(user_input.length() >= 4 && user_input.substring(0,4).equals("todo")){
            return parseTodo(user_input);
        } else if(user_input.length() >= 5 && user_input.substring(0,5).equals("event")){
            return parseEvent(user_input);
        } else if(user_input.length() >= 9 && user_input.substring(0,9).equals("deadline ")){
            return parseDeadline(user_input);
        } else if(user_input.length() >= 7 && user_input.substring(0,6).equals("delete")){
            return parseDelete(user_input);
        } else if(user_input.length() >= 5 && user_input.substring(0,4).equals("find")){
            return parseFind(user_input);
        } else {
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }



    /**
     *  Generally
     *  0. Parse the data
     *  1. validate the data
     *      1.1 if data is invalid, throw duke exception
     *      1.2 if data is valid, generate the command and return it
     * */

    private AddCommand parseTodo(String todo_input) throws DukeException{
        todo_input = todo_input.substring(4).trim();
        if(todo_input.equals("")){
            //user did not add any task
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }

        //INPUT VALIDATED
        return new AddCommand(taskList, storage, ui, new ToDo(todo_input));
    }

    private AddCommand parseEvent(String event_input) throws DukeException{
        event_input = event_input.substring(5).trim();
        String[] data = event_input.split("/at", 2);
        if(data.length == 2){
            data[0] = data[0].trim();
            data[1] = data[1].trim();

            if(data[0].equals("")){
                throw new DukeException("     ☹ OOPS!!! You need to have some event description!");
            }
            if(data[1].equals("")){
                throw new DukeException("     ☹ OOPS!!! You need to include some timing for the event!");
            }

            //INPUT IS VALIDATED
            return new AddCommand(taskList, storage, ui, new Event(data[0], DateTimeParser.parseEventInfo(data[1])));
        } else {
            //user input format wrong
            throw new DukeException("     ☹ OOPS!!! You must separate the event description and timing with /at ");
        }
    }

    private AddCommand parseDeadline(String deadline_input) throws DukeException{
        deadline_input = deadline_input.substring(9);
        String[] data = deadline_input.split("/by", 2);
        if(data.length == 2){
            data[0] = data[0].trim();
            data[1] = data[1].trim();
            if(data[0].equals("")){
                throw new DukeException("     ☹ OOPS!!! You need to have some task description!");
            }
            if(data[1].equals("")){
                throw new DukeException("     ☹ OOPS!!! You need to include some task deadline after /by");
            }

            //INPUT VALIDATED
            return new AddCommand(taskList, storage, ui, new Deadline(data[0], DateTimeParser.parseDeadlineInfo(data[1])));
        } else {
            //user input format wrong
            throw new DukeException("     ☹ OOPS!!! You must separate the task and deadline with /by ");
        }
    }

    private DeleteCommand parseDelete(String delete_input) throws DukeException{
        delete_input = delete_input.substring(6).trim();
        int taskNum;
        try {
            taskNum = Integer.parseInt(delete_input);
        } catch (NumberFormatException n) {
            throw new DukeException("     ☹ OOPS!!! Please only enter the task number of the task that you want to delete");
        }

        if (taskNum < 1 || taskNum > taskList.size()) {
            throw new DukeException("     ☹ OOPS!!! Task " + taskNum + " does not exist");
        }

        //INPUT VALIDATED
        taskNum--;
        return new DeleteCommand(taskList, storage, ui, taskNum);
    }

    private DoneCommand parseDone(String done_input) throws DukeException{
        int task_num;
        try{
            task_num = Integer.parseInt(done_input.substring(5)) - 1; //task_num rebased to 0 index
        } catch (NumberFormatException e){
            throw new DukeException("     ☹ OOPS!!! Invalid done command entered. "+done_input.substring(5)+" is not an integer. Follow this format:done <task number>");
        }
        if(task_num < 0){
            task_num++;
            throw new DukeException("     ☹ OOPS!!! Invalid done command entered. "+ task_num + " is less than 1.");
        } else if(task_num >= taskList.size()){
            task_num++;
            throw new DukeException("     ☹ OOPS!!! Invalid done command entered. "+ task_num + " exceeds the number of tasks in the list.");
        }

        //INPUT VALIDATED
        return new DoneCommand(taskList, storage, ui, task_num);
    }

    private FindCommand parseFind(String find_input) throws DukeException{
        find_input = find_input.substring(4).trim();

        //INPUT VALIDATED
        return new FindCommand(taskList, ui,find_input);
    }
}
