package com.valantic.sti;

import java.util.Optional;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;

import org.testcontainers.containers.*;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.*;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class ApplicationTests {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("gerrit")
            .withPassword("develop")
            .withDatabaseName("testdb");

    @DynamicPropertySource
    static void properties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    private final BookRepository bookRepository;

    @Autowired
    ApplicationTests(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

//    @Test
//    void autonomousTest() {
//        try (final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
//                .withUsername("gerrit")
//                .withPassword("develop")
//                .withDatabaseName("testdb");
//        ) {
//            final Book book = new Book();
//            book.setName("Testcontainers");
//            bookRepository.save(book);
//            final Optional<Book> actual = bookRepository.findByName("Testcontainers");
//            assertTrue(actual.isPresent());
//            assertEquals("Testcontainers", actual.get().getName())
//        }
//    }

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
