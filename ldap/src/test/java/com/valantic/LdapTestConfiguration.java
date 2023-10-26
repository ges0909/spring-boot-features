package com.valantic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.ldap.test.unboundid.TestContextSourceFactoryBean;

@TestConfiguration
class LdapTestConfiguration {

    @Value("${ldap.partition}")
    String partition;

    @Value("${ldap.partitionSuffix}")
    String partitionSuffix;

    @Value("${ldap.principal}")
    String principal;

    @Value("${ldap.password}")
    String password;

    @Value("${ldap.ldiffile}")
    Resource ldiffile;

    @Value("${ldap.port}")
    int port;

    /**
     * Another way to work against an embedded LDAP server is by using
     * <code>org.springframework.ldap.test.unboundid.TestContextSourceFactoryBean</code>.
     * <p>
     * See: https://docs.spring.io/spring-ldap/reference/testing.html#spring-ldap-testing-embedded-server
     *
     * @return
     */
    @Bean
    public TestContextSourceFactoryBean testContextSource() {
        TestContextSourceFactoryBean contextSource = new TestContextSourceFactoryBean();
        contextSource.setDefaultPartitionName(partition);
        contextSource.setDefaultPartitionSuffix(partitionSuffix);
        contextSource.setPrincipal(principal);
        contextSource.setPassword(password);
        contextSource.setLdifFile(ldiffile);
        contextSource.setPort(port);
        return contextSource;
    }
}
