package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldSuccessfullySendForm() {
        String formattedString = generateDate(4);
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(formattedString);
        $("[data-test-id='name'] input").setValue("Киреев-Пушкарев Александр");
        $("[data-test-id='phone'] input").setValue("+79234556677");
        $("[class='checkbox__box']").click();
        $("[class='button__text']").click();
        $(Selectors.withText("Встреча успешно")).shouldBe(hidden, Duration.ofMillis(10000));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + formattedString), Duration.ofSeconds(15));
    }
}