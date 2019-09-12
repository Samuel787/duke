package Duke;

import java.util.ArrayList;

/**
 *  The TaskList is an ArrayList containing all the tasks.
 *  This ArrayList will be getting updated along with the data in the file whenever there is some update
 *  action requested by the user.
 *  This serves to increase the speed of execution of the program by avoiding to read the data file
 *  everytime we need information about the task data within the system
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * The ArrayList of tasks provided to this method will be wrapped up as TaskList.
     * This must be provided during the initialization phase of the program.
     * @param tasks an ArrayList of tasks loaded from the data file
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Empty constructor creates an empty ArrayList for tasks
     */
    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds the task to the ArrayList of tasks
     * @param t task to be added to the TaskList
     */
    public void add(Task t){
        tasks.add(t);
    }

    /**
     * Deletes a task from the ArrayList of tasks
     * @param n number of the task in the TaskList. TaskList index starts from 0
     */
    public void delete(int n){
        tasks.remove(n);
    }

    /**
     * Marks the n-th task as done
     * @param n the corresponding number of the task in the TaskList. TaskList index starts from 0
     */
    public void setDone(int n){
        tasks.get(n).setDone(true);
    }

    /**
     * Retrieves the n-th task from the TaskList
     * @param n the corresponding number of the task in the TaskList to be retrieved. TaskList index starts from 0
     * @return n-th task from the taskList
     */
    public Task get(int n){
        return tasks.get(n);
    }

    /**
     * This method informs how many tasks there are currently in the TaskList
     * @return the number of tasks in the tasklist
     */
    public int size(){
        return tasks.size();
    }

}
