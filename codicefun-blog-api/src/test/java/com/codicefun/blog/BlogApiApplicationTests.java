package com.codicefun.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO: Test case should be more standard, see:
 * <a href="https://www.baeldung.com/java-unit-testing-best-practices">java unit testing best practices</a>
 * <p></p>
 * TODO: Test coverage, see:
 * <a href="https://www.baeldung.com/sonarqube-jacoco-code-coverage">Code Coverage with SonarQube and JaCoCo</a>
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BlogApiApplicationTests {

    @Test
    void contextLoads() {
    }

}
