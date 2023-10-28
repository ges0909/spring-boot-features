package com.valantic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import java.util.stream.Stream;

public class MainApplication {

    static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    public static void main(final String... args) {
        final String command = Stream.of(args).findFirst().orElse("server");
        final String[] remainingArgs = Stream.of(args).skip(1).toArray(String[]::new); // left shift
        switch (command) {
            // case "server" -> ServerApplication.main(remainingArgs);
            // case "import" -> ImportApplication.main(remainingArgs);
            case "server" -> SpringApplication.from(ServerApplication::main).run(remainingArgs);
            case "import" -> SpringApplication.from(ImportApplication::main).run(remainingArgs);
            default -> LOGGER.error("Unknown command '{}'", command);
        }
    }
}