package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TextBoxPage {

    SelenideElement
            userName = $("#userName"),
            userEmail = $("#userEmail"),
            userCurrentAddress = $("#currentAddress"),
            userPermanentAddress = $("#permanentAddress"),
    userFormTitle = $(".main-header"),
            submitClick = $("#submit"),
   formResult = $(".border");


    public TextBoxPage openPage() {
        open("/text-box");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    public TextBoxPage setFirstName(String firstName) {
        userName .setValue(firstName);
        return this;
    }

    public TextBoxPage setUserEmail(String userMainEmail) {
        userEmail.setValue(userMainEmail);
        return this;
    }

    public TextBoxPage setCurrentAddress(String currentAddress) {
        userCurrentAddress.setValue(currentAddress);
        return this;
    }
    public TextBoxPage setPermanentAddress(String PermanentAddress) {
        userPermanentAddress.setValue(PermanentAddress);
        return this;
    }


    public TextBoxPage submitClick() {
        submitClick.click();
        return this;
    }


    public TextBoxPage checkResult(String value) {
        formResult.shouldHave(text(value));
        return this;
    }

    public TextBoxPage userFormTitle(String value) {
        userFormTitle.shouldHave(text(value)); //"Text Box"
        return this;
    }
}
