package nl.softwarestrijders.waiter.order.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidModificationException extends RuntimeException {
    public InvalidModificationException(String errorMessage) {
        super(errorMessage);
    }
}
