
import Duke.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void checkDone_deadlineDone_taskIsDone(){
        Task task = new Deadline("Meow!", "1200");
        task.setDone(true);
        assertEquals("[D][✓] Meow! (by: 1200)", task.toString());
    }
}
