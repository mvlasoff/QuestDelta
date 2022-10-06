package ua.com.javarush.quest.khmelov.exception;

public class AppException extends RuntimeException {

    private final String source;

    public AppException(String message) {
        super(message);
        StackTraceElement throwLine = this.getStackTrace()[2];
        source = "%s:%d".formatted(throwLine.getClassName(), throwLine.getLineNumber());
    }

    public AppException(String message, Throwable cause) {
        super(cause);
        StackTraceElement throwLine = this.getStackTrace()[2];
        source = "%s:%d".formatted(throwLine.getClassName(), throwLine.getLineNumber());
    }

    @Override
    public String toString() {
        return source + getMessage();
    }



}
