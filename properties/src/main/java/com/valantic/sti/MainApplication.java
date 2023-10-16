package com.valantic.sti;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.properties.*;

import org.slf4j.*;

@SpringBootApplication
// @EnableConfigurationProperties(ConfigProperties.class) // variant 1
@ConfigurationPropertiesScan("com.valantic.sti") // variant 2
public class MainApplication {

    private final Logger log = LoggerFactory.getLogger(MainApplication.class);

    private final ConfigProperties configProperties;
    private final PropertyConversion propertyConversion;

    @Value("${person.firstname:unknown}")
    public String firstName;

    @Autowired
    public MainApplication(final ConfigProperties configProperties, final PropertyConversion propertyConversion) {
        this.configProperties = configProperties;
        this.propertyConversion = propertyConversion;
    }

    /**
     * @param args
     */
    public static void main(final String... args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @PostConstruct
    private void postConstruct() {
        log.info("Hostname = {}", configProperties.hostName());
        log.info("Port = {}", configProperties.port());
        log.info("From = {}", configProperties.from());
        log.info("DefaultRecipients = {}", configProperties.defaultRecipients());
        log.info("AdditionalHeaders = {}", configProperties.additionalHeaders());
        log.info("Credentials = {} {} {}",
                configProperties.credentials().authMethod(),
                configProperties.credentials().username(),
                configProperties.credentials().password()
        );
        log.info("Conversion = {} {} {} {} {} {} {}",
                propertyConversion.timeInDefaultUnit(),
                propertyConversion.timeInNano(),
                propertyConversion.sizeInDefaultUnit(),
                propertyConversion.sizeInGB(),
                propertyConversion.sizeInTB(),
                propertyConversion.employee().name(),
                propertyConversion.employee().salary()
        );
    }
}
