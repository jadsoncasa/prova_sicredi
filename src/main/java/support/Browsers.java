package support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Browsers {

    private static String chapa = Utils.getPropertiesValue("chapa");
    private static String senha = Utils.getPropertiesValue("chapa.senha");

    private static WebDriver getChromeDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    public static WebDriver getGeckodriver(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static WebDriver getSeleniumDocker(){
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setBrowserName("chrome");
        try {
            return new RemoteWebDriver(new URL("http://10.10.43.200:4444/wd/hub"), capability);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WebDriver getHeadlessChrome(){
        ChromeOptions chromeOpt = new ChromeOptions();
        chromeOpt.addArguments("--headless");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOpt);
    }

    public static WebDriver getChromeFlashEnable(String url){
        WebDriverManager.chromedriver().setup();

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.state.flash",1);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }

    public static WebDriver openBrowser(WebDriver driver, String url){
        String browser = Utils.getPropertiesValue("browser");
        switch (browser) {
            case "grid":
                driver = getSeleniumDocker();
                driver.get(url);
                break;
            case "headless":
                driver = getHeadlessChrome();
                driver.get(url);
                break;
            case "flash_enable":
                driver = getChromeFlashEnable(url);
                driver.get(url);
                break;
            default:
                driver = getChromeDriver();
                driver.get(url);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}