package com.colvir.webinar14;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@SpringBootTest
@Testcontainers
public abstract class SpringBootAppTest {

    private static final String DATABASE_NAME = "test-staff";

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:17.4")
            .withReuse(true)
            .withDatabaseName(DATABASE_NAME);

    @BeforeAll
    public static void setUp() {
        postgreSQLContainer.setWaitStrategy(
                new LogMessageWaitStrategy()
                        .withRegEx(".*database system is ready to accept connections.*\\s")
                        .withTimes(1)
                        .withStartupTimeout(Duration.of(60, ChronoUnit.SECONDS))
        );
        postgreSQLContainer.start();
    }

    @AfterAll
    public static void tearDown() {
        postgreSQLContainer.close();
    }

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.driverClassName", postgreSQLContainer::getDriverClassName);

        dynamicPropertyRegistry.add("spring.liquibase.url", postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.liquibase.user", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.liquibase.password", postgreSQLContainer::getPassword);
        dynamicPropertyRegistry.add("spring.liquibase.driverClassName", postgreSQLContainer::getDriverClassName);
    }
}
