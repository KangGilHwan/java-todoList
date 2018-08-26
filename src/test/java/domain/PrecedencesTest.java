package domain;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PrecedencesTest {

    @Test
    public void checkCompletion_false(){
        Todo todo = Todo.of(1, "빨래", null);
        Todo todo2 = Todo.of(2, "청소", Arrays.asList(todo));
        Todo todo3 = Todo.of(3, "설거지", Arrays.asList(todo, todo2));

        todo.addPrecedence(todo2);
        todo.addPrecedence(todo3);

        todo2.addPrecedence(todo3);
        assertFalse(todo.checkCompletionPrecedences());
        assertFalse(todo2.checkCompletionPrecedences());

        todo3.complete();
        assertTrue(todo2.checkCompletionPrecedences());
    }

    @Test
    public void checkCompletion_true(){
        Todo todo = Todo.of(1, "빨래", null);
        Todo todo2 = Todo.of(2, "청소", Arrays.asList(todo));
        Todo todo3 = Todo.of(3, "설거지", Arrays.asList(todo, todo2));

        todo.addPrecedence(todo2);
        todo.addPrecedence(todo3);
        todo2.addPrecedence(todo3);

        todo3.complete();
        todo2.complete();
        assertTrue(todo2.checkCompletionPrecedences());
        assertTrue(todo.checkCompletionPrecedences());
    }
}
