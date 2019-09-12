package Duke;

import java.util.ArrayList;

/**
 * Find Command, when executed, finds all tasks that contains a search phrase.
 * It then notifies the user of all matching tasks.
 */
public class FindCommand extends Command{

    private String keyword;

    /**
     * The constructor initializes a Find Command for eventual execution
     * @param taskList a reference to the current TaskList used by the program for easy access to tasks
     * @param ui a reference to the Ui Object to read and print messages to the terminal to interact with the user
     * @param keyword the search phrase that the user wants to search among the tasks
     */
    public FindCommand(TaskList taskList, Ui ui, String keyword){
        super(taskList, null, ui);
        this.keyword = keyword;
    }

    /**
     * This method searches if the search phrase exists in any of the tasks.
     * It then nicely formats all the positive matches
     * @return a string of the nicely formatted matches
     */
    public String execute(){
        ArrayList<Task> searchResults = new ArrayList<>();
        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).getDescription().contains(keyword)){
                searchResults.add(taskList.get(i));
            }
        }
        return ui.foundTask(searchResults);
    }

}
