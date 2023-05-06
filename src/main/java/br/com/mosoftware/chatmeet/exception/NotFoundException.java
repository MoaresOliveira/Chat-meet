package br.com.mosoftware.chatmeet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.NOT_FOUND )
public class NotFoundException extends RuntimeException {

    public NotFoundException( Class<?> clazz, Object identifier ) {
        super( clazz.getSimpleName() + " not found with ID: " + identifier );
    }
}
