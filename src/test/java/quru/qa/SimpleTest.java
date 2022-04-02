package quru.qa;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;

public class SimpleTest {

    @BeforeAll
    static void initDB() {
        System.out.println("### beforeAll");
    }

    @BeforeEach //Выполняется перед каждым тестом
    void openYaPage() {
        System.out.println("### beforeEach");
        //Selenide.open("https://ya.ru");
    }

    @AfterAll //Закрывается после каждого теста
    static void cleanDB() {
        System.out.println("### afterAll");
       // WebDriverRunner.closeWindow();
    }

    @AfterEach //Закрывается после каждого теста
    void close() {
        System.out.println("### afterEach");
        //WebDriverRunner.closeWindow();
    }

    @Test
    void assertTest1() {
        System.out.println("### test0");
    }

    @Test
    void assertTest2() {
        //Assertions.assertTrue(2 < 3);
        System.out.println("### test1");
    }


}
