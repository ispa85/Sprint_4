package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;


//класс главной страницы
public class HomePage {
    private WebDriver driver;
    private final WebDriverWait wait;
    private static final By COOKIE_BANNER = By.cssSelector(".App_CookieConsent");
    private static final By COOKIE_BUTTON = By.cssSelector("[class*='App_CookieConsent'] button, [class*='App_CookieConsent'] a, [class*='App_CookieConsent'] [role='button']");
    private static final By ORDER_BUTTON = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        closeCookieBanner();
    }

    private void closeCookieBanner() {
        List<WebElement> buttons = driver.findElements(COOKIE_BUTTON);
        if (!buttons.isEmpty()) {

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buttons.get(0));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(COOKIE_BANNER));
        }
    }

    public void clickOrderButton () {
        driver.findElement(ORDER_BUTTON).click();
    }

    public void checkAccordionButtonText (int numberText, String expectedText) {
        By accordionButton = By.id("accordion__heading-" + numberText);
        By accordionButtonText = By.id("accordion__panel-" + numberText);

        driver.findElement(accordionButton).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement paragraph = wait.until(ExpectedConditions.visibilityOfElementLocated(accordionButtonText));
        String actualText = paragraph.getText();
        Assert.assertEquals("Текст элемента №" + numberText + " не совпадает с ожидаемым!", expectedText, actualText);
    }

}
