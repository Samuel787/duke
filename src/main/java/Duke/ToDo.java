package Duke;

/**
 * To-Do Object is a super class of Task.
 * It is the most basic form of a task.
 * It only contains a description.
 * The task can be marked as done
 */
public class ToDo extends Task {

    /**
     * To create a To do task, it requires a description alone
     * @param description description of the task
     */
    public ToDo(String description){
        super(description);
    }


    /**
     * String representation of the To-do Object. It contains the following info:
     * 1. Task type - To do
     * 2. Whether the task is marked as done
     * 3. Task description
     * @return
     */
    @Override
    public String toString(){
        return "[T]"+super.toString();
    }


    
}
