package domain;

import java.util.*;
import java.util.stream.Collectors;

public class TodoList {

    private Map<Integer, Todo> todoList;

    private TodoList() {
        this.todoList = new LinkedHashMap<>();
    }

    public void doWork(int id) {
        Todo todo = hasTodo(id);
        todo.work();
    }

    public void write(String todoName, Integer... referencesId) {
        List<Todo> references = findReferences(referencesId);

        int index = nextIndex();
        Todo todo = Todo.of(index, todoName, references);
        todoList.put(index, todo);

        addPrecedence(todo, referencesId);
    }

    private List<Todo> findReferences(Integer... referencesId) {
        return Arrays.stream(referencesId)
                .map(p -> hasTodo(p))
                .collect(Collectors.toList());
    }

    private void addPrecedence(Todo todo, Integer... referencesId){
        Arrays.stream(referencesId)
                .map(p -> hasTodo(p))
                .forEach(p -> p.addPrecedence(todo));
    }

    public Todo hasTodo(int todoId) {
        return Optional.ofNullable(todoList.get(todoId))
                .orElseThrow(() -> new IllegalArgumentException("Todo 리스트에 없는 값을 입력하였습니다."));
    }

    public int nextIndex() {
        return todoList.size() + 1;
    }

    public static TodoList of() {
        return new TodoList();
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "todoList=" + todoList +
                '}';
    }
}
