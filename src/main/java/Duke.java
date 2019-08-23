import java.io.IOException;
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
                duke_done(duke_line, duke_indent, tasks, user_input);
            } else {
                duke_add(duke_line, duke_indent, tasks, user_input);
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

    private static void duke_add(String duke_line, String duke_indent, ArrayList<Task> tasks, String user_input){
        tasks.add(new Task(user_input));
        System.out.println(duke_line);
        System.out.println(duke_indent + "added: "+user_input+"\n");
        System.out.println(duke_line);
    }

    private static void duke_list(String duke_line, String duke_indent, ArrayList<Task> tasks){
        System.out.println(duke_line);
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
        } catch (NumberFormatException e){
            duke_add(duke_line, duke_indent, tasks, user_input);
        } catch (IllegalArgumentException e){
            duke_add(duke_line, duke_indent, tasks, user_input);
        }
    }
}