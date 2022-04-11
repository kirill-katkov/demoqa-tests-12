package quru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;

import static java.lang.String.format;

public class RegistrationFormWithPageObjectTests {
    static Faker faker = new Faker();
    static String firstName;
    static String lastName;
    static String userEmail;
    static String gender;
    static String day;
    static String month;
    static String year;
    static String mobileNumber;
    static String subject;
    static String hobby;
    static String imgPath;
    static String imgPathResult;
    static String currentAddress;
    static String state;
    static String city;
    static String expectedFullName;
    static String expectedStateCityName;
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void setUp() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        userEmail = faker.internet().emailAddress();
        expectedFullName = format("%s %s", firstName, lastName);
        gender = "Male";
        day = "15";
        month = "November";
        year = "1995";
        mobileNumber = faker.numerify("##########");
        subject = "English";
        hobby = "Sports";
        imgPath = "img/test.png";
        imgPathResult = "test.png";
        currentAddress = faker.address().fullAddress();
        state = "NCR";
        city = "Delhi";
        expectedStateCityName = format("%s %s", state, city);

        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {

        registrationFormPage.openPage().
                setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setUserMobileNumber(mobileNumber)
                .setDateOfBirth(day, month, year)
                .setSubjects(subject)
                .setUserHobby(hobby)
                .upLoadPicture(imgPath)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submitClick();

        registrationFormPage.checkResultTitle()
                .checkResult("Student Name", expectedFullName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobileNumber)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", imgPathResult)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", expectedStateCityName);

        registrationFormPage.closeForm();

    }
}