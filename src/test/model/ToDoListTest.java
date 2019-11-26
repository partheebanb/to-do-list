package model;

import exceptions.ExceededMaxSizeException;
import model.items.Item;
import model.lists.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoListTest {
    ToDoList toDoList;

    @BeforeEach
    public void declare() {
        toDoList = new ToDoList("bruh");
    }
    @Test
    public void testCompleteItem() {
        Item normalItem = new Item();

        try {
            toDoList.addItem(normalItem);
        } catch (ExceededMaxSizeException e) {
            fail();
        }
        toDoList.removeItem(1);

        assertEquals(toDoList.getTheList().size(), 0);
    }

    @Test
    public void testSort() throws ExceededMaxSizeException {
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        item1.setPriority("Low");
        item2.setPriority("Normal");
        item3.setPriority("Urgent");
        toDoList.addItem(item1);
        toDoList.addItem(item2);
        toDoList.addItem(item3);

        toDoList.sort();
        assertEquals((toDoList.getTheList().get(0)), item3);
        assertEquals((toDoList.getTheList().get(1)), item2);
        assertEquals((toDoList.getTheList().get(2)), item1);


    }

    @Test
    public void testSwitch() throws ExceededMaxSizeException {
        Item item1 = new Item();
        Item item2 = new Item();

        toDoList.addItem(item1);
        toDoList.addItem(item2);
        toDoList.switchItems(0);
        assertEquals(toDoList.getItem(0), item2);
        assertEquals(toDoList.getItem(1), item1);
    }
}


