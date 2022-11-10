package ua.com.javarush.quest.khmelov.repository.dao_jdbc;

public class DaoException extends RuntimeException{


    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
