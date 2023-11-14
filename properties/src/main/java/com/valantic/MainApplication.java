package com.valantic;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ConfigProperties.class, PropertyConversion.class, Secret.class}) // variant 1
// @ConfigurationPropertiesScan("com.valantic") // variant 2
public class MainApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    private final ConfigProperties configProperties;
    private final PropertyConversion propertyConversion;
    private final Item item;
    private final Secret secret;

    @Value("${person.firstname:unknown}")
    public String firstName;

    @Autowired
    public MainApplication(final ConfigProperties configProperties, final PropertyConversion propertyConversion, Item item, Secret secret) {
        this.configProperties = configProperties;
        this.propertyConversion = propertyConversion;
        this.item = item;
        this.secret = secret;
    }

    public static void main(final String... args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @PostConstruct
    void postConstruct() {
        LOGGER.info("Mail = {} {} {}",
                configProperties.hostName(),
                configProperties.port(),
                configProperties.from());
        LOGGER.info("DefaultRecipients = {}", configProperties.defaultRecipients());
        LOGGER.info("AdditionalHeaders = {}", configProperties.additionalHeaders());
        LOGGER.info("Credentials = {} {} {}",
                configProperties.credentials().authMethod(),
                configProperties.credentials().username(),
                configProperties.credentials().password()
        );
        LOGGER.info("Conversion = {} {} {} {} {} {} {}",
                propertyConversion.timeInDefaultUnit(),
                propertyConversion.timeInNano(),
                propertyConversion.sizeInDefaultUnit(),
                propertyConversion.sizeInGB(),
                propertyConversion.sizeInTB(),
                propertyConversion.employee().name(),
                propertyConversion.employee().salary()
        );
        LOGGER.info("Item = {} {} {}",
                item.getA(),
                item.getB(),
                item.isC());
        LOGGER.info("Secret = {}", secret.password());
    }
}
