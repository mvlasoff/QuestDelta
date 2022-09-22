package ua.com.javarush.quest.khmelov.service;

import ua.com.javarush.quest.khmelov.dto.ui.QuestDto;
import ua.com.javarush.quest.khmelov.entity.Quest;
import ua.com.javarush.quest.khmelov.mapping.Mapper;
import ua.com.javarush.quest.khmelov.repository.Repository;
import ua.com.javarush.quest.khmelov.repository.QuestRepository;

import java.util.Collection;
import java.util.Optional;

public enum QuestService {

    INSTANCE;

    private final Repository<Quest> questRepository = QuestRepository.get();


    public Collection<QuestDto> getAll() {
        return questRepository.getAll()
                .map(Mapper.quest::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public Optional<QuestDto> get(long id) {
        Quest Quest = questRepository.get(id);
        return Mapper.quest.get(Quest);
    }
}
