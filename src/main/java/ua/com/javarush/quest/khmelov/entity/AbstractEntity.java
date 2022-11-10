package ua.com.javarush.quest.khmelov.entity;

/**
 * Parent any entity. Use as parent in wildcard
 */
public abstract class AbstractEntity {

    public abstract Long getId();

    public abstract void setId(Long id);

}
