
import Duke.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void checkDone_eventDone_eventIsDone(){
        Task task = new Event("Meow!", "1200-1400");
        task.setDone(true);
        assertEquals("[E][✓] Meow! (at: 1200-1400)", task.toString());
    }
}
