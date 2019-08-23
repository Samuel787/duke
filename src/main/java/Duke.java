import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        final String duke_line = "    ____________________________________________________________\n";
        final String duke_indent = "     ";

        duke_start(duke_line, duke_indent);

        ArrayList<String> tasks = new ArrayList<>();

        while(true){
            Scanner scanner = new Scanner(System.in);
            String user_input = scanner.nextLine();
            if(user_input.equals("bye")){
                duke_bye(duke_line, duke_indent);
                break;
            } else if(user_input.equals("list")){
                duke_list(duke_line, duke_indent, tasks);
            }
            else {
                tasks.add(user_input);
                duke_add(duke_line, duke_indent, user_input);
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

    private static void duke_add(String duke_line, String duke_indent, String task){
        System.out.println(duke_line);
        System.out.println(duke_indent + "added: "+task+"\n");
        System.out.println(duke_line);
    }

    private static void duke_list(String duke_line, String duke_indent, ArrayList<String> tasks){
        System.out.println(duke_line);
        for(int i=1; i<= tasks.size(); i++){
            System.out.println(duke_indent + i+". "+tasks.get(i-1)+"\n");
        }
        System.out.println(duke_line);
    }
}