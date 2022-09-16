package ua.com.javarush.quest.khmelov.questdelta.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SpaceQuest implements Quest {
    private final Map<Long, Node> map = new HashMap<>();

    public SpaceQuest() {
        map.put(1L, new Node(1L, "Space Question #1"));
        map.put(2L, new Node(2L, "Space Question #2"));
        map.put(3L, new Node(3L, "Space Question #3"));
    }

    public Collection<Node> getAll() {
        return map.values();
    }

    public Optional<Node> get(long id) {
        return Optional.ofNullable(map.get(id));
    }
}
