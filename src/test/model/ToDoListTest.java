package model;

import exceptions.ExceededMaxSizeException;
import model.items.Item;
import model.items.LowItem;
import model.items.NormalItem;
import model.items.UrgentItem;
import model.lists.ExamPrepList;
import model.lists.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoListTest {
    ToDoList toDoList;

    @BeforeEach
    public void declare() {
        toDoList = new ExamPrepList();
    }
    @Test
    public void testCompleteItem() {
        NormalItem normalItem = new NormalItem();

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
        Item item1 = new LowItem();
        Item item2 = new NormalItem();
        Item item3 = new UrgentItem();
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
        Item item1 = new NormalItem();
        Item item2 = new NormalItem();

        toDoList.addItem(item1);
        toDoList.addItem(item2);
        toDoList.switchItems(0);
        assertEquals(toDoList.getItem(0), item2);
        assertEquals(toDoList.getItem(1), item1);
    }
}


