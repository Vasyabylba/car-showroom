package by.vasyabylba.carshowroom.controller;

import by.vasyabylba.carshowroom.dto.error.GenericErrorResponse;
import by.vasyabylba.carshowroom.dto.error.ValidationErrorResponse;
import by.vasyabylba.carshowroom.excteption.CarAlreadyInShowroomException;
import by.vasyabylba.carshowroom.excteption.CarNotFoundException;
import by.vasyabylba.carshowroom.excteption.CarShowroomNotFoundException;
import by.vasyabylba.carshowroom.excteption.ClientAlreadyHasCarException;
import by.vasyabylba.carshowroom.excteption.ClientNotFoundException;
import by.vasyabylba.carshowroom.excteption.ReviewNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestErrorHandler {

    @ExceptionHandler({
            CarNotFoundException.class,
            CarShowroomNotFoundException.class,
            CarNotFoundException.class,
            ClientNotFoundException.class,
            ReviewNotFoundException.class
    })
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public GenericErrorResponse handleUnprocessableEntityException(Exception ex) {
        return generateGenericErrorResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({
            CarAlreadyInShowroomException.class,
            ClientAlreadyHasCarException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public GenericErrorResponse handleConflictException(Exception ex) {
        return generateGenericErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Violation> violations = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .toList();
        return generateValidationErrorResponse(violations);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleConstraintValidationException(ConstraintViolationException ex) {
        List<Violation> violations = ex.getConstraintViolations().stream()
                .map(violation ->
                        new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        ))
                .toList();
        return generateValidationErrorResponse(violations);
    }

    private GenericErrorResponse generateGenericErrorResponse(String message, HttpStatus httpStatus) {
        return GenericErrorResponse.builder()
                .code(httpStatus.value())
                .status(httpStatus)
                .message(message)
                .time(getCurrentDateTime())
                .build();
    }

    private ValidationErrorResponse generateValidationErrorResponse(List<Violation> violations) {
        return ValidationErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .errors(Collections.singletonList(violations))
                .time(getCurrentDateTime())
                .build();
    }

    private LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public record Violation(String property, String message) {

    }

}
