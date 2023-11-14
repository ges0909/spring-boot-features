package com.valantic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
    ExitCodeExceptionMapper exitCodeExceptionMapper() {
        return exception -> {
            LOGGER.error(exception.getMessage());
            return 61;
        };
    }

    // this works because @SpringBootApplication is also a @Configuration class
    @Bean
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
