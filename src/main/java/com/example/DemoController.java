package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Map;

@Controller("/api")
public class DemoController {

    @Get
    public Map<String, Object> index() {
        return Map.of("msg", "Hello, World!");
    }

    @Get("/error")
    public void error() {
        throw new DemoException("This is a test exception!");
    }
}
