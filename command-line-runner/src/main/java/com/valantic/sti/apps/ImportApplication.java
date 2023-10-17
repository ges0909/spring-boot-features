package com.valantic.sti.apps;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.event.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.context.event.*;
import org.springframework.http.*;
import org.springframework.web.client.*;

import org.slf4j.*;

@Profile("import")
@SpringBootApplication
public class ImportApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportApplication.class);

    public static void main(final String... args) {
        final var app = new SpringApplication(ImportApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);  // avoid port binding
        app.setAdditionalProfiles("import");
        final ConfigurableApplicationContext applicationContext = app.run(args);
        // get any bean by name from IoC container
        final CommandLineRunner commandLineRunner = (CommandLineRunner) applicationContext.getBean("commandLineRunner");
        LOGGER.debug(commandLineRunner.toString());
    }

    @Bean
        // this works because @SpringBootApplication is also a @Configuration class
    CommandLineRunner commandLineRunner() {
        LOGGER.info("command line runner: IMPORT");
        return args -> {
            final RestTemplate restTemplate = new RestTemplate();
            final ResponseEntity<String> response
                    = restTemplate.getForEntity("http://localhost:8080/trigger?ref=meineReferenz", String.class);
            LOGGER.info("Https Status = {}", response.getStatusCode());
        };
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> LOGGER.info("application runner: IMPORT, args={}", args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        LOGGER.info("application ready event: IMPORT");
    }
}
