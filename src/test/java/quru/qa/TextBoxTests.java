package quru.qa;

import static com.codeborne.selenide.Selenide.open;
import org.junit.jupiter.api.Test;

public class TextBoxTests {

    @Test
    void fillFormTest(){
       open("https://demoqa.com/text-box");
    }
}
