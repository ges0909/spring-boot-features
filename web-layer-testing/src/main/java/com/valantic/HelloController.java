package com.valantic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    String hello() {
        return "Hello";
    }

    @GetMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name;
    }
}
