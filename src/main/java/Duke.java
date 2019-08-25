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
            } else if(user_input.length() >= 4 && user_input.substring(0,4).equals("done")){
                try{
                    duke_done(duke_line, duke_indent, tasks, user_input);
                } catch(DukeException d){
                    dukeHandleException(d, duke_line);
                }
            } else if(user_input.length() >= 4 && user_input.substring(0,4).equals("todo")){
                try{
                    duke_toDo(duke_line, duke_indent, tasks, user_input);
                } catch(DukeException d){
                    dukeHandleException(d, duke_line);
                }
            } else if(user_input.length() >= 5 && user_input.substring(0,5).equals("event")){
                try{
                    duke_event(duke_line, duke_indent, tasks, user_input);
                } catch (DukeException d){
                    dukeHandleException(d, duke_line);
                }

            } else if(user_input.length() >= 9 && user_input.substring(0,9).equals("deadline ")){
                try{
                    duke_deadline(duke_line, duke_indent, tasks, user_input);
                } catch(DukeException d){
                    dukeHandleException(d, duke_line);
                }
            } else {
                dukeHandleException(new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-("), duke_line);
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

    private static void duke_toDo(String duke_line, String duke_indent, ArrayList<Task> tasks, String user_input) throws DukeException{
        user_input = user_input.substring(4).trim();
        if (user_input.equals("")){
            //user did not add any task
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task currTask = new ToDo(user_input);
        tasks.add(currTask);
        System.out.println(duke_line);
        System.out.println(duke_indent + "Got it. I've added this task:");
        System.out.println(duke_indent + "  " +currTask);
        System.out.println(duke_indent + "Now you have "+tasks.size()+" tasks in the list.");
        System.out.println(duke_line);
    }

    private static void duke_event(String duke_line, String duke_indent, ArrayList<Task> tasks, String user_input) throws DukeException{
        user_input = user_input.substring(5);
        String[] data = user_input.split("/at", 2);
        if(data.length == 2){
            data[0] = data[0].trim();
            data[1] = data[1].trim();

            if(data[0].equals("")){
                throw new DukeException("     ☹ OOPS!!! You need to have some event description!");
            }
            if(data[1].equals("")){
                throw new DukeException("     ☹ OOPS!!! You need to include some timing for the event!");
            }

            //Data is okay
            Task currTask = new Event(data[0], data[1]);
            tasks.add(currTask);
            System.out.println(duke_line);
            System.out.println(duke_indent + "Got it. I've added this task:");
            System.out.println(duke_indent + "  " +currTask);
            System.out.println(duke_indent + "Now you have "+tasks.size()+" tasks in the list.");
            System.out.println(duke_line);
        } else {
            //user input format wrong
            throw new DukeException("     ☹ OOPS!!! You must separate the event description and timing with /at ");
        }
    }

    private static void duke_deadline(String duke_line, String duke_indent, ArrayList<Task> tasks, String user_input) throws DukeException{
        user_input = user_input.substring(9);
        String[] data = user_input.split("/by", 2);
        if(data.length == 2){
            data[0] = data[0].trim();
            data[1] = data[1].trim();
            if(data[0].equals("")){
                throw new DukeException("     ☹ OOPS!!! You need to have some task description!");
            }
            if(data[1].equals("")){
                throw new DukeException("     ☹ OOPS!!! You need to include some task deadline after /by");
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
            throw new DukeException("     ☹ OOPS!!! You must separate the task and deadline with /by ");
        }
    }

    private static void duke_list(String duke_line, String duke_indent, ArrayList<Task> tasks){
        System.out.println(duke_line);
        System.out.println(duke_indent + "Here are the tasks in your list:");
        for(int i=1; i<= tasks.size(); i++){
            System.out.println(duke_indent + i+". "+tasks.get(i-1));
        }
        System.out.println(duke_line);
    }

    private static void duke_done(String duke_line, String duke_indent, ArrayList<Task> tasks, String user_input) throws DukeException{
//        try{
            int task_num;
            try{
                task_num = Integer.parseInt(user_input.substring(5)) - 1;
            } catch (NumberFormatException e){
                throw new DukeException("     ☹ OOPS!!! Invalid done command entered. "+user_input.substring(5)+" is not an integer. Follow this format:done <task number>");
            }

            if(task_num >= 0){
                try{
                    tasks.get(task_num).setDone();
                } catch (IndexOutOfBoundsException e){
                    task_num++;
                    throw new DukeException("     ☹ OOPS!!! Invalid done command entered. "+ task_num + " exceeds the number of tasks in the list.");
                }
                System.out.println(duke_line);
                System.out.println(duke_indent+"Nice! I've marked this task as done:");
                System.out.println(duke_indent+tasks.get(task_num));
                System.out.println(duke_line);
            } else {
                task_num++;
                throw new DukeException("     ☹ OOPS!!! Invalid done command entered. "+ task_num + " is less than 1.");
            }
    }

    private static void dukeHandleException(DukeException d, String duke_line){
        System.out.println(duke_line);
        System.out.println(d.getMessage());
        System.out.println(duke_line);
    }
}