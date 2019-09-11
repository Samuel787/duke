package Duke;

import java.util.ArrayList;

public class FindCommand extends Command{

    private String keyword;

    public FindCommand(TaskList taskList, Ui ui, String keyword){
        super(taskList, null, ui);
        this.keyword = keyword;
    }

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
