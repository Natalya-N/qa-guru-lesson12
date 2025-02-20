import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.TestDataGenerator;
import java.util.List;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import static io.qameta.allure.Allure.step;

@Tag("simple")
public class RegistrationWithPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestDataGenerator testData = new TestDataGenerator();

    @Tag("smoke")
    @Test
    void magentoSTBsearchTest() {
        open("https://magento.softwaretestingboard.com/");
        $("#search").sendKeys("pants");
        $("#search").pressEnter();
        $(".product-item-link").shouldBe(visible);
        $(".product-item-link").shouldHave(text("Pant"));
    }

    @Tag("smoke")
    @Test
    void theInternetHerokuTest() {
        open("https://the-internet.herokuapp.com/login");
        $("#username").shouldBe(Condition.interactable, Duration.ofSeconds(20));
        $("#username").setValue("tomsmith");
        $("#password").setValue("SuperSecretPassword!");
        $("button.radius").click();
        $("div.flash").shouldHave(text("You logged into a secure area!"));
    }

    @Tag("smoke")
    @Test
    void successfulDuckDuckGoSearchTest() {
        open("https://duckduckgo.com/");
        $("[id=searchbox_input]").setValue("selenide").pressEnter();
        $("section[data-testid='mainline']").shouldHave(text("Selenide: concise UI tests in Java"));
    }
    
    @Test
    void successfulRegistrationTest() {
        step("Open page and set data", () -> {
            registrationPage.openPage()
                    .setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setEmail(testData.emailAddress)
                    .setGender(testData.gender)
                    .setUserNumber(testData.mobileNumber)
                    .setDateOfBirth(testData.dayOfBirth, testData.monthOfBirth, testData.yearOfBirth)
                    .setSubjects(testData.subjects)
                    .setHobbiesWrapper(testData.hobbies)
                    .setUploadPicture("git.png")
                    .setCurrentAddress(testData.currentAddress)
                    .setState(testData.state)
                    .setCity(testData.city);
        });
        step("Submit form", () -> {
            registrationPage.submit();
        });
        step("Check modal dialog", () -> {
            registrationPage.checkModalDialogAppear()
                    .checkDataInTable("Student Name", testData.firstName + " " + testData.lastName)
                    .checkDataInTable("Student Email", testData.emailAddress)
                    .checkDataInTable("Gender", testData.gender)
                    .checkDataInTable("Mobile", testData.mobileNumber)
                    .checkDataInTable("Date of Birth", testData.dayOfBirth + " " + testData.monthOfBirth + "," + testData.yearOfBirth)
                    .checkDataInTable("Subjects", testData.subjects)
                    .checkDataInTable("Hobbies", testData.hobbies)
                    .checkDataInTable("Picture", "git.png")
                    .checkDataInTable("Address", testData.currentAddress)
                    .checkDataInTable("State and City", testData.state + " " + testData.city);
        });
    }

    @Test
    void successfulRegistrationMinimumValuesTest() {
        step("Open page and set data", () -> {
            registrationPage.openPage()
                    .setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setEmail(testData.emailAddress)
                    .setGender(testData.gender)
                    .setUserNumber(testData.mobileNumber)
                    .setDateOfBirth(testData.dayOfBirth, testData.monthOfBirth, testData.yearOfBirth);
        });
        step("Submit form", () -> {
            registrationPage.submit();
        });
        step("Check modal dialog", () -> {
            registrationPage.checkModalDialogAppear()
                    .checkDataInTable("Student Name", testData.firstName + " " + testData.lastName)
                    .checkDataInTable("Student Email", testData.emailAddress)
                    .checkDataInTable("Gender", testData.gender)
                    .checkDataInTable("Mobile", testData.mobileNumber)
                    .checkDataInTable("Date of Birth", testData.dayOfBirth + " " + testData.monthOfBirth + "," + testData.yearOfBirth);
        });
    }

    @Test
    void unsuccessfulRegistrationTest() {
        step("Open page and set no data, than submit and check no dialog appears", () -> {
            registrationPage.openPage()
                    .setFirstName(testData.firstName)
                    .submit()
                    .checkModalDialogNotAppear();
        });
    }
}
