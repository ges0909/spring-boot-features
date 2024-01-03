package com.valantic;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/echo")
public class EchoController {

    private static final Logger logger = LoggerFactory.getLogger(EchoController.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        if (e.getDetailMessageArguments() != null) {
            Stream.of(e.getDetailMessageArguments()).forEach(d -> logger.error("{}", d));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @GetMapping
    String hello() {
        return "Hello";
    }

    @GetMapping("/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        // @ResponseBody
    ResponseEntity<Person> login(@RequestBody @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            bindingResult.getFieldErrors().forEach(error -> logger.error("{}: {}", error.getField(), error.getDefaultMessage()));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(person);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(person);
    }
}
