package com.valantic;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/echo")
public class EchoControllerWebFlux {

    @GetMapping
    Mono<String> echo() {
        return Mono.just("Hello");
    }

    @GetMapping("/{name}")
    Mono<String> echo(@PathVariable String name) {
        return Mono.just("Hello, " + name);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<Person> echo(@RequestBody @Valid Person person) {
        return Mono.just(person);
    }
}
