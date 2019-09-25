import model.Item;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ItemTest {

    @Test
    public void testDisplayItem() {
        Item item = new Item();
        String display;

        item.setTitle("Test");
        item.setStatus("Not done");
        item.setDueDate(new SimpleDateFormat("21-04-2020"));
        item.setPriority("High");
        display = item.displayItem(item);

        assertEquals(display, "Title: Test. Priority: High. Status: Not done. Due Date: 21-04-2000");
    }

}