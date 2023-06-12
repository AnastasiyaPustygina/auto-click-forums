package com.example.autoclickforums.service;

import com.example.autoclickforums.cache.MessageCache;
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

import java.net.URL;

@Service
@RequiredArgsConstructor
public class AutoClickService {
    private final MessageCache messageCache;
    private final Logger logger = LoggerFactory.getLogger(AutoClickService.class);

    public void clickButton(String url, String buttonId){
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        Message message = new Message(url, "Успешное нажатие на кнопку", Status.SUCCESS);
        try{
            WebElement button = driver.findElement(By.id(buttonId));
            button.click();
        }catch (Exception e){
            message.setText("Нажатие на кнопку привело к ошибке");
            logger.error(e.getMessage());
            message.setStatus(Status.FAILURE);
        }
        messageCache.addToList(message);
    }

}
