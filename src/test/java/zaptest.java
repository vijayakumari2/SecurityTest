
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.IOException;
import java.lang.reflect.Method;

import static utils.zapUtils.*;
public class zaptest {
        WebDriver driver;
        //private final String urlToTest="http://127.0.0.1:8080";
        private final String urlToTest="https://app.qa.az.memcrypt.io/";

        @BeforeMethod
        public void setUp(){
            ChromeOptions chromeOptions=new ChromeOptions();
            chromeOptions.setProxy(proxy);
            chromeOptions.setAcceptInsecureCerts(true);
            chromeOptions.addArguments("--remote-allow-origins=*");
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver(chromeOptions);
        }

        @Test
        public void testPassiveScan(){
            driver.get(urlToTest);
            waitTillPassiveScanCompleted();
        }

        @AfterMethod
        public void tearDown(Method method) throws ClientApiException, IOException {
            generateZapReport(urlToTest);
            driver.quit();
        }

    }

