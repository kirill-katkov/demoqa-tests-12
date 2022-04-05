package quru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTests {
    static String firstName;
    static String lastName;
    static String userEmail;
    static String gender;
    static String mobileNumber;
    static String subject;
    static String hobby;
    static String imgPath;
    static String currentAddress;
    static String state;
    static String city;


    @BeforeAll
    static void setAllSitting() {
        firstName = "Kirill";
        lastName = "Katkov";
        userEmail = "kirillkatkov@gmail.com";
        gender = "Male";
        mobileNumber = "9041995985";
        subject = "English";
        hobby = "Sports";
        imgPath = "img/test.png";
        currentAddress = "moskovskaya 55";
        state = "NCR";
        city = "Delhi";
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void automatedFormTest() { //
        open("/automation-practice-form");
        //zoom(0.5); //установка масштаба для маленьких экранов
        // hide footer and GoogleAds (as Submit btn is not visible on my screen)
        Selenide.executeJavaScript("document.querySelector(\"footer\").hidden = 'true';" +
                "document.querySelector(\"#fixedban\").hidden = 'true'");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(mobileNumber);
        //$(byXpath("//*[@id='submit']")).scrollIntoView(true);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1995");
        $(byText("15")).click();
        $("#subjectsInput").setValue("E").pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath(imgPath);
        $("#currentAddress").setValue(currentAddress);
        $(".css-1wa3eu0-placeholder").click();
        $(byText(state)).click();
        $(".css-1wa3eu0-placeholder").click();
        $(byText(city)).click();
        $("#submit").click();
        $(".modal-body").shouldHave(
                text(firstName + " " + lastName),
                text(userEmail),
                text(gender),
                text(mobileNumber),
                text("Date of Birth 15 November,1995"),
                text(subject),
                text(hobby),
                text(imgPath.substring(4)),
                text(currentAddress),
                text(state + " " + city)
        );

        $("#closeLargeModal").click();
        $(".modal-content").shouldNotBe(visible);
    }
}