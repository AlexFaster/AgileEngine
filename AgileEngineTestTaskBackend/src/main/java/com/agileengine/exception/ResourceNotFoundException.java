package com.agileengine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(final NotFoundEnum notFoundEntity) {
        super(notFoundEntity.identifier() + " not found");
    }
}
