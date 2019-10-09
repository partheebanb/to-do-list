import model.NormalItem;
import org.junit.jupiter.api.Test;
import ui.ToDoList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoListTest {

    @Test
    public void testCompleteItem() {
        ToDoList toDoList = new ToDoList();
        NormalItem normalItem = new NormalItem();

        toDoList.getTheList().add(normalItem);
        toDoList.completeItem(1);

        assertEquals("Complete", normalItem.getStatus());
    }
}
