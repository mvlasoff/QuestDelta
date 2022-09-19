package ua.com.javarush.quest.khmelov.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.com.javarush.quest.khmelov.entity.Entity;
import ua.com.javarush.quest.khmelov.entity.Question;

import java.util.Collection;

@Data
@Builder(builderMethodName = "with")
public class QuestDto {
    Long id;
    Long authorId;
    String name;
}
