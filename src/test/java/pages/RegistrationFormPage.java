package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    SelenideElement
            userFirstName = $("#firstName"),
            userLastName = $("#lastName"),
            userEmail = $("#userEmail"),
            userGender = $("#genterWrapper"),
            userMobileNumber = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            userSubjects = $("#subjectsInput"),
            userHobbies = $("#hobbiesWrapper"),
            upLoadPicture = $("#uploadPicture"),
            userCurrentAddress = $("#currentAddress"),
            setUserState = $("#react-select-3-input"),
            setUserCity = $("#react-select-4-input"),
            submitClick = $("#submit"),
            tableResponsive = $(".table-responsive"),
            practiceFormWrapper = $(".practice-form-wrapper"),
            practiceFormTitle = $("#example-modal-sizes-title-lg"),
            closeCheckForm = $("#closeLargeModal");
    private final CalendarComponent calendar = new CalendarComponent();

    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        practiceFormWrapper.shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    public RegistrationFormPage setFirstName(String firstName) {
        userFirstName.setValue(firstName);
        return this;
    }

    public RegistrationFormPage setLastName(String lastName) {
        userLastName.setValue(lastName);
        return this;
    }

    public RegistrationFormPage setEmail(String email) {
        userEmail.setValue(email);
        return this;
    }

    public RegistrationFormPage setGender(String gender) {
        userGender.$(byText(gender)).click();
        return this;
    }

    public RegistrationFormPage setUserMobileNumber(String userNumber) {
        userMobileNumber.setValue(userNumber);
        return this;
    }

    public RegistrationFormPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendar.setDateOfBirth(day, month, year);
        return this;
    }


    public RegistrationFormPage setSubjects(String subjects) {
        userSubjects.setValue(subjects).pressEnter();
        return this;
    }

    public RegistrationFormPage setUserHobby(String hobbies) {
        userHobbies.$(byText(hobbies)).click();
        return this;
    }

    public RegistrationFormPage upLoadPicture(String namePicture) {
        upLoadPicture.uploadFromClasspath(namePicture);
        return this;
    }

    public RegistrationFormPage setCurrentAddress(String address) {
        userCurrentAddress.setValue(address);
        return this;
    }

    public RegistrationFormPage setState(String state) {
        setUserState.setValue(state).pressEnter();
        return this;
    }

    public RegistrationFormPage setCity(String City) {
        setUserCity.setValue(City).pressEnter();
        return this;
    }

    public RegistrationFormPage submitClick() {
        submitClick.click();
        return this;
    }


    public RegistrationFormPage checkResultTitle() {
        practiceFormTitle.shouldHave(text("Thanks for submitting the form"));
        return this;
    }


    public RegistrationFormPage checkResult(String key, String value) {
        tableResponsive.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }

    public void closeForm() {
        closeCheckForm.click();
    }


}
