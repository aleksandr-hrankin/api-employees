package ua.antibyte.exception;

public class ApiConnectionException extends RuntimeException {
    public ApiConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
