package ua.com.javarush.quest.khmelov.questdelta.entity;

import java.util.*;

public class Node {
    private final long id;
    private final String question;
    private final Map<String, Node> nodeDependencies;

    public Node(long id, String question) {
        this.id = id;
        this.question = question;
        this.nodeDependencies = new HashMap<>();
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Map<String, Node> getNodeDependencies() {
        return nodeDependencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id && Objects.equals(question, node.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", question='" + question + '\'' +
                '}';
    }
}
