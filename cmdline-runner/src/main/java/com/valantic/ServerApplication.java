package com.valantic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

@Profile("server")
@SpringBootApplication
public class ServerApplication implements CommandLineRunner, ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerApplication.class);

    public static void main(final String... args) {
        final var app = new SpringApplication(ServerApplication.class);
        app.setAdditionalProfiles("server");
        final ConfigurableApplicationContext context = app.run(args);
        // get all instantiated beans
        LOGGER.trace(String.join("\n", context.getBeanDefinitionNames()));
    }

    @Override
    public void run(final String... args) {
        LOGGER.info("command line runner: SERVER");
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        LOGGER.info("application runner: SERVER, args={}", args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        LOGGER.info("application ready event: SERVER");
    }
}
