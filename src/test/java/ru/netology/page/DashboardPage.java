package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

//страница личного кабинета
public class DashboardPage {
    //находим заголовок Личный кабинет
    private SelenideElement heading = $("[data-test-id=dashboard]");

    //проверяем, виден ли заголовок ЛИЧНЫЙ КАБИНЕТ
    public DashboardPage() {
        heading.shouldBe(visible);
    }
}
