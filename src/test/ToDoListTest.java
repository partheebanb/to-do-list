import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoListTest {
    ToDoList toDoList;

    @BeforeEach
    public void declare() {
        toDoList = new ToDoList();
    }
    @Test
    public void testCompleteItem() {
        NormalItem normalItem = new NormalItem();

        toDoList.getTheList().add(normalItem);
        toDoList.completeItem(1);

        assertEquals("Complete", normalItem.getStatus());
    }

    @Test
    public void testSort() {
        Item item1 = new LowItem();
        Item item2 = new NormalItem();
        Item item3 = new UrgentItem();
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
        Item item1 = new NormalItem();
        Item item2 = new NormalItem();

        toDoList.getTheList().add(item1);
        toDoList.getTheList().add(item2);
        toDoList.switchItems(0);
        assertEquals(toDoList.getTheList().get(0), item2);
        assertEquals(toDoList.getTheList().get(1), item1);
    }
}


