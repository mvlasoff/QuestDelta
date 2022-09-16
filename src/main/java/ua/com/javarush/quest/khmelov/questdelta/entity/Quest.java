package ua.com.javarush.quest.khmelov.questdelta.entity;

import java.util.Collection;
import java.util.Optional;

public interface Quest {

    Collection<Node> getAll();

    Optional<Node> get(long id);
}