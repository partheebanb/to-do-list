import model.Item;
import ui.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
        Item item = new Item();

        item.createItem("Test", "High", "Complete", new SimpleDateFormat("20-04-2020"));
        toDoList.getTheList().add(item);
        toDoList.save("SaveableTestFile");
        toDoList.load("SaveableTestFile");

        assertEquals(item.getTitle(), toDoList.getItem(0).getTitle());
        assertEquals(item.getPriority(), toDoList.getItem(0).getPriority());
        assertEquals(item.getStatus(), toDoList.getItem(0).getStatus());
        assertEquals(item.getDueDate(), toDoList.getItem(0).getDueDate());

    }
}
