package com.example;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Produces
@Singleton
@Requires(classes = {DemoException.class, ExceptionHandler.class})
public class CustomExceptionHandler implements ExceptionHandler<DemoException, HttpResponse> {

    Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @Override
    public HttpResponse handle(HttpRequest request, DemoException exception) {
        logger.error("Caught exception", exception);
        return HttpResponse.status(HttpStatus.BAD_REQUEST);
    }
}
