package ru.netology.bdd.data;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;

public class WrongRefillPage {

    private static final Faker faker = new Faker(new Locale("ru"));

    public static int getRandomAmount() {

        int amountToRefill = faker.number().numberBetween(0, 10_000);
        return amountToRefill;
    }

    public static WrongRefillPage incorrectCardTopUp() {
        $("data-test-id=0f3f5c2a-249e-4c3d-8287-09f7a039391d]");
        $("[data-test-id=action-deposit]").click();
        $("[data-test-id=amount] input").setValue(String.valueOf(getRandomAmount()));
        $("[data-test-id=from] input").setValue(IncorrectData.getFirstIncorrectNumber().firstIncorrectNumber);
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=error-notification]")
                .shouldHave(Condition.text("Произошла ошибка"));
        return new WrongRefillPage();
    }
}