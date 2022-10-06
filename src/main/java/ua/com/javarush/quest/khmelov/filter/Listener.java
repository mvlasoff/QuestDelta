package ua.com.javarush.quest.khmelov.filter;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.service.RepositoryService;

@WebListener
public class Listener implements ServletContextListener {

    private final RepositoryService repositoryService;

    public Listener() {
        this.repositoryService = Winter.getBean(RepositoryService.class);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        repositoryService.load();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //repositoryService.save(); //save DB in services after add/update quest, game, user, images
    }


}
