package Pages;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class HomePageTest {
    private WebDriver driver;


    @Test
    public void openAccordion() {
        // драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        int[] numberText = new int [] {0, 4, 7};
        String[] expectedText = new String [] {
                "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                "Да, обязательно. Всем самокатов! И Москве, и Московской области."
        } ;
        HomePage objHomePageScooter = new HomePage(driver);
        for (int i = 0; i < numberText.length; i++) {
            objHomePageScooter.checkAccordionButtonText(numberText[i], expectedText[i]);
        }

    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
