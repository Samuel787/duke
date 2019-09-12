package Duke;

/**
 *  Exit Command, when executed, notifies the user that Duke is going to close
 *  by printing a good bye message to the screen. Following that,
 *  the isExit method call will return true, prompting Duke to close in its run method.
 */
public class ExitCommand extends Command{

    /**
     * The constructor initializes an Exit Command for eventual execution
     * @param ui a reference to the Ui Object to read and print messages to the terminal to interact with the user
     */
    public ExitCommand(Ui ui){
        super(null, null, ui);
    }

    /**
     * This method merely prints the good bye message to the terminal before ending the program
     * @return returns the good bye message that was printed to the terminal
     */
    @Override
    public String execute(){
        return ui.goodByeMessage();
    }


    /**
     * This method would inform the run method in Duke that the user wants to terminate the program
     * @return true to terminate the program
     */
    @Override
    public boolean isExit(){
        return true;
    }
}
