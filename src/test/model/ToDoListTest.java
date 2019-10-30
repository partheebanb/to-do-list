package model;

import exceptions.ExceededMaxSizeException;
import model.items.Item;
import model.items.LowItem;
import model.items.NormalItem;
import model.items.UrgentItem;
import model.lists.GeneralToDoList;
import model.lists.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoListTest {
    ToDoList toDoList;

    @BeforeEach
    public void declare() {
        toDoList = new GeneralToDoList();
    }
    @Test
    public void testCompleteItem() {
        NormalItem normalItem = new NormalItem(new GeneralToDoList());

        toDoList.getTheList().add(normalItem);
        toDoList.removeItem(1);

        assertEquals(toDoList.getTheList().size(), 0);
    }

    @Test
    public void testSort() throws ExceededMaxSizeException {
        Item item1 = new LowItem(new GeneralToDoList());
        Item item2 = new NormalItem(new GeneralToDoList());
        Item item3 = new UrgentItem(new GeneralToDoList());
        toDoList.getTheList().add(item1);
        toDoList.getTheList().add(item2);
        toDoList.getTheList().add(item3);
        toDoList.setSize(3);

        toDoList.sort();
        assertEquals((toDoList.getTheList().get(0)), item3);
        assertEquals((toDoList.getTheList().get(1)), item2);
        assertEquals((toDoList.getTheList().get(2)), item1);


    }

    @Test
    public void testSwitch() {
        Item item1 = new NormalItem(new GeneralToDoList());
        Item item2 = new NormalItem(new GeneralToDoList());

        toDoList.getTheList().add(item1);
        toDoList.getTheList().add(item2);
        toDoList.switchItems(0);
        assertEquals(toDoList.getTheList().get(0), item2);
        assertEquals(toDoList.getTheList().get(1), item1);
    }
}


