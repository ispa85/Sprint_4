package Pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderPageTest {
    private WebDriver driver;
    private final String browser;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String dateOrder;
    private final String daysOrder;
    private final boolean checkBoxBlack;
    private final boolean checkBoxGrey;
    private final String comment;

    public OrderPageTest(String browser, String name, String surname, String address, String metroStation,
                         String phoneNumber, String dateOrder, String daysOrder,
                         boolean checkBoxBlack, boolean checkBoxGrey, String comment) {
        this.browser = browser;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.dateOrder = dateOrder;
        this.daysOrder = daysOrder;
        this.checkBoxBlack = checkBoxBlack;
        this.checkBoxGrey = checkBoxGrey;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] dataTest() {
        return new Object[][] {
                // Тестовый набор 1
                {"firefox", "Ирина", "Хлыстова", "ул. Мира, 99", "Чистые пруды", "+79998884455", "30.10.2026", "трое суток", true, false, "Жду самокат"},
                {"chrome", "Ирина", "Хлыстова", "ул. Мира, 99", "Чистые пруды", "+79998884455", "30.10.2026", "трое суток", true, false, "Жду самокат"},
                // Тестовый набор 2
                {"chrome", "Алексей", "Smirnov", "Ленинградский пр., 12", "Сокольники", "89001112233", "28.10.2026", "сутки", false, true, "Позвоните заранее"}
        };
    }

    @Before
    public void setup() {
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        // driver = new ChromeDriver(options);
        // driver.manage().window().maximize();
        if ("firefox".equalsIgnoreCase(browser)) {
            // Перед этим не забудьте добавить зависимость geckodriver или использовать WebDriverManager
            driver = new org.openqa.selenium.firefox.FirefoxDriver();
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
    }

    @Test
    public void toOrderScooter() {

        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickOrderButton();

        OrderPage objOrderPageScooter = new OrderPage(driver);
        objOrderPageScooter.nextToOrder(name, surname, address, metroStation, phoneNumber);

        RentPage objRentPage = new RentPage(driver);
        objRentPage.toOrder(dateOrder, daysOrder, comment, checkBoxBlack, checkBoxGrey);

        SubmitPage objSubmitPageScooter = new SubmitPage(driver);
        objSubmitPageScooter.clickYesButton();

        ConfirmPage objConfirmPage = new ConfirmPage(driver);
        assertTrue("Всплывающее окно подтверждения не появилось", objConfirmPage.isVisible());
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
