package com.valantic;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Service
public class LdapService {

    private final LdapTemplate ldapTemplate;

    public LdapService(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    /**
     * @param username
     * @return
     */
    public List<String> search(String username) {
        return ldapTemplate
                .search(
                        "ou=users",
                        "cn=" + username,
                        (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
    }

    /**
     * @param username
     * @param password
     */
    public void create(String username, String password) {
        Name dn = LdapNameBuilder
                .newInstance()
                .add("ou", "users")
                .add("cn", username)
                .build();
        DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues(
                "objectclass",
                new String[]
                        {"top",
                                "person",
                                "organizationalPerson",
                                "inetOrgPerson"});
        context.setAttributeValue("cn", username);
        context.setAttributeValue("sn", username);
        context.setAttributeValue
                ("userPassword", digestSHA(password));
        ldapTemplate.bind(context);
    }

    /**
     * @param username
     * @param password
     */
    public void modify(String username, String password) {
        Name dn = LdapNameBuilder.newInstance()
                .add("ou", "users")
                .add("cn", username)
                .build();
        DirContextOperations context
                = ldapTemplate.lookupContext(dn);
        context.setAttributeValues
                ("objectclass",
                        new String[]
                                {"top",
                                        "person",
                                        "organizationalPerson",
                                        "inetOrgPerson"});
        context.setAttributeValue("cn", username);
        context.setAttributeValue("sn", username);
        context.setAttributeValue("userPassword",
                digestSHA(password));
        ldapTemplate.modifyAttributes(context);
    }

    /**
     * @param password
     * @return
     */
    private String digestSHA(final String password) {
        String base64;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(password.getBytes());
            base64 = Base64
                    .getEncoder()
                    .encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "{SHA}" + base64;
    }
}
