package utils;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextBoxTests {


    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        String name = "Kirill Katkov";
        open("/text-box");
        $("[id=userName]").setValue(name);
        $("[id=userEmail]").setValue("kirillkatkov@gmail.com");
        $("[id=currentAddress]").setValue("Some street 1");
        $("[id=permanentAddress]").setValue("Another street 2");
        $("[id=submit]").click();

        $("[id=output]").shouldHave(
                text(name), text("kirillkatkov@gmail.com"),
                text("Some street 1"), text("Another street 2"));


        $("[id=output] [id=name]").shouldHave(text(name));//первый вариант поиска вложенного тега
        $("[id=output]").$("[id=name]").shouldHave(text(name)); //второй вариант поиска вложенного тега
       $("[id=name]").shouldHave(text(name)); //второй вариант поиска вложенного тега

        $("[id=permanentAddress]").shouldHave(text("Permananet Address :Another street 2"));//wrong
        $("p[id=permanentAddress]").shouldHave(text("Permananet Address :Another street 2"));
        $("[id=permanentAddress]",1).shouldHave(text("Permananet Address :Another street 2"));

        String expectedPermanentAddress="Another street 2";
        String actualPermanentAddress=$("p[id=permanentAddress]").text();
        assertEquals(expectedPermanentAddress, actualPermanentAddress);
    }
}
