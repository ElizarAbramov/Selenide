package ru.netology.web;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {
    LocalDate localDate = LocalDate.now().plusDays(3);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
    String formattedString = localDate.format(formatter);


    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldSuccessfullySendForm() {
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(formattedString);
        $("[data-test-id='name'] input").setValue("Киреев-Пушкарев Александр");
        $("[data-test-id='phone'] input").setValue("+79234556677");
        $("[class='checkbox__box']").click();
        $("[class='button__text']").click();
        $(Selectors.withText("Встреча успешно")).shouldBe(hidden, Duration.ofMillis(10000));
    }
}