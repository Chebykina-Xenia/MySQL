package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

//страница верификации
public class VerificationPage {
    //ищем поле для ввода кода
    private SelenideElement codeField = $("[data-test-id=code] input");
    //ищем кнопку подтверждения
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    //проверяем, что поле для ввода кода появилось
    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(String code) {
        //вводим код
        codeField.setValue(code);
        //кликаем по кнопке подтверждения
        verifyButton.click();
        //возвращаем страницу личного кабинета
        return new DashboardPage();
    }
}
