package ru.netology.database;

import lombok.SneakyThrows;

import java.sql.*;

public class Database {

    //пустой конструктор
    private Database() {

    }

    @SneakyThrows
    //получаем код верификации
    public static String getVerificationCode(String login) {
        String userId = null;
        //определяем запрос к БД в строку
        String dataSQLId = "SELECT id FROM users WHERE login = ?;";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
                //создаём консоль, через которую будем создавать запрос к БД
                PreparedStatement idStatment = connection.prepareStatement(dataSQLId);
        ) {
            idStatment.setString(1, login); //подставляем в SQL запросе значение вместо ?
            try (ResultSet rs = idStatment.executeQuery()) {
                if (rs.next()) {  //определяем есть ли следующая строка в возвращённом из селекта результате
                    userId = rs.getString("id");  //сохраняем полученный id
                }
            }
        }
        String code = null;
        String dataSQLCode = "SELECT code FROM auth_codes WHERE user_id = ? order by created desc limit 1;";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
                //создаём консоль, через которую будем создавать запрос к БД
                PreparedStatement codeStatment = connection.prepareStatement(dataSQLCode);
        ) {
            codeStatment.setString(1, userId); //подставляем в SQL запросе значение вместо ?
            try (ResultSet rs = codeStatment.executeQuery()) {
                if (rs.next()) {  //определяем есть ли следующая строка в возвращённом из селекта результате
                    code = rs.getString("code");  //сохраняем полученный код
                }
            }
        }
        return code;
    }

    @SneakyThrows
    //получаем статус
    public static String getStatus(String login) {
        String dataSQLStatus = "SELECT status FROM users WHERE login = ?;";
        String status = null;
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
                //создаём консоль, через которую будем создавать запрос к БД
                PreparedStatement statusStatment = connection.prepareStatement(dataSQLStatus);
        ) {
            statusStatment.setString(1, login); //подставляем в SQL запросе значение вместо ?
            try (ResultSet rs = statusStatment.executeQuery()) {
                if (rs.next()) {  //определяем есть ли следующая строка в возвращённом из селекта результате
                    status = rs.getString("status");  //сохраняем полученный статус
                }
            }
        }
        return status;
    }

    @SneakyThrows
    //очищаем БД
    public static void cleanDatabase() {
        String deleteAuthCodes = "DELETE FROM auth_codes WHERE TRUE;";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
             Statement deleteAuthCodesStatmrnt = connection.createStatement();
        ) {
            deleteAuthCodesStatmrnt.executeUpdate(deleteAuthCodes);
        }
    }
}