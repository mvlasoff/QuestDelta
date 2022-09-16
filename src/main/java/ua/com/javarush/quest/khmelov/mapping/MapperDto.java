package ua.com.javarush.quest.khmelov.mapping;

import ua.com.javarush.quest.khmelov.entity.AbstractEntity;

public interface MapperDto<P extends AbstractEntity, C> {
    C from(P entity);

}
