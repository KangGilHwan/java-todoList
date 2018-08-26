package domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TodoListTest {

    private TodoList todoList;

    @Before
    public void setUp() {
        todoList = TodoList.of();
        todoList.write("집안일");
        todoList.write("빨래", 1);
        todoList.write("청소", 1);
        todoList.write("방청소", 1, 3);
        todoList.write("설거지", 3);
        todoList.write("옷장 정리", 4, 5, 2);
    }

    @Test
    public void size() {
        assertThat(todoList.nextIndex() - 1, is(6));
    }

    @Test(expected = IllegalArgumentException.class)
    public void refer_not_to_exist() {
        todoList.write("책상 닦기", 8);
    }

    @Test
    public void todo_done(){
        todoList.doWork(1);
        assertTrue(todoList.hasTodo(1).isDone());
    }

    @Test
    public void todo_done_and_completion(){
        assertFalse(todoList.hasTodo(6).isCompletion());

        todoList.doWork(6);
        assertTrue(todoList.hasTodo(6).isCompletion());
    }

    @Test
    public void one_precedence_completion(){
        todoList.doWork(4);
        assertTrue(todoList.hasTodo(4).isDone());
        assertFalse(todoList.hasTodo(4).isCompletion());

        todoList.doWork(6);
        assertTrue(todoList.hasTodo(6).isCompletion());
        assertTrue(todoList.hasTodo(4).isCompletion());
    }

    @Test
    public void two_precedence_completion(){
        todoList.write("쓸기");
        todoList.write("닦기", 7);
        todoList.write("휴지통 비우기", 7, 8);
        todoList.doWork(7);
        assertFalse(todoList.hasTodo(7).isCompletion());

        todoList.doWork(8);
        assertFalse(todoList.hasTodo(8).isCompletion());
        assertFalse(todoList.hasTodo(7).isCompletion());

        todoList.doWork(9);
        assertTrue(todoList.hasTodo(8).isCompletion());
        assertTrue(todoList.hasTodo(7).isCompletion());
    }
}
