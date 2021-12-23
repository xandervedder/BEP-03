package nl.softwarestrijders.waiter.order.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find order")
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
