
import Duke.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeParserTest {

    @Test
    public void dateValidity_validDate_success(){
        assertEquals(true, DateTimeParser.isDateVaid(29, 2, 2020));
    }

    @Test
    public void dateValidity_invalidDate_fail(){
        assertEquals(false, DateTimeParser.isDateVaid(29, 2, 2019));
    }
}
