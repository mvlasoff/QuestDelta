package ua.com.javarush.quest.khmelov.dto.ui;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "with")
public class QuestDto {
    Long id;
    Long authorId;
    String name;
}
