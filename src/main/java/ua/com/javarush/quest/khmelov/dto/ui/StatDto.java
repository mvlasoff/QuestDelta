package ua.com.javarush.quest.khmelov.dto.ui;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "with")
public class StatDto {
    String login;
    long win;
    long lost;
    long play;
    long total;
}
