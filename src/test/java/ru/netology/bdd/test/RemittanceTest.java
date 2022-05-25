package ru.netology.bdd.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.bdd.data.DataHelper;
import ru.netology.bdd.data.LoginPage;
import ru.netology.bdd.data.WrongRefillPage;
import ru.netology.bdd.data.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RemittanceTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferFromFirstCardToSecond() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validCode(verificationCode);
        var firstNumber = DataHelper.getFirstNumber();
        var secondNumber = DataHelper.getSecondNumber();
        int amount = 1000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstNumber) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondNumber) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondNumber);
        dashboardPage = transferPage.makeTransfer(String.valueOf(amount), firstNumber);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstNumber);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondNumber);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void shouldTransferFromSecondCardToFirst() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validCode(verificationCode);
        var firstNumber = DataHelper.getSecondNumber();
        var secondNumber = DataHelper.getFirstNumber();
        int amount = 1000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstNumber) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondNumber) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondNumber);
        dashboardPage = transferPage.makeTransfer(String.valueOf(amount), firstNumber);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstNumber);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondNumber);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void shouldIncorrectTransfer() {
        var authInfo = DataHelper.getAuthInfo();
        var loginPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var codeInput = VerificationPage.validCode(verificationCode);
        var refillPage = WrongRefillPage.incorrectCardTopUp();
    }
}