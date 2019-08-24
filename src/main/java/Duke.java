import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        final String duke_line = "    ____________________________________________________________\n";
        final String duke_indent = "     ";

        duke_start(duke_line, duke_indent);

        ArrayList<Task> tasks = new ArrayList<>();

        while(true){
            Scanner scanner = new Scanner(System.in);
            String user_input = scanner.nextLine();
            if(user_input.equals("bye")){
                duke_bye(duke_line, duke_indent);
                break;
            } else if(user_input.equals("list")){
                duke_list(duke_line, duke_indent, tasks);
            } else if(user_input.length() >= 5 && user_input.substring(0,5).equals("done ")){
                duke_done(duke_line, duke_indent, tasks, user_input);
            } else if(user_input.length() >= 5 && user_input.substring(0,5).equals("todo ")){
                duke_toDo(duke_line, duke_indent, tasks, user_input);
            } else if(user_input.length() >= 6 && user_input.substring(0,6).equals("event ")){
                duke_event(duke_line, duke_indent, tasks, user_input);
            } else if(user_input.length() >= 9 && user_input.substring(0,9).equals("deadline ")){
                duke_deadline(duke_line, duke_indent, tasks, user_input);
            } else {
                //duke_add(duke_line, duke_indent, tasks, user_input, TaskType.obsolete);
                System.out.println(duke_line);
                System.out.println("Command not recognized");
                System.out.println(duke_line);
            }
        }
    }

    private static void duke_start(String duke_line, String duke_indent){
        System.out.println(duke_line);
        System.out.println(duke_indent + "Hello! I'm Duke\n");
        System.out.println(duke_indent + "What can I do for you?\n");
        System.out.println(duke_line);
    }

    private static void duke_bye(String duke_line, String duke_indent){
        System.out.println(duke_line);
        System.out.println(duke_indent + "Bye. Hope to see you again soon!\n");
        System.out.println(duke_line);
    }

    private static void duke_toDo(String duke_line, String duke_indent, ArrayList<Task> tasks, String user_input){
        user_input = user_input.substring(5);
        Task currTask = new ToDo(user_input);
        tasks.add(currTask);
        System.out.println(duke_line);
        System.out.println(duke_indent + "Got it. I've added this task:");
        System.out.println(duke_indent + "  " +currTask);
        System.out.println(duke_indent + "Now you have "+tasks.size()+" tasks in the list.");
        System.out.println(duke_line);
    }

    private static void duke_event(String duke_line, String duke_indent, ArrayList<Task> tasks, String user_input){
        user_input = user_input.substring(6);
        String[] data = user_input.split("/at", 2);
        if(data.length == 2){
            if(data[1].substring(0,1).equals(" ")){
                data[1] = data[1].substring(1);
            }
            Task currTask = new Event(data[0], data[1]);
            tasks.add(currTask);
            System.out.println(duke_line);
            System.out.println(duke_indent + "Got it. I've added this task:");
            System.out.println(duke_indent + "  " +currTask);
            System.out.println(duke_indent + "Now you have "+tasks.size()+" tasks in the list.");
            System.out.println(duke_line);
        } else {
            //user input format wrong
            System.out.println(duke_line);
            System.out.println(duke_indent + "Wrong input format for Event. Please Enter in this format: ");
            System.out.println(duke_indent + "event <event name> /at <event timing>");
            System.out.println(duke_line);
        }
    }

    private static void duke_deadline(String duke_line, String duke_indent, ArrayList<Task> tasks, String user_input){
        user_input = user_input.substring(9);
        String[] data = user_input.split("/by", 2);
        if(data.length == 2){
            if(data[1].substring(0,1).equals(" ")){
                data[1] = data[1].substring(1);
            }
            Task currTask = new Deadline(data[0], data[1]);
            tasks.add(currTask);
            System.out.println(duke_line);
            System.out.println(duke_indent + "Got it. I've added this task:");
            System.out.println(duke_indent + "  " + currTask);
            System.out.println(duke_indent + "Now you have "+tasks.size()+" tasks in the list.");
            System.out.println(duke_line);
        } else {
            //user input format wrong
            System.out.println(duke_line);
            System.out.println(duke_indent + "Wrong input format for Deadline. Please Enter in this format: ");
            System.out.println(duke_indent + "deadline <topic name> /by <deadline>");
            System.out.println(duke_line);
        }
    }

    private static void duke_list(String duke_line, String duke_indent, ArrayList<Task> tasks){
        System.out.println(duke_line);
        System.out.println(duke_indent + "Here are the tasks in your list:");
        for(int i=1; i<= tasks.size(); i++){
            System.out.println(duke_indent + i+". "+tasks.get(i-1)+"\n");
        }
        System.out.println(duke_line);
    }

    private static void duke_done(String duke_line, String duke_indent, ArrayList<Task> tasks, String user_input){
        try{
            int task_num = Integer.parseInt(user_input.substring(5)) - 1;
            if(task_num >= 0){
                tasks.get(task_num).setDone();
                System.out.println(duke_line);
                System.out.println(duke_indent+"Nice! I've marked this task as done:");
                System.out.println(duke_indent+tasks.get(task_num));
                System.out.println(duke_line);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            System.out.println(duke_line);
            System.out.println(duke_indent + "Wrong format for marking an event as done. Please enter in this format: ");
            System.out.println(duke_indent + "done <event number>");
            System.out.println(duke_line);
        }
    }
}