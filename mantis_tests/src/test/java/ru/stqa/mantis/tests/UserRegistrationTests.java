package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    public static List<String> randomUsername() {
        return List.of(CommonFunctions.randomString(8));
    }

    @ParameterizedTest
    @MethodSource("randomUsername")
    public void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        //создать пользователя (адрес) на почтовом сервере (JamesHelper)
        app.jamesCli().addUser(email,"password");
        //заполняем форму создания и отправляем -- браузер
        app.user().createNewAccount(username, email);
        //ждём и получаем почту (MailHelper)
        var messages = app.mail().receive(email,"password", Duration.ofSeconds(10));
        //извлекаем ссылку из письма
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (!matcher.find()) {
            throw new AssertionError("Activation URL not found in email");
        }
        var url = text.substring(matcher.start(), matcher.end());
        if (url.isBlank()) {
            throw new AssertionError("Activation URL is empty");
        }
        //проходим по ссылке и завершаем регистрацию -- браузер
        app.user().activateUserAccount(url);
        //проверяем, что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, "password");
        Assertions.assertTrue(app.http().isLoggedIn());
    }

}
