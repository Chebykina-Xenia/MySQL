package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

//Страница входа
public class LoginPage {
    //находим поле логин
    private SelenideElement loginField = $("[data-test-id=login] input");
    //находим поле пароль
    private SelenideElement passwordField = $("[data-test-id=password] input");
    //находим кнопку продолжить
    private SelenideElement authButton = $("[data-test-id=action-login]");

    //очищаем поля на странице входа
    public void cleanLoginPage() {
        //кликаем дважды в поле логин и стираем значение
        loginField.doubleClick().sendKeys(Keys.BACK_SPACE);
        //кликаем дважды в поле пароль и стираем значение
        passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
    }

    public VerificationPage validLogin(DataHelper.UserInfo info) {
        //заполняем поле логин
        loginField.setValue(info.getLogin());
        //заполняем поле пароль
        passwordField.setValue(info.getPassword());
        //кликаем по кнопке продолжить
        authButton.click();
        //возвращаем страницу, где вводим код верификации
        return new VerificationPage();
    }

    public void invalidPassword(DataHelper.UserInfo info) {
        //заполняем поле логин
        loginField.setValue(info.getLogin());
        //заполняем поле пароль
        passwordField.setValue(info.getPassword());
        //кликаем по кнопке продолжить
        authButton.click();
    }

}
