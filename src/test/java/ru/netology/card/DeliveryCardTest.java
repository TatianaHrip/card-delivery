package ru.netology.card;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class DeliveryCardTest {
    @Test
    void DeliveryCardFormTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Калининград");
        String dateMeeting = LocalDate.now().plusDays(17).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement dateElement = $("[data-test-id=date] input[class=input__control]");
        dateElement.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        dateElement.setValue(dateMeeting);
        $("[data-test-id=name] input").setValue("Анастасия");
        $("[data-test-id=phone] input").setValue("+79117875432");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification] .notification__content").waitUntil(visible, 15000)
                .shouldHave(exactText("Встреча успешно забронирована на " + dateMeeting));

    }
}
