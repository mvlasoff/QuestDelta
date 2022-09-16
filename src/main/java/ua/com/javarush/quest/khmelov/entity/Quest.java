package ua.com.javarush.quest.khmelov.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Quest extends AbstractEntity{
    Long id;
    Long authorId;
    Collection<Question> questions;
    String name;
}
