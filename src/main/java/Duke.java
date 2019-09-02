import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        final String duke_line = "    ____________________________________________________________\n";
        final String duke_indent = "     ";
        //final String data_file_path = "../../../data/duke.txt";
        //final String data_file_path = "src/data/duke.txt";
        final String data_file_path ="data/duke.txt";

        duke_start(duke_line, duke_indent);

        ArrayList<Task> tasks = new ArrayList<>();
        try{
            dukeInit(tasks, data_file_path);
        } catch (DukeException d){
            dukeHandleException(new DukeException("     ☹ OOPS!!! Could not load tasks from hard disk :-("), duke_line);
        }

        Scanner scanner = new Scanner(System.in);
        while(true){
            String user_input;
            if(scanner.hasNextLine()){
                user_input = scanner.nextLine();
            } else {
                try{
                    user_input = scanner.nextLine();
                } catch (NoSuchElementException e){
                    //end of file
                    break;
                }
            }

            if(user_input.equals("bye")){
                duke_bye(duke_line, duke_indent);
                break;
            } else if(user_input.equals("list")){
                duke_list(duke_line, duke_indent, tasks);
            } else if(user_input.length() >= 4 && user_input.substring(0,4).equals("done")){
                try{
                    duke_done(duke_line, duke_indent, tasks, user_input, data_file_path);
                } catch(DukeException d){
                    dukeHandleException(d, duke_line);
                }
            } else if(user_input.length() >= 4 && user_input.substring(0,4).equals("todo")){
                try{
                    duke_toDo(duke_line, duke_indent, tasks, user_input, data_file_path);
                } catch(DukeException d){
                    dukeHandleException(d, duke_line);
                }
            } else if(user_input.length() >= 5 && user_input.substring(0,5).equals("event")){
                try{
                    duke_event(duke_line, duke_indent, tasks, user_input, data_file_path);
                } catch (DukeException d){
                    dukeHandleException(d, duke_line);
                }

            } else if(user_input.length() >= 9 && user_input.substring(0,9).equals("deadline ")){
                try{
                    duke_deadline(duke_line, duke_indent, tasks, user_input, data_file_path);
                } catch(DukeException d){
                    dukeHandleException(d, duke_line);
                }
            } else if(user_input.length() >= 7 && user_input.substring(0,7).equals("delete ")){
                try{
                    dukeDeleteTask(tasks, user_input.substring(7), duke_line, duke_indent);
                } catch(DukeException d){
                    dukeHandleException(d, duke_line);
                }
            } else if(user_input.length() >= 5 && user_input.substring(0,5).equals("find ")){
                dukeFindTask(tasks, user_input.substring(5), duke_line, duke_indent);
            }else {
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

    private static void duke_toDo(String duke_line, String duke_indent, ArrayList<Task> tasks, String user_input, String file_path) throws DukeException{
        user_input = user_input.substring(4).trim();
        if (user_input.equals("")){
            //user did not add any task
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task currTask = new ToDo(user_input);

        try{
            dukeAddTask(currTask, tasks, file_path);
        } catch (DukeException d){
            dukeHandleException(d, duke_line);
        }
        //tasks.add(currTask);
        System.out.println(duke_line);
        System.out.println(duke_indent + "Got it. I've added this task:");
        System.out.println(duke_indent + "  " +currTask);
        System.out.println(duke_indent + "Now you have "+tasks.size()+" tasks in the list.");
        System.out.println(duke_line);
    }

    private static void duke_event(String duke_line, String duke_indent, ArrayList<Task> tasks, String user_input, String file_path) throws DukeException{
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

            try{
                Task currTask = new Event(data[0], DateTimeParser.parseEventInfo(data[1], duke_indent));
                dukeAddTask(currTask, tasks, file_path);
                System.out.println(duke_line);
                System.out.println(duke_indent + "Got it. I've added this task:");
                System.out.println(duke_indent + "  " +currTask);
                System.out.println(duke_indent + "Now you have "+tasks.size()+" tasks in the list.");
                System.out.println(duke_line);
            } catch (DukeException d){
                dukeHandleException(d, duke_line);
            }

        } else {
            //user input format wrong
            throw new DukeException("     ☹ OOPS!!! You must separate the event description and timing with /at ");
        }
    }

    private static void duke_deadline(String duke_line, String duke_indent, ArrayList<Task> tasks, String user_input, String file_path) throws DukeException{
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

            try{
                Task currTask = new Deadline(data[0], DateTimeParser.parseDeadlineInfo(data[1], duke_indent));
                dukeAddTask(currTask, tasks, file_path);
                System.out.println(duke_line);
                System.out.println(duke_indent + "Got it. I've added this task:");
                System.out.println(duke_indent + "  " + currTask);
                System.out.println(duke_indent + "Now you have "+tasks.size()+" tasks in the list.");
                System.out.println(duke_line);
            } catch (DukeException d){
                dukeHandleException(d, duke_line);
            }
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

    private static void duke_done(String duke_line, String duke_indent, ArrayList<Task> tasks, String user_input, String file_path) throws DukeException{
        int task_num;
        try{
            task_num = Integer.parseInt(user_input.substring(5)) - 1;
        } catch (NumberFormatException e){
            throw new DukeException("     ☹ OOPS!!! Invalid done command entered. "+user_input.substring(5)+" is not an integer. Follow this format:done <task number>");
        }
        if(task_num >= 0){
            if(task_num >= tasks.size()){
                task_num++;
                throw new DukeException("     ☹ OOPS!!! Invalid done command entered. "+ task_num + " exceeds the number of tasks in the list.");
            }
            //update hard disk
            try{
                BufferedReader file = new BufferedReader(new FileReader(file_path));
                StringBuffer inputBuffer = new StringBuffer();
                String line;
                int x = 0;

                while((line = file.readLine()) != null){
                    if(x == task_num){
                        String[] contents = line.split("\\|");
                        contents[1] = "1"; //mark as done;
                        line = "";
                        for(int i=0; i<contents.length; i++){
                            line += contents[i];
                            if(i != contents.length-1){
                                line +="|";
                            }
                        }
                    }
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                    x++;
                }
                file.close();

                FileOutputStream fileOut = new FileOutputStream(file_path);
                fileOut.write(inputBuffer.toString().getBytes());
                fileOut.close();
            } catch (IOException e){
                throw new DukeException("     ☹ OOPS!!! Could not update task in hard disk right now :-(");
            }
            tasks.get(task_num).setDone(true);
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

    private static void dukeInit(ArrayList<Task> tasks, String file_path) throws DukeException{
        //load tasks from the file
        try{

            FileReader fr = new FileReader(file_path);
            BufferedReader br = new BufferedReader(fr);

            String str;
            while((str = br.readLine()) != null){
                String[] data = str.split("\\|");
                if(data[0].equals("T")){
                    ToDo toDo = new ToDo(data[2]);
                    if(data[1].equals("1")){
                        toDo.setDone(true);
                    }
                    tasks.add(toDo);
                } else if(data[0].equals("D")){
                    Deadline deadline = new Deadline(data[2], data[3]);
                    if(data[1].equals("1")){
                        deadline.setDone(true);
                    }
                    tasks.add(deadline);
                } else if(data[0].equals("E")){
                    Event event = new Event(data[2], data[3]);
                    if(data[1].equals("1")){
                        event.setDone(true);
                    }
                    tasks.add(event);
                }
            }
            br.close();
        } catch (IOException e){
            throw new DukeException("");
        }
    }

    private static void dukeAddTask(Task task, ArrayList<Task> tasks, String file_path) throws DukeException{
        try{
            //write task to the file
            FileWriter fw = new FileWriter(file_path, true);
            PrintWriter pw = new PrintWriter(fw);

            if(task instanceof ToDo){
                ToDo toDo = (ToDo)task;
                int isDone = (toDo.getIsDone()) ? 1 : 0;
                pw.println("T|" + isDone + "|"+ toDo.getDescription());
            } else if(task instanceof Event){
                Event event = (Event)task;
                int isDone = (event.getIsDone()) ? 1 : 0;
                pw.println("E|" + isDone + "|"+event.getDescription()+"|"+event.getAt());
            } else if(task instanceof Deadline){
                Deadline deadline = (Deadline)task;
                int isDone = (deadline.getIsDone()) ? 1 : 0;
                pw.println("D|"+isDone+"|"+deadline.getDescription()+"|"+deadline.getBy());
            }
            pw.close();
            fw.close();
            tasks.add(task);
        } catch(IOException e){
            //error handling here
            throw new DukeException("     ☹ OOPS!!! Due to some file writing error, this task isn't added :-(");
        }
    }

    private static void dukeDeleteTask(ArrayList<Task> tasks, String num, String duke_line, String duke_indent) throws DukeException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(num);
        } catch (NumberFormatException n) {
            throw new DukeException("     ☹ OOPS!!! Please only enter the task number of the task that you want to delete");
        }

        if (taskNum < 1 || taskNum > tasks.size()) {
            throw new DukeException("     ☹ OOPS!!! Task " + taskNum + " does not exist");
        }

        //valid input -> proceed to delete the task from the file
        try {
            BufferedReader file = new BufferedReader(new FileReader("src/data/duke.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            int x = 1;

            while ((line = file.readLine()) != null) {
                if (x == taskNum) {
                    x++;
                    continue; //skip appending this line
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
                x++;
            }
            file.close();

            FileOutputStream fileOut = new FileOutputStream("src/data/duke.txt");
            fileOut.write(inputBuffer.toString().getBytes());

            System.out.println(duke_line);
            System.out.println(duke_indent + "Noted. I've removed this task:");
            System.out.println(duke_indent + tasks.get(taskNum - 1));
            System.out.println(duke_indent + "Now you have " + (tasks.size() - 1) + " tasks in the list.");
            System.out.println(duke_line);

            //Used collections
            tasks.remove(taskNum - 1);
            fileOut.close();
        } catch (IOException e) {
            throw new DukeException("     ☹ OOPS!!! Could not update task in hard disk right now :-(");
        }
    }
    
    /**
     *  We will loop through the tasks arraylist to find for task descriptions with matching keyword
     * */
    private static void dukeFindTask(ArrayList<Task> tasks, String keyword, String duke_line, String duke_indent){
        System.out.println(duke_line);
        System.out.println(duke_indent+"Here are the matching tasks in your list:");
        int counter = 1;
        for(Task task:tasks){
            if(task.getDescription().contains(keyword)){
                System.out.println(duke_indent+counter+"."+task.getDescription());
                counter++;
            }
        }
        System.out.println(duke_line);
    }
}
