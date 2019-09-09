import java.io.*;
import java.util.ArrayList;

public class Storage {

    private String file_path;
    private int numTasks;

    public Storage(String file_path){
        this.file_path = file_path;
        numTasks = 0;
    }

    /**
     *  This method is invoked upon starting up Duke
     *  It reads all the data from the data file and puts it into an ArrayList
     *  and returns this ArrayList
     * */
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
            throw new DukeException("");
        }
        return tasks;
    }


    /**
     * This method adds the task to the file
     * */
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
     *  This method executes a valid delete command on the data
     * */
    public void dukeDeleteTask(int taskNum)throws DukeException{
        try {
            BufferedReader file = new BufferedReader(new FileReader("src/data/duke.txt"));
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

            FileOutputStream fileOut = new FileOutputStream("src/data/duke.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (IOException e) {
            throw new DukeException("     ☹ OOPS!!! Could not update task in hard disk right now :-(");
        }
    }


    /**
     *  This command executes a valid done command on the data
     * */
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
