import model.Item;
import org.junit.jupiter.api.Test;
import ui.ToDoList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoListTest {

    @Test
    public void testCompleteItem() {
        ToDoList toDoList = new ToDoList();
        Item item = new Item();

        toDoList.getTheList().add(item);
        toDoList.completeItem(1);

        assertEquals("Complete", item.getStatus());
    }
}
