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

        while(true){
            Scanner scanner = new Scanner(System.in);
            String user_input = scanner.nextLine();
            if(user_input.equals("bye")){
                System.out.println("    ____________________________________________________________\n"
                        + "    Bye. Hope to see you again soon!\n"
                        + "    ____________________________________________________________\n"
                );
                break; // breaks out of loop and ends Duke
            } else {
                System.out.println("    ____________________________________________________________\n"
                        + "    " + user_input + "\n"
                        + "    ____________________________________________________________\n"
                );
            }
        }
    }
}
