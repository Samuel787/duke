package Duke;

/**
 * The Event is a type of Task. It must contain two crucial pieces of information.
 * It must contain the task description and also the date and start and end timing of the event.
 * The date and timing information must be provided in this specific format:
 * dd/mm/yy hhmm-hhmm
 */
public class Event extends Task {

    protected String at;

    /**
     * To create an Event Task, it requires the description of the event and the event date and timings
     *
     * @param description description of the event
     * @param at the date and timings of the event. This must be in the format dd/mm/yy hhmm-hhmm
     */
    public Event(String description, String at){
        super(description);
        this.at = at;
    }


    /**
     * Returns the event information of the task
     * @return the event date and timings
     */
    public String getAt(){
        return this.at;
    }

    /**
     * String representation of the event Object. It contains the following info:
     * 1. Task Type - Event
     * 2. Whether the task is marked as done
     * 3. Task description
     * 4. The Task's date and timings
     * @return
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: "+this.at+")";
    }
}
