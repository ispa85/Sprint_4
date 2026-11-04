package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RentPage {
    private final WebDriverWait wait;
    private final WebDriver driver;

    private final By rentHeader = By.xpath("//div[text()='Про аренду']");
    private final By dateFieldLocator = By.xpath("//input[contains(@placeholder, 'Когда привезти')]");
    private final By daysDropdown = By.xpath("//div[contains(@class, 'Dropdown-root')]");
    private final By checkBoxBlack = By.id("black");
    private final By checkBoxGrey = By.id("grey");
    private final By commentField = By.xpath("//input[@placeholder = 'Комментарий для курьера']");
    private final By orderButton = By.xpath("//div[contains(@class, 'Order_Buttons')]/button[text()='Заказать']");

    public RentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void setDate(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(rentHeader));
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(dateFieldLocator));

        // Очищаем поле и вводим дату
        field.sendKeys(Keys.CONTROL + "a");
        field.sendKeys(Keys.BACK_SPACE);
        field.sendKeys(date);
        field.sendKeys(Keys.ENTER);

        // Снимаем фокус с инпута даты, чтобы закрыть календарь без клика по перекрытому заголовку
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", field);
    }

    public void setDays(String days) {
        By dropdownControl = By.className("Dropdown-control");
        WebElement control = wait.until(ExpectedConditions.presenceOfElementLocated(dropdownControl));

        // Прокручиваем к дропдауну в центр видимой области
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", control);

        // Ждем кликабельности и открываем меню
        wait.until(ExpectedConditions.elementToBeClickable(control)).click();

        // Локатор варианта из списка по тексту с нормализацией пробелов
        By optionLocator = By.xpath("//div[contains(@class, 'Dropdown-option') and normalize-space()='" + days + "']");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        option.click();
    }

    public void clickCheckBox(boolean black, boolean grey) {
        if (black) {
            driver.findElement(checkBoxBlack).click();
        }
        if (grey) {
            driver.findElement(checkBoxGrey).click();
        }
    }

    public void setComment(String comment) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(commentField));
        field.clear();
        field.sendKeys(comment);
    }

    public void clickOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    public void toOrder(String date, String days, String comment, boolean black, boolean grey) {
        setDate(date);
        setDays(days);
        clickCheckBox(black, grey);
        setComment(comment);
        clickOrderButton();
    }
}
