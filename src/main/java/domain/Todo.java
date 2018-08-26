package domain;

import java.util.List;

public class Todo {

    private int id;
    private String todo;
    private References references;
    private Precedences precedences;
    private boolean done = false;
    private boolean completion = false;

    private Todo(int id, String todo, List<Todo> references) {
        this.id = id;
        this.todo = todo;
        this.references = new References(references);
        this.precedences = new Precedences();
    }

    public void addPrecedence(Todo precedence) {
        precedences.add(precedence);
    }

    public void work() {
        done = true;
        canBeCompleted();
    }

    public void canBeCompleted() {
        if (isDone() && checkCompletionPrecedences()) {
            complete();
        }
    }

    public boolean checkCompletionPrecedences() {
        return precedences.checkCompletion();
    }

    public void complete() {
        completion = true;
        canBeCompletedReferences();
    }

    public void canBeCompletedReferences() {
        references.canBeCompleted();
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
                ", references=" + references +
                ", done=" + done +
                ", precedences=" + precedences +
                ", completion=" + completion +
                '}';
    }
}
