package quru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pages.TextBoxPage;
import pages.domain.TitleEnum;

import java.util.stream.Stream;

@DisplayName("Класс с демонстрационными тестами")
public class ParametrTest {
    static Faker faker = new Faker();
    static String firstName = faker.name().firstName();
    static String userEmail = faker.internet().emailAddress();
    static String currentAddress = faker.address().fullAddress();
    static String userPermanentAddress = faker.address().fullAddress();

    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    static Stream<Arguments> methodSourceTextBox() {
        return Stream.of(
                Arguments.of("Kirill", "kirillkatkov@gmail.com ", "Address 1", "Address 2"),
                Arguments.of("Ivan", "ivanivanov@gmail.com ", "Address 3", "Address 4")
        );
    }

    @ValueSource(strings = {"Kiriill", "Ivan"})
    @ParameterizedTest(name = "Заполнение формы с помощью ValueSource {0}")
    void valueSourceForm(String testData) {
        textBoxPage.openPage().
                setFirstName(testData).setUserEmail(userEmail).setCurrentAddress(currentAddress).setPermanentAddress(userPermanentAddress)
                .submitClick();
    }

    @CsvSource(value = {
            "Kirill| kirillkatkov@gmail.com | Address 1 | Address 2",
            "Ivan| ivanivanov@gmail.com | Address 3 | Address 4"
    },
            delimiter = '|'
    )


    @ParameterizedTest(name = "Заполнение формы с помощью CsvSource {0}")
    void csvSourceForm(String name, String email, String currentAddress, String permanentAddress) {
        textBoxPage.openPage().
                setFirstName(name).setUserEmail(email).setCurrentAddress(currentAddress).setPermanentAddress(permanentAddress)
                .submitClick();
        textBoxPage.checkResult("Name:" + name).checkResult("Email:" + email).checkResult("Current Address :" + currentAddress).checkResult("Permananet Address :" + permanentAddress);
    }

    @MethodSource("methodSourceTextBox")
    @ParameterizedTest(name = "Заполнение формы c помощью MethodSource {0}")
    void methodSourceExampleTest(String name, String email, String currentAddress, String permanentAddress) {
        textBoxPage.openPage().
                setFirstName(name).setUserEmail(email).setCurrentAddress(currentAddress).setPermanentAddress(permanentAddress)
                .submitClick();
    }

    @EnumSource(TitleEnum.class)
    @ParameterizedTest(name = "Проверка с помощью Enum")
    void enumSourceTest(TitleEnum testData) {
        textBoxPage.openPage().userFormTitle(testData.rusName);
        System.out.println(testData.rusName);
        Assertions.assertEquals("Text Box", testData.rusName);
    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }
}
