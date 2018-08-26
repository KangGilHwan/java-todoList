package domain;

import java.util.List;

public class References {

    private List<Todo> references;

    public References(List<Todo> references){
        this.references = references;
    }

    public void canBeCompleted() {
        references.stream().forEach(r -> r.canBeCompleted());
    }

    @Override
    public String toString() {
        return "References{" +
                "references=" + references.size() +
                '}';
    }
}
