package domain;

import java.util.ArrayList;
import java.util.List;

public class Todo {

    private int id;
    private String todo;

    private List<Todo> references;
    private boolean done = false;

    private List<Todo> precedences;
    private boolean completion = false;

    private Todo(int id, String todo, List<Todo> references) {
        this.id = id;
        this.todo = todo;
        this.references = references;
        this.precedences = new ArrayList<>();
    }

    public void addPrecedence(Todo precedence) {
        precedences.add(precedence);
    }

    public void work() {
        done = true;
        checkChild();
    }

    public void checkChild() {
        if (isDone() && checkPrecedencesCompletion()) {
            complete();
        }
    }

    public boolean checkPrecedencesCompletion() {
        return precedences.stream().allMatch(p -> p.isCompletion());
    }

    public void complete() {
        completion = true;
        checkReferencesCompletion();
    }

    public void checkReferencesCompletion() {
        references.stream().forEach(r -> r.checkChild());
    }

    public boolean isDone() {
        return done;
    }

    public boolean isCompletion() {
        return completion;
    }

    public static Todo of(int id, String todo, List<Todo> references) {
        return new Todo(id, todo, references);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", todo='" + todo + '\'' +
                ", references=" + references.size() +
                ", done=" + done +
                ", precedences=" + precedences.size() +
                ", completion=" + completion +
                '}';
    }
}
