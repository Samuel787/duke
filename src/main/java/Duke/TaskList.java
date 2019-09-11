package Duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public void add(Task t){
        tasks.add(t);
    }

    public void delete(int n){
        tasks.remove(n);
    }

    public void setDone(int n){
        tasks.get(n).setDone(true);
    }

    public Task get(int n){
        return tasks.get(n);
    }

    public int size(){
        return tasks.size();
    }

}
