package Duke;

/**
 * The Deadline is a type of Task. It must contain two crucial pieces of information.
 * It must contain the task description and also the date and time the task is due.
 */
public class Deadline extends Task {
    protected String by;

    protected String date;
    protected String time;

    /**
     * To create a Deadline Task, it requires a description of the deadline and the deadline date
     * @param description description of the task
     * @param by the date and time when the task is due. This must be in the format dd/mm/yyyy hhmm
     */
    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline information of the task
     * @return the deadline date and time of the task
     */
    public String getBy(){
        return this.by;
    }

    /**
     * String representation of the deadline Object. It contains the following info:
     * 1. Task type - Deadline
     * 2. Whether the task is marked as done
     * 3. Task description
     * 4. The Task's deadline date and time
     * @return deadline task information in a String
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() +" (by: " +by+")";
    }
}
