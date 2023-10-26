package com.valantic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.support.LdapContextSource;

@SpringBootApplication
@EnableConfigurationProperties(LdapConfig.class)
public class MainApplication {

    private final LdapConfig ldapConfig;

    public MainApplication(LdapConfig ldapConfig) {
        this.ldapConfig = ldapConfig;
    }

    public static void main(final String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(ldapConfig.url());
        contextSource.setBase(ldapConfig.base());
        contextSource.setUserDn(ldapConfig.password());
        contextSource.setPassword(ldapConfig.password());
        return contextSource;
    }
}
