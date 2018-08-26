package domain;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReferencesTest {

    @Test
    public void canBeCompletionTest(){
        Todo todo = Todo.of(1, "정리", null);
        Todo todo2 = Todo.of(2, "강아지 집짓기", Arrays.asList(todo));
        Todo todo3 = Todo.of(3, "바닥 청소", Arrays.asList(todo, todo2));

        todo2.complete();
        assertTrue(todo3.isCompletion());
    }
}
