package demo.exceptions;

public class ExternalApiUnavailableException
        extends ExternalApiException {

    public ExternalApiUnavailableException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}