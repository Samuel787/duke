
import Duke.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void checkDone_todoDone_taskIsDone(){
        Task task = new ToDo("Meow!");
        task.setDone(true);
        assertEquals("[T][\u2713] Meow!", task.toString());
    }
}
