package model;

import model.items.Item;
import model.items.NormalItem;
import model.lists.ExamPrepList;
import model.lists.GeneralToDoList;
import model.lists.ToDoList;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ItemTest {

    @Test
    public void testDisplayItem() {
        NormalItem normalItem = new NormalItem( new GeneralToDoList());
        String display;

        normalItem.setTitle("Test");
        normalItem.setStatus("Not done");
        normalItem.setDueDate(new SimpleDateFormat("20-04-2000"));
        normalItem.setPriority("High");
        display = normalItem.displayItem();

        assertEquals(display, "Title: Test. Priority: High. Status: Not done. Due Date: 20-04-2000");
    }

    @Test
    public void testPriorityDecider() {
        Item item = new NormalItem(new GeneralToDoList());
        item = item.priorityDecider("Low");
        assertEquals(item.getPriority(), "Low");
        item = item.priorityDecider("Normal");
        assertEquals(item.getPriority(), "Normal");
        item = item.priorityDecider("Urgent");
        assertEquals(item.getPriority(), "URGENT");
    }
}