import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n"
                        + "    Hello! I'm Duke\n"
                        + "    What can I do for you?\n"
                        + "    ____________________________________________________________\n"
        );

        //Store data
//        ArrayList<String> data = new ArrayList<>();
//        ArrayList<Boolean> bool = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();

        while(true){
            Scanner scanner = new Scanner(System.in);
            String user_input = scanner.nextLine();
            if(user_input.equals("bye")){
                System.out.println("    ____________________________________________________________\n"
                        + "    Bye. Hope to see you again soon!\n"
                        + "    ____________________________________________________________\n"
                );
                break; // breaks out of loop and ends Duke
            } else if(user_input.equals("list")){
                System.out.println("    ____________________________________________________________\n");
                for(int i=1; i<= tasks.size(); i++){

                    System.out.println("    "+ i+"."+tasks.get(i-1)+"\n");
                }
                System.out.println("    ____________________________________________________________\n");
            } else if(user_input.substring(0,4).equals("done")){
                int task_no = Integer.parseInt(user_input.substring(5));
                tasks.get(task_no - 1).setDone();
                System.out.println("    ____________________________________________________________\n");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       "+tasks.get(task_no-1));
                System.out.println("    ____________________________________________________________\n");
            } else {
                tasks.add(new Task(user_input));
                //notify user that the activity is added
                System.out.println("    ____________________________________________________________\n"
                        + "    added: " + user_input + "\n"
                        + "    ____________________________________________________________\n"
                );
            }
        }
    }
}
