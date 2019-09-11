package Duke;

public abstract class Task{
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean x){
        this.isDone = x;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public String getDescription(){
        return this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString(){
        return "["+getStatusIcon()+"] "+this.description;
    }

}