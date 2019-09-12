package Duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class has methods to help read user commands and reply to user commands.
 * It can be taught of as the ears and mouth of Duke
 */
public class Ui {

    final String duke_line;
    final String duke_indent;

    final String logo;

    /**
     * Upon initializing an instance of the Ui, the duke logo will be printed out to the terminal. This initialization must only be done
     * once during the execution of the entire program.
     */
    public Ui(){
        this.duke_line = "    ____________________________________________________________";
        this.duke_indent = "     ";
        this.logo = duke_indent + " ____        _        \n"
                + duke_indent +"|  _ \\ _   _| | _____ \n"
                + duke_indent + "| | | | | | | |/ / _ \\\n"
                + duke_indent + "| |_| | |_| |   <  __/\n"
                + duke_indent + "|____/ \\__,_|_|\\_\\___|\n";

    }

    /**
     * This helper method reads in the command given by the user in the terminal and returns what the user entered in string format
     * @return string of what user entered in the terminal
     */
    public String readCommand(){
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()){
            return scanner.nextLine();
        } else {
            return ""; //simulate enter being pressed
        }
    }

    /**
     * Prints out a single line.
     */
    public void showLine(){
        System.out.println(duke_line);
    }

    /**
     * Prints out the welcome message to the user in the terminal
     */
    public void showWelcome(){
        System.out.println(logo);
        System.out.println(duke_line);
        System.out.println(duke_indent + "Hello! I'm Duke.Duke\n");
        System.out.println(duke_indent + "What can I do for you?\n");
        System.out.println(duke_line);
    }


    /**
     * When an exception specific to duke occurs, it informs the user what caused the exception
     * @param d DukeException containing an error messaging suggesting what caused this exception
     */
    public void showError(DukeException d){
        System.out.println(d.getMessage());
    }


    /**
     * Prints out the good by message to the terminal
     * @return a string containing the goodbye message
     */
    public String goodByeMessage(){
        String msg = duke_indent + "Bye. Hope to see you again soon!";
        System.out.println(duke_indent + "Bye. Hope to see you again soon!");
        return msg;
    }

    /**
     * Informs the user that the task has been marked as done
     * @param task the task that needs to be marked as done
     * @return the message that was printed to the user indicating that the task has been marked as done
     */
    public String markDoneMessage(Task task){
        String msg = duke_indent+"Nice! I've marked this task as done:" + "\n";
        msg += duke_indent + task;

        System.out.println(duke_indent+"Nice! I've marked this task as done:");
        System.out.println(duke_indent+task);

        return msg;
    }

    /**
     * Informs the user that the task has been added to the system
     * @param task the task that has to be added to the system
     * @param numTasks the number of tasks that will be in the system after the addition of the task
     * @return a message that informs the user that the task has been added to the system
     */
    public String addedMessage(Task task, int numTasks) {
        String msg = duke_indent + "Got it. I've added this task:" + "\n";
        msg += duke_indent + "  " +task + "\n";
        msg += duke_indent + "Now you have "+numTasks+" tasks in the list.";

        System.out.println(duke_indent + "Got it. I've added this task:");
        System.out.println(duke_indent + "  " +task);
        System.out.println(duke_indent + "Now you have "+numTasks+" tasks in the list.");

        return msg;
    }

    /**
     * Informs the user that the task has been deleted from th system
     * @param task the task to be deleted
     * @param totalTasks the number of tasks that will be in the system after the deletion of the task
     * @return a message that infroms the user that the task has been removed from the sytem
     */
    public String deletedMessage(Task task, int totalTasks){
        String msg = duke_indent + "Noted. I've removed this task:" + "\n";
        msg += duke_indent + task + "\n";
        msg += duke_indent + "Now you have " + totalTasks + " tasks in the list.";

        System.out.println(duke_indent + "Noted. I've removed this task:");
        System.out.println(duke_indent + task);
        System.out.println(duke_indent + "Now you have " + totalTasks + " tasks in the list.");

        return msg;
    }


    /**
     * Informs the user all the tasks that matches a search phrase
     * @param searchResults an ArrayList of tasks whose descriptions match the search phrase
     * @return a nicely formatted string of the message that was used to inform the user of all the tasks that matches the search phrase
     */
    public String foundTask(ArrayList<Task> searchResults){
        String msg = duke_indent+"Here are the matching tasks in your list:" + "\n";

        System.out.println(duke_indent+"Here are the matching tasks in your list:");
        int counter = 1;
        for(Task task : searchResults){
            System.out.println(duke_indent+counter+"."+task);
            msg += duke_indent+counter+"."+task+"\n";
            counter++;
        }

        return msg;
    }

    /**
     * Informs the user all the tasks that are present in the system
     * @param taskList contains all the tasks that are currently in the system
     * @return a nicely formatted string that was used to inform the user of all the tasks in the system
     */
    public String listTasks(TaskList taskList){
        String msg = "";

        for(int i=1; i <= taskList.size(); i++){
            msg += duke_indent + i +". " +taskList.get(i-1).toString() + "\n";
            System.out.println(duke_indent + i +". " +taskList.get(i-1).toString());
        }
        return msg;
    }

}
