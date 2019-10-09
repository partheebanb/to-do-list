import model.NormalItem;
import model.ToDoList;
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
        NormalItem normalItem1 = new NormalItem();
        NormalItem normalItem2 = new NormalItem();
        normalItem1.createItem("Test", "High", "Incomplete", new SimpleDateFormat("20-04-2020"));
        normalItem2.createItem("Test2", "Low", "Complete", new SimpleDateFormat("18-12-2019"));
        toDoList.load("LoadableTestFile");

        assertEquals(normalItem1.getTitle(), toDoList.getItem(0).getTitle());
        assertEquals(normalItem1.getPriority(), toDoList.getItem(0).getPriority());
        assertEquals(normalItem1.getStatus(), toDoList.getItem(0).getStatus());
        assertEquals(normalItem1.getDueDate(), toDoList.getItem(0).getDueDate());

        assertEquals(normalItem2.getTitle(), toDoList.getItem(1).getTitle());
        assertEquals(normalItem2.getPriority(), toDoList.getItem(1).getPriority());
        assertEquals(normalItem2.getStatus(), toDoList.getItem(1).getStatus());
        assertEquals(normalItem2.getDueDate(), toDoList.getItem(1).getDueDate());
    }
}