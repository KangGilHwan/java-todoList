package domain;

import java.util.*;
import java.util.stream.Collectors;

public class TodoList {

    private Map<Integer, Todo> todoList;

    private TodoList() {
        this.todoList = new LinkedHashMap<>();
    }

    public void write(String todo, Integer... referencesId) {
        int index = nextIndex();
        List<Todo> references = Arrays.stream(referencesId)
                .map(p -> hasTodo(p))
                .collect(Collectors.toList());

        Todo todo2 = Todo.of(index, todo, references);
        todoList.put(index, todo2);

        Arrays.stream(referencesId)
                .map(p -> hasTodo(p))
                .forEach(p -> p.addPrecedence(todo2));
    }

    public Todo hasTodo(int todoId){
        return Optional.ofNullable(todoList.get(todoId))
                .orElseThrow(() -> new IllegalArgumentException("Todo 리스트에 없는 값을 입력하였습니다."));
    }

    public void doWork(int id) {
       Todo todo = hasTodo(id);
       todo.work();
    }

    public int nextIndex(){
        return todoList.size() + 1;
    }

    public static TodoList of(){
        return new TodoList();
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "todoList=" + todoList +
                '}';
    }
}
