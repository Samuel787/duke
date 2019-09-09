import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    final String duke_line;
    final String duke_indent;

    final String logo;

    public Ui(){
        this.duke_line = "    ____________________________________________________________";
        this.duke_indent = "     ";
        this.logo = duke_indent + " ____        _        \n"
                + duke_indent +"|  _ \\ _   _| | _____ \n"
                + duke_indent + "| | | | | | | |/ / _ \\\n"
                + duke_indent + "| |_| | |_| |   <  __/\n"
                + duke_indent + "|____/ \\__,_|_|\\_\\___|\n";

    }

    public String readCommand(){
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()){
            return scanner.nextLine();
        } else {
            return ""; //simulate enter being pressed
        }
    }

    public void showLine(){
        System.out.println(duke_line);
    }

    public String showWelcome(){
        String msg = "";
        msg += logo;
        msg += duke_line;
        msg += duke_indent + "Hello! I'm Duke\n";
        msg += duke_indent + "What can I do for you?\n";
        msg += duke_line;

        System.out.println(logo);
        System.out.println(duke_line);
        System.out.println(duke_indent + "Hello! I'm Duke\n");
        System.out.println(duke_indent + "What can I do for you?\n");
        System.out.println(duke_line);

        return msg;
    }


    public void showError(DukeException d){
        System.out.println(d.getMessage());
    }

    public String goodByeMessage(){
        System.out.println(duke_indent + "Bye. Hope to see you again soon!");
        return duke_indent + "Bye. Hope to see you again soon!";
    }

    public String markDoneMessage(Task task){
        String msg = "";
        msg += duke_indent+"Nice! I've marked this task as done:" +"\n";
        msg += duke_indent+task +"\n";

        System.out.println(duke_indent+"Nice! I've marked this task as done:");
        System.out.println(duke_indent+task);

        return msg;
    }

    public String addedMessage(Task task, int numTasks) {
        String msg = "";
        msg += duke_indent + "Got it. I've added this task:" + "\n";
        msg += duke_indent + "  " +task+"\n";
        msg += duke_indent + "Now you have "+numTasks+" tasks in the list."+"\n";

        System.out.println(duke_indent + "Got it. I've added this task:");
        System.out.println(duke_indent + "  " +task);
        System.out.println(duke_indent + "Now you have "+numTasks+" tasks in the list.");

        return msg;
    }

    public String deletedMessage(Task task, int totalTasks){
        String msg = "";
        msg += duke_indent + "Noted. I've removed this task:" +"\n";
        msg += duke_indent + task + "\n";
        msg += duke_indent + "Now you have " + totalTasks + " tasks in the list."+"\n";

        System.out.println(duke_indent + "Noted. I've removed this task:");
        System.out.println(duke_indent + task);
        System.out.println(duke_indent + "Now you have " + totalTasks + " tasks in the list.");

        return msg;
    }

    public String foundTask(ArrayList<Task> searchResults){
        String msg = "";
        msg += duke_indent+"Here are the matching tasks in your list:" + "\n";

        System.out.println(duke_indent+"Here are the matching tasks in your list:");
        int counter = 1;
        for(Task task : searchResults){
            System.out.println(duke_indent+counter+"."+task);
            msg += duke_indent+counter+"."+task +"\n";
            counter++;
        }
        return msg;
    }

    /**
     *  Lists out all the tasks
     * */
    public String listTasks(TaskList taskList){
        String msg = "";
        for(int i=1; i <= taskList.size(); i++){
            System.out.println(duke_indent + i +". " +taskList.get(i-1).toString());
            msg += duke_indent + i +". " +taskList.get(i-1).toString() +"\n";
        }
        return msg;
    }

}
