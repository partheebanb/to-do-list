import model.NormalItem;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class SaveableTest {
    ToDoList toDoList;

    @BeforeEach
    public void initialize() {
        toDoList = new ToDoList();
    }

    @Test
    public void testSave() throws IOException {
        NormalItem normalItem = new NormalItem();

        normalItem.createItem("Test", "High", "Complete", new SimpleDateFormat("20-04-2020"));
        toDoList.getTheList().add(normalItem);
        toDoList.save("SaveableTestFile");
        toDoList.load("SaveableTestFile");

        assertEquals(normalItem.getTitle(), toDoList.getItem(0).getTitle());
        assertEquals(normalItem.getPriority(), toDoList.getItem(0).getPriority());
        assertEquals(normalItem.getStatus(), toDoList.getItem(0).getStatus());
        assertEquals(normalItem.getDueDate(), toDoList.getItem(0).getDueDate());

    }
}
