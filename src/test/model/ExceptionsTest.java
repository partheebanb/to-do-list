package model;

import exceptions.ExceededMaxSizeException;
import model.lists.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ExceptionsTest {
    ToDoList toDoList;

    @BeforeEach
    public void before() {
        toDoList = new ToDoList("bruh");
    }
    @Test
    public void TestExceededMaxSizeException() throws ExceededMaxSizeException {
        try {
            toDoList.setSize(toDoList.getMaxSize());
        } catch (ExceededMaxSizeException e) {
            fail();
        }

        try {
            toDoList.setSize(toDoList.getMaxSize() + 1);
        } catch (ExceededMaxSizeException e) {}
    }
}
