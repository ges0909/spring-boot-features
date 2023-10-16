package com.valantic.sti;

import java.util.stream.Stream;

import org.slf4j.*;
import org.springframework.boot.*;

import com.valantic.sti.apps.ImportApplication;
import com.valantic.sti.apps.ServerApplication;

public class MainApplication {

    static final Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(final String... args) {
        final String command = Stream.of(args).findFirst().orElse("server");
        final String[] remainingArgs = Stream.of(args).skip(1).toArray(String[]::new); // left shift
        switch (command) {
            // case "server" -> ServerApplication.main(remainingArgs);
            // case "import" -> ImportApplication.main(remainingArgs);
            case "server" -> SpringApplication.from(ServerApplication::main).run(remainingArgs);
            case "import" -> SpringApplication.from(ImportApplication::main).run(remainingArgs);
            default -> logger.error("Unknown command '{}'", command);
        }
    }
}
