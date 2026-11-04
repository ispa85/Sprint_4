package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SubmitPage {

    private WebDriver driver;
    //локатор кнопки Да
    private By yesButtonLocator  = By.xpath(".//button[text()='Да']");

    public SubmitPage (WebDriver driver) {
        this.driver = driver;
    }
    public void clickYesButton() {
        // 1. Дожидаемся, пока кнопка "Да" станет видимой на экране
        WebElement yesButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(yesButtonLocator));

        // 2. Кликаем по ней принудительно через JavaScript в обход всех перекрывающих окон
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", yesButton);
    }

}

