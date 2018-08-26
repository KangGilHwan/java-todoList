package domain;

import java.util.ArrayList;
import java.util.List;

public class Precedences {

    private List<Todo> precedences;

    public Precedences() {
        this.precedences = new ArrayList<>();
    }

    public void add(Todo precedence) {
        precedences.add(precedence);
    }

    public boolean checkCompletion() {
        return precedences.stream().allMatch(p -> p.isCompletion());
    }

    @Override
    public String toString() {
        return "Precedences{" +
                "precedences=" + precedences.size() +
                '}';
    }
}
