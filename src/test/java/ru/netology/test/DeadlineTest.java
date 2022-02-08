package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.database.Database;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @BeforeEach
    public void setUp() {
        //открываем сайт
        open("http://localhost:9999/");
    }

    @SneakyThrows
    @Test
        //УСПЕШНАЯ АВТОРИЗАЦИЯ
    void shouldBeSuccessfulAuthorization() {
        //создаём переменную и инициализируем её новым объектом
        var loginPage = new LoginPage();
        //получаем информацию для авторизации (валидный пользователь)
        var authInfo = DataHelper.getUserInfo1();
        //передаёём информацию по авторизации (логин и пароль)
        var verificationPage = loginPage.validLogin(authInfo);
        //вызываем метод для запроса пуш-кода (код верификации)
        var verificationCode = Database.getVerificationCode(authInfo.getLogin());
        //открывается страница — личный кабинет
        var dashboardPageActual = verificationPage.validVerify(verificationCode);
    }

    @Test
        //пользователя должны блокировать после 3 попыток ввода неверного пароля
    void shouldBlockedAfterThreeInvalidPassword() {
        //создаём переменную и инициализируем её новым объектом
        var loginPage = new LoginPage();
        //получаем информацию для авторизации (валидный пользователь)
        var authoInfo = DataHelper.getUserInfo2();
        //вводим неверный пароль и нажимаем продолжить
        loginPage.invalidPassword(authoInfo);
        //очищаем поля
        loginPage.cleanLoginPage();
        //вводим неверный пароль и нажимаем продолжить 2 раз
        loginPage.invalidPassword(authoInfo);
        //очищаем поля
        loginPage.cleanLoginPage();
        //вводим неверный пароль и нажимаем продолжить 3 раз
        loginPage.invalidPassword(authoInfo);
        //получаем статус
        var statusSQL = Database.getStatus(authoInfo.getLogin());

        //сравниваем фактический и ожидаемый результат
        assertEquals("blocked", statusSQL);
    }

    @AfterAll
    //очищаем данные в таблицах
    static void close() {
        Database.cleanDatabase();
    }
}
