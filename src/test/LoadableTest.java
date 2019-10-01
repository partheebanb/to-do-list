import model.Item;
import ui.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class LoadableTest {
    ToDoList toDoList;

    @BeforeEach
    public void initialise() {
        toDoList = new ToDoList();
    }

    @Test
    public void testLoad() throws IOException {
        Item item1 = new Item();
        Item item2 = new Item();
        item1.createItem("Test", "High", "Incomplete", new SimpleDateFormat("20-04-2020"));
        item2.createItem("Test2", "Low", "Complete", new SimpleDateFormat("18-12-2019"));
        toDoList.load("LoadableTestFile");

        assertEquals(item1.getTitle(), toDoList.getItem(0).getTitle());
        assertEquals(item1.getPriority(), toDoList.getItem(0).getPriority());
        assertEquals(item1.getStatus(), toDoList.getItem(0).getStatus());
        assertEquals(item1.getDueDate(), toDoList.getItem(0).getDueDate());

        assertEquals(item2.getTitle(), toDoList.getItem(1).getTitle());
        assertEquals(item2.getPriority(), toDoList.getItem(1).getPriority());
        assertEquals(item2.getStatus(), toDoList.getItem(1).getStatus());
        assertEquals(item2.getDueDate(), toDoList.getItem(1).getDueDate());
    }
}