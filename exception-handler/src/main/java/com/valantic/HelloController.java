package com.valantic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @ExceptionHandler(MissingRequestValueException.class)
    ResponseEntity<String> handleMissingRequestValueException(MissingRequestValueException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @GetMapping("/hello")
    String hello() {
        return "Hello";
    }

    @GetMapping("/hello/{name}")
    String hello(@PathVariable String name) throws MissingRequestValueException {
        if (name.equals("throw")) {
            throw new MissingRequestValueException("missing 'name'");
        }
        return "Hello, " + name;
    }
}
