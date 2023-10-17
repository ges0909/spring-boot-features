package com.valantic.sti;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;

@SpringB
public class LdapServiceTest {

    @Bean
    public TestContextSourceFactoryBean testContextSource() {
        TestContextSourceFactoryBean contextSource
                = new TestContextSourceFactoryBean();

        contextSource.setDefaultPartitionName(
                env.getRequiredProperty("ldap.partition"));
        contextSource.setDefaultPartitionSuffix(
                env.getRequiredProperty("ldap.partitionSuffix"));
        contextSource.setPrincipal(
                env.getRequiredProperty("ldap.principal"));
        contextSource.setPassword(
                env.getRequiredProperty("ldap.password"));
        contextSource.setLdifFile(
                resourceLoader.getResource(
                        env.getRequiredProperty("ldap.ldiffile")));
        contextSource.setPort(
                Integer.valueOf(
                        env.getRequiredProperty("ldap.port")));
        return contextSource;
    }

    @Test
    void createUser() {
        ldapService.create("user", "pass");
    }

}
