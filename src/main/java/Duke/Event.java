package Duke;

public class Event extends Task {

    protected String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    public String getAt(){
        return this.at;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: "+this.at+")";
    }
}