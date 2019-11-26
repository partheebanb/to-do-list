package model;

import model.items.Item;
import model.lists.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class LoadableTest {
    ToDoList toDoList;

    @BeforeEach
    public void initialise() {
        toDoList = new ToDoList("bruh");
    }

    @Test
    public void testLoad() throws IOException {
        Item normalItem1 = new Item();
        Item normalItem2 = new Item();
        normalItem1.createItem("Test", new SimpleDateFormat("20-04-2020"));
        normalItem2.createItem("Test2", new SimpleDateFormat("18-12-2019"));
        toDoList.getTheList().add(normalItem1);
        toDoList.getTheList().add(normalItem2);
        toDoList.save();
        toDoList.load();

        assertEquals(normalItem1.getTitle(), toDoList.getItem(0).getTitle());
        assertEquals(normalItem1.getPriority(), toDoList.getItem(0).getPriority());
        assertEquals(normalItem1.getDueDate(), toDoList.getItem(0).getDueDate());

        assertEquals(normalItem2.getTitle(), toDoList.getItem(1).getTitle());
        assertEquals(normalItem2.getPriority(), toDoList.getItem(1).getPriority());
        assertEquals(normalItem2.getDueDate(), toDoList.getItem(1).getDueDate());
    }
}