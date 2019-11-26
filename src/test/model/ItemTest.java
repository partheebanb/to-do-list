package model;

import model.items.Item;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ItemTest {

    @Test
    public void testDisplayItem() {
        Item normalItem = new Item();
        String display;

        normalItem.setTitle("Test");
        normalItem.setDueDate(new SimpleDateFormat("20-04-2000"));
        normalItem.setPriority("High");
        display = normalItem.displayItem();

        assertEquals(display, "Title: Test. Priority: High. Status: Not done. Due Date: 20-04-2000");
    }

}