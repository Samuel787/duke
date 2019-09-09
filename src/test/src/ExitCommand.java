public class ExitCommand extends Command{

    public ExitCommand(Ui ui){
        super(null, null, ui);
    }

    @Override
    public void execute(){
        ui.goodByeMessage();
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
