package Duke;

public class ExitCommand extends Command{

    public ExitCommand(Ui ui){
        super(null, null, ui);
    }

    @Override
    public String execute(){
        return ui.goodByeMessage();
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
