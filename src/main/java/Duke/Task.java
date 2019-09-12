package Duke;

/**
 *  Task is the abstract superclass for all Tasks such as To-do, Event and deadline.
 *  It must minimally contain a task description and a boolean indicating if the task
 *  has been marked as done.
 */

public abstract class Task{
    protected String description;
    protected boolean isDone;

    /**
     * Any tasks must have a description. By default, the task will be considered as not done upon creation.
     * @param description a description or a name or a title of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method can be called with the respective boolean to mark it as done or not
     * @param x the new done state of the task. True means that the task is done. False means that the task is not done.
     */
    public void setDone(boolean x){
        this.isDone = x;
    }


    /**
     * Informs whether the task has been saved as completed or not in the system
     * @return the done state of the task
     */
    public boolean getIsDone(){
        return this.isDone;
    }

    /**
     * Provides the description or name or title of the task as saved in the system
     * @return the description or name or title of the task as saved in the system
     */
    public String getDescription(){
        return this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Nicely formats the task in a string format with the following information:
     * -Whether task has been done
     * -task description
     * @return string with all information of the selected task
     */
    @Override
    public String toString(){
        return "["+getStatusIcon()+"] "+this.description;
    }

}