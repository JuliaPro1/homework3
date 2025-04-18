import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FillStudentRegistrationForm {

    @BeforeAll
    static void beforeAll() {

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    void fillStudentFormTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Julia");
        $("#lastName").setValue("Amel");
        $("#userEmail").setValue("amel@car.ca");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("89804561456");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionByValue("2005");
        $(".react-datepicker__month-select").selectOptionByValue("7");
        $(".react-datepicker__day.react-datepicker__day--023").click();

        $("#subjectsInput").setValue("Maths");
        $(".subjects-auto-complete__option").shouldBe(Condition.visible);
        $(".subjects-auto-complete__option").click();

        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();

        $("#uploadPicture").uploadFromClasspath("photo.jpg");

        $("#currentAddress").setValue("New street 1");

        $("#state").click();
        $("#stateCity-wrapper").$(byText("Uttar Pradesh")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Lucknow")).click();

        $("#submit").click();


        //Checks
        $$(".table-responsive").findBy(text("Student Name")).shouldHave(text("Julia Amel"));
        $$(".table-responsive").findBy(text("Student Email")).shouldHave(text("amel@car.ca"));
        $$(".table-responsive").findBy(text("Gender")).shouldHave(text("Female"));
        $$(".table-responsive").findBy(text("Mobile")).shouldHave(text("8980456145"));
        $$(".table-responsive").findBy(text("Date of Birth")).shouldHave(text("23 August,2005"));
        $$(".table-responsive").findBy(text("Subjects")).shouldHave(text("Maths"));
        $$(".table-responsive").findBy(text("Hobbies")).shouldHave(text("Reading, Music"));
        $$(".table-responsive").findBy(text("Picture")).shouldHave(text("photo.jpg"));
        $$(".table-responsive").findBy(text("Address")).shouldHave(text("New street 1"));
        $$(".table-responsive").findBy(text("State and City")).shouldHave(text("Uttar Pradesh Lucknow"));

    }
}
