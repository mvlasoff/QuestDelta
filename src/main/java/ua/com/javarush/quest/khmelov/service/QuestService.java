package ua.com.javarush.quest.khmelov.service;

import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.QuestDto;
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
                .map(Mapper.quest::write)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public Optional<QuestDto> get(long id) {
        Quest Quest = questRepository.get(id);
        return Mapper.quest.write(Quest);
    }

    public Optional<QuestDto> find(FormData formData) {
        Quest Quest = Mapper.quest.read(formData);
        Optional<Quest> optionalQuest = questRepository
                .find(Quest)
                .findFirst();
        return optionalQuest.isPresent()
                ? Mapper.quest.write(optionalQuest.get())
                : Optional.empty();
    }

    public void update(FormData formData) {
        Quest Quest = questRepository.get(formData.getId());
        Mapper.quest.fill(Quest, formData);
        questRepository.update(Quest);
    }

    public void create(FormData formData) {
        Quest Quest = new Quest();
        Mapper.quest.fill(Quest, formData);
        questRepository.create(Quest);
    }

    public void delete(FormData formData) {
        Quest Quest = questRepository.get(formData.getId());
        questRepository.delete(Quest);
    }
}
