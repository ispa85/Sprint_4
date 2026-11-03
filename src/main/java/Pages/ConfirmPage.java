package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ConfirmPage {
    private final WebDriver driver;

    public ConfirmPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By orderConfirmed = By.xpath("//div[contains(@class, 'Order_ModalHeader') and contains(text(), 'Заказ оформлен')]");

    public boolean isVisible() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(orderConfirmed)) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
