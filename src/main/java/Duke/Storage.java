package Duke;

import java.io.*;
import java.util.ArrayList;

/**
 * The Storage class interacts with the data file to update the content
 */
public class Storage {

    private String file_path;
    private int numTasks;

    /**
     * The Storage class needs the relative path of the data file so that it can interact with it
     * @param file_path the relative path of the data file
     */
    public Storage(String file_path){
        this.file_path = file_path;
        numTasks = 0;
    }

    /**
     * This method will read all the tasks in the data file, insert them into an ArrayList of Tasks
     * and returns this ArrayList.
     * @return ArrayList of tasks from the data file
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException{
        ArrayList<Task> tasks = new ArrayList<>();
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
                    this.numTasks++;
                } else if(data[0].equals("D")){
                    Deadline deadline = new Deadline(data[2], data[3]);
                    if(data[1].equals("1")){
                        deadline.setDone(true);
                    }
                    tasks.add(deadline);
                    this.numTasks++;
                } else if(data[0].equals("E")){
                    Event event = new Event(data[2], data[3]);
                    if(data[1].equals("1")){
                        event.setDone(true);
                    }
                    tasks.add(event);
                    this.numTasks++;
                }
            }
            br.close();
        } catch (IOException e){
            throw new DukeException("Error parsing data from data file");
        }
        return tasks;
    }

    /**
     * This method takes in a task that needs to be added and appends it to the data file
     * @param task the task that has to be added to the data file
     * @throws DukeException if there is any IOException with file opening, writing or closing, this exception gets thrown
     */
    public void dukeAddTask(Task task) throws DukeException{
        try{
            //write task to the file
            FileWriter fw = new FileWriter(file_path, true);
            PrintWriter pw = new PrintWriter(fw);

            if(task instanceof ToDo){
                ToDo toDo = (ToDo)task;
                int isDone = (toDo.getIsDone()) ? 1 : 0;
                pw.println("T|" + isDone + "|"+ toDo.getDescription());
                this.numTasks++;
            } else if(task instanceof Event){
                Event event = (Event)task;
                int isDone = (event.getIsDone()) ? 1 : 0;
                pw.println("E|" + isDone + "|"+event.getDescription()+"|"+event.getAt());
                this.numTasks++;
            } else if(task instanceof Deadline){
                Deadline deadline = (Deadline)task;
                int isDone = (deadline.getIsDone()) ? 1 : 0;
                pw.println("D|"+isDone+"|"+deadline.getDescription()+"|"+deadline.getBy());
                this.numTasks++;
            }
            pw.close();
            fw.close();
        } catch(IOException e){
            //error handling here
            throw new DukeException("     ☹ OOPS!!! Due to some file writing error, this task isn't added :-(");
        }
    }

    /**
     * This method takes in the taskNum to delete from the data file and deletes that task and updates the data file
     * @param taskNum taskNum refers to the task number of the task. Task number always starts from 0. However, for the users,
     *                taskNum will start from 1. Hence, in the implementation, the taskNum must be decremented by 1 before calling
     *                this method
     * @throws DukeException if there is any IOException with file opening, writing or closing, this exception gets thrown
     */
    public void dukeDeleteTask(int taskNum)throws DukeException{
        try {
            BufferedReader file = new BufferedReader(new FileReader(file_path));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            int line_counter = 0;

            while ((line = file.readLine()) != null) {
                if (line_counter == taskNum) {
                    line_counter++;
                    continue; //skip appending this line
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
                line_counter++;
            }
            file.close();

            FileOutputStream fileOut = new FileOutputStream(file_path);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (IOException e) {
            throw new DukeException("     ☹ OOPS!!! Could not update task in hard disk right now :-(");
        }
    }

    /**
     * This method marks the task corresponding to the taskNum as done in the data file
     * @param taskNum taskNum refers to the task number of the task. This starts from 0. However, for the user, it starts from 1.
     *                Hence, the value has to be decremented by 1 before passing into this method call
     * @throws DukeException if there is any IOException with file opening, writing or closing, this exception gets thrown
     */
    public void dukeDoneTask(int taskNum) throws DukeException{
        //update hard disk
        try{
            BufferedReader file = new BufferedReader(new FileReader(file_path));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            int x = 0;

            while((line = file.readLine()) != null){
                if(x == taskNum){
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
    }



}
