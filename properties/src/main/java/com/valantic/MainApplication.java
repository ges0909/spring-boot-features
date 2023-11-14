package com.valantic;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
// @EnableConfigurationProperties(ConfigProperties.class) // variant 1
@ConfigurationPropertiesScan("com.valantic") // variant 2
public class MainApplication {

    private final Logger log = LoggerFactory.getLogger(MainApplication.class);

    private final ConfigProperties configProperties;
    private final PropertyConversion propertyConversion;
    private final Item item;

    @Value("${person.firstname:unknown}")
    public String firstName;

    @Autowired
    public MainApplication(final ConfigProperties configProperties, final PropertyConversion propertyConversion, Item item) {
        this.configProperties = configProperties;
        this.propertyConversion = propertyConversion;
        this.item = item;
    }

    /**
     * @param args
     */
    public static void main(final String... args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @PostConstruct
    void postConstruct() {
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
        log.info("Item = {} {} {}", item.getA(), item.getB(), item.isC());
    }
}
