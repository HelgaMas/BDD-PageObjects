package ru.netology.bdd.page;

import ru.netology.bdd.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    public static DashboardPage validCode(DataHelper.VerificationCode code) {
        $("[data-test-id=code] input").setValue(code.getCode());
        $("[data-test-id=action-verify]").click();
        return new DashboardPage();
    }
}