package ru.netology.data;

import lombok.Value;

//данные для авторизации
public class DataHelper {
    private DataHelper() {

    }

    @Value
    public static class UserInfo{
        private String login;
        private String password;
    }

    //первый пользователь
    public static UserInfo getUserInfo1() {
        return new UserInfo("vasya", "qwerty123");
    }

    //второй пользователь
    public static UserInfo getUserInfo2() {
        return new UserInfo("petya", "555");
    }

    @Value
    public static class VerificationCode {
    private String code;
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
    }
}
