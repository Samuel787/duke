package Duke;

public class Deadline extends Task {
    protected String by;

    protected String date;
    protected String time;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public String getBy(){
        return this.by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() +" (by: " +by+")";
    }
}