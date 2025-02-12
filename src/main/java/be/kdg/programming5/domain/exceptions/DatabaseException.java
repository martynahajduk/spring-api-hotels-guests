package be.kdg.programming5.domain.exceptions;

//TODO
public class DatabaseException extends RuntimeException {
    private DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
