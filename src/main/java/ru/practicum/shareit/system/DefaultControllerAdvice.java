package ru.practicum.shareit.system;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.practicum.shareit.system.dto.Response;
import ru.practicum.shareit.system.exception.*;

import javax.validation.ValidationException;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler({UserNotFoundException.class, ItemNotFoundException.class})
    public ResponseEntity<Response> notFoundHandler(Exception e) {
        return new ResponseEntity<>(new Response(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> accessDeniedHandler(Exception e) {
        return new ResponseEntity<>(new Response(e), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoEmailException.class)
    public ResponseEntity<Response> noEmailExceptionHandler(Exception e) {
        return new ResponseEntity<>(new Response(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Response> duplicateEmailExceptionHandler(Exception e) {
        return new ResponseEntity<>(new Response(e), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Response> validationExceptionHandler(Exception e) {
        return new ResponseEntity<>(new Response(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> commonHandler(Exception e) {
        return new ResponseEntity<>(new Response(e), HttpStatus.BAD_REQUEST);
    }

}
