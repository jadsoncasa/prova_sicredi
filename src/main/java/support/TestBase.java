package support;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.lang.reflect.Method;

public class TestBase {

    public WebDriver driver;
    public String url = "https://www.grocerycrud.com/demo/bootstrap_theme";

    @Parameters({"ambiente", "nome"})
    @BeforeMethod
    public void beforeMethod(Method method, @Optional("") String ambiente, @Optional("") String nome){
            driver = Browsers.openBrowser(driver, url);
    }

    @AfterMethod
    protected void afterMethod(ITestResult result)
    {
        driver.quit();
    }
}