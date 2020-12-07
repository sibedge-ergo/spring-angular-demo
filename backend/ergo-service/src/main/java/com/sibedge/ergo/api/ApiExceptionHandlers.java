package com.sibedge.ergo.api;

import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sibedge.ergo.shared.transport.ErrorData;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ControllerAdvice
@RequiredArgsConstructor
class ApiExceptionHandlers {

    private final MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorData constraintViolationException(BindException cause,
                                                  HttpServletRequest request,
                                                  HttpServletResponse response) {
        var bindingResult = cause.getBindingResult();
        var errors = bindingResult.getFieldErrors().stream()
                .map(error -> String.join(": ", error.getField(), messageSource.getMessage(error, request.getLocale())))
                .collect(Collectors.toList());
        bindingResult.getGlobalErrors().stream()
                .map(objectError -> messageSource.getMessage(objectError, request.getLocale()))
                .collect(Collectors.toCollection(() -> errors));
        return ErrorData.of(errors);
    }

}
