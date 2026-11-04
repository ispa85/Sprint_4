package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class WebDriverFactory {

    public static WebDriver create(String browser) {
        String b = browser.toLowerCase();
        if ("firefox".equals(b)) {
            return createFirefox();
        }
        return createChrome();
    }

    private static WebDriver createChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless"); // headless для Firefox
        return new FirefoxDriver(options);
    }
}
