package com.example.autoclickforums.service;

import com.example.autoclickforums.cache.MessageCache;
import com.example.autoclickforums.domain.Forum;
import com.example.autoclickforums.domain.Message;
import com.example.autoclickforums.domain.Status;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizeService {
    private final Logger logger = LoggerFactory.getLogger(AutoClickService.class);
    private final MessageCache messageCache;

    public void authorize(Forum forum){
        Message message = new Message(forum.getUrl(), "Успешная авторизация", Status.SUCCESS);
        WebDriver driver = new ChromeDriver();
        driver.get(forum.getUrl());
        try{
            openAuthorizationWindow(forum.getIdElements().getIdButtonAuthorize(), driver);
            editData(forum.getIdElements().getIdInputLogin(), forum.getUser().getLogin(),
                    forum.getIdElements().getIdInputPassword(), forum.getUser().getPassword(), driver);
            try{
                openAuthorizationWindow(forum.getIdElements().getIdButtonLogin(), driver);
            }catch (Exception e){
                message.setText("Ошибка авторизации. Ошибка при отправке данных для авторизации");
                logger.error(e.getMessage());
                message.setStatus(Status.FAILURE);
            }
        }catch (Exception e){
            message.setText("Ошибка авторизации. Нажатие на кнопку авторизации привело к ошибке");
            logger.error(e.getMessage());
            message.setStatus(Status.FAILURE);
        }
        messageCache.addToList(message);
    }
    private void openAuthorizationWindow(String idButtonAuthorize, WebDriver driver){
        WebElement button = driver.findElement(By.id(idButtonAuthorize));
        button.click();
    }
    private void editData(String idInputLogin, String login, String idInputPassword, String password, WebDriver driver){
        WebElement inputLogin = driver.findElement(By.id(idInputLogin));
        WebElement inputPassword = driver.findElement(By.id(idInputPassword));
        inputLogin.sendKeys("value", login);
        inputPassword.sendKeys("value", password);
    }
    private void login(String idButtonLogin, WebDriver driver){
        WebElement buttonLogin = driver.findElement(By.id(idButtonLogin));
        buttonLogin.click();
    }
}
