package ua.com.javarush.quest.khmelov.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.khmelov.config.Config;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.repository.UserRepository;
import ua.com.javarush.quest.khmelov.service.QuestService;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@UtilityClass
public class RepositoryLoader {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    private final UserRepository userRepository = UserRepository.get();

    public void load() {
        //load from json
        defaultTxtInit();
        save();
    }


    public static void main(String[] args) {
        RepositoryLoader.load();
    }


    @SneakyThrows
    public void save() {
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        List<User> users = userRepository.getAll().toList();
        Config config = Config.get();
        Path folder = Path.of(config.dataBase.folder);
        Files.createDirectories(folder);
        String json = config.dataBase.json;
        try (OutputStream outputStream = Files.newOutputStream(folder.resolve(json))) {
            MAPPER.writeValue(outputStream, users);
        }
    }

    private void defaultTxtInit() {
        //пользователи
        User ivan = new User(1L, "Ivan", "456", Role.ADMIN);
        User andrew = new User(2L, "Andrew", "789", Role.GUEST);
        User elena = new User(3L, "Elena", "123", Role.USER);
        userRepository.create(ivan);
        userRepository.create(andrew);
        userRepository.create(elena);
        QuestService questService = QuestService.INSTANCE;
        Long ivanId = ivan.getId();

        questService.create(
                "Играем в неопознанный летающий объект (обязательный квест)",
                """
                        1:  Ты потерял память. Принять вызов НЛО?
                        2<  Принять вызов
                        91< Отклонить вызов
                        
                        2:  Ты принял вызов. Подняться на мостик к капитану?
                        92< Отказаться подниматься на мостик
                        3< Подняться на мостик
                        
                        3:  Ты поднялся на мостик. Ты кто?
                        93< Солгать о себе
                        99< Рассказать правду
                        
                        91- Ты отклонил вызов. Поражение.
                        92- Ты не пошел на переговоры. Поражение.
                        93- Твою ложь разоблачили. Поражение.

                        99+ Вы выиграли
                        """,
                ivanId
        );


        questService.create(
                "Три богатыря",
                """
                        1: Ты перед волшебным камнем, на нем есть надписи. Что выберешь?
                        2< налево пойдешь - в сказку попадешь
                        3< направо пойдешь - свою смерть найдешь
                        4< прямо пойдешь - в разработчики пойдешь
                                     
                        2: Ты видишь закрытый сундук, ну-с... И что будем делать?
                        1< Кто ж его знает, что там, вернусь-ка я обратно...
                        7< Я смелый и ничего не боюсь, я его открою...
                                           
                        3: Ты видишь какую-то пещеру, что будешь делать?
                        1< Та ну нафиг. Боюсь. Вернусь-ка я за подмогой.
                        8< Я смелый и ничего не боюсь, меч достаю и быстро захожу...
                                           
                        4: Ты видишь Кикимору Болотную, которая что-то увлеченно печатает на ноутбуке.
                        1< Кикимора? Ноутбук? Какие-то опасные грибы я съел... Бегом отсюда...
                        5< Скажу громко "Эй ты, а ну отошла от ноута, ща я тебе покажу класс..."
                        6< Похоже без ноута никак разработчиком не стать. Бью Кикимору дубиной по башке, и теперь я настоящий программист.
                                         
                        5: Кикимора прыгнула в болото, ноут утонул вместе с ней, но зато справа я вижу какой-то тоннель
                        3< Ок, пойду-ка я туда, делать-то нечего.
                                                              
                        6: Настоящий поступок, настоящего программиста. Теперь осталось только Java доучить, и все, дело в шляпе.
                        1< Стоп, мне же еще нужна литература... Поищу-ка я ее, вон и тропинка видна какая-то с табличкой "в библиотеку"
                        9< Да, точно. Все, я быстро-быстро иду все повторять, учить, и кодить-кодить-кодить. Игры - зло.
                                           
                        7- Ого. Вы нашли большую кучу золота. Но прибежали злые печенеги и вы бесславно погибли. Жадность до добра не доведет.
                        8- Вы заходите в пещеру, а там Кащей, с Бабой Ягой, и Змеем Горынычем. Тут и сказочке конец. Вы погибли в неравном бою.
                        9+ Да! Это победа! Но пора приниматься за работу, хватит уже ерундой заниматься.
                        """,
                ivanId
        );
        questService.create(
                "Проверим ваши знания арифметики",
                """
                        1: Вы знаете арифметику?
                        2< Да, конечно
                        99< А что это такое?

                        2: Сколько будет дважды два?
                        99< Один
                        99< Два
                        99< Три
                        100< Четыре

                        99- Вы проиграли

                        100+ Вы выиграли
                        """,
                ivanId
        );
    }

}
