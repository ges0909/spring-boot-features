package com.valantic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(LdapTestConfiguration.class)
public class LdapIntegrationTest {

    @Autowired
    private LdapService ldapService;

    @Test
    void createUser() {
        ldapService.create("user", "pass");
    }
}
