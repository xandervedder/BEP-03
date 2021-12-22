package nl.softwarestrijders.waiter.delivery.core.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super(String.format("Could not find delivery with id %s", id));
    }
}
