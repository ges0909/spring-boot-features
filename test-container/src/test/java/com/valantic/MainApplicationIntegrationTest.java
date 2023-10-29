package com.valantic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
class MainApplicationIntegrationTest {

    @Container
    // false positive warning: https://stackoverflow.com/questions/47959505/how-to-get-the-suppresswarnings-warning-name-for-an-intellij-warning
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("gerrit")
            .withPassword("develop")
            .withDatabaseName("testdb");

    private final BookRepository bookRepository;

    @Autowired
    MainApplicationIntegrationTest(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @DynamicPropertySource
    static void properties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Test
    void saveBook() {
        final Book book = new Book();
        book.setName("Testcontainers");
        bookRepository.save(book);
        //
        final Optional<Book> actual = bookRepository.findByName("Testcontainers");
        assertTrue(actual.isPresent());
        assertEquals("Testcontainers", actual.get().getName());
    }
}
