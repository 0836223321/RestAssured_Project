package Base;

import Utilities.ExcelReader;
import Utilities.ExtentManager;
import Utilities.ScreenshotUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class TestBase {


    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties elements = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel = new ExcelReader(
            System.getProperty("user.dir") + "\\src\\test\\java\\Testdata\\Pets Test Data.xlsx");
    public static WebDriverWait wait;
    public ExtentReports extentReports = ExtentManager.getInstance();
    public static ExtentTest test;
    public static String browser;

    @BeforeSuite
    public void setUp() throws IOException {

        if (driver == null) {

            try {
                fis = new FileInputStream(
                        System.getProperty("user.dir") + "\\src\\test\\java\\Properties\\Config.properties");
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }
            try {
                config.load(fis);
                //log.debug("Config file loaded !!!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fis = new FileInputStream(
                        System.getProperty("user.dir") + "\\src\\test\\java\\Properties\\Elements.properties");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                elements.load(fis);
                //log.debug("OR file loaded !!!");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            if (System.getenv("browser") == null || System.getenv("browser").isEmpty()) {

                browser = config.getProperty("browser");

            } else {

                browser = System.getenv("browser");
            }

            config.setProperty("browser", browser);

            if (config.getProperty("browser").equals("firefox")) {

                // System.setProperty("webdriver.gecko.driver", "gecko.exe");
                driver = new FirefoxDriver();

            } /*else if (config.getProperty("browser").equals("chrome")) {

                System.setProperty("webdriver.chrome.driver",
                        System.getProperty("user.dir") + "\\src\\test\\java\\executables\\chromedriver.exe");
                driver = new ChromeDriver();*/
            //log.debug("Chrome Launched !!!");
        } else if (config.getProperty("browser").equals("ie")) {

            System.setProperty("webdriver.ie.driver",
                    System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();

        }

           /* driver.get(config.getProperty("QMSUrl"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
                    TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 5);*/
    }



    public void click(String locator) {

        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(elements.getProperty(locator))).click();
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(elements.getProperty(locator))).click();
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(elements.getProperty(locator))).click();
        }
        test.log(LogStatus.INFO, "Clicking on : " + locator);
    }

    public void type(String locator, String value) {

        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(elements.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_xpath")) {
            driver.findElement(By.xpath(elements.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(elements.getProperty(locator))).sendKeys(value);
        }

        test.log(LogStatus.INFO, "Typing in : " + locator + " entered value as " + value);

    }

    static WebElement dropdown;

    public void select(String locator, String value) {

        if (locator.endsWith("_CSS")) {
            dropdown = driver.findElement(By.cssSelector(elements.getProperty(locator)));
        } else if (locator.endsWith("_XPATH")) {
            dropdown = driver.findElement(By.xpath(elements.getProperty(locator)));
        } else if (locator.endsWith("_ID")) {
            dropdown = driver.findElement(By.id(elements.getProperty(locator)));
        }

        Select select = new Select(dropdown);
        select.selectByVisibleText(value);

        test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + value);

    }

    public boolean isElementPresent(By by) {

        try {

            driver.findElement(by);
            return true;

        } catch (NoSuchElementException e) {

            return false;

        }

    }

    public static void verifyEquals(String expected, String actual) throws IOException {

        try {

            Assert.assertEquals(actual, expected);

        } catch (Throwable t) {

            ScreenshotUtil.captureScreenshot();
            // ReportNG
            Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
            Reporter.log("<a target=\"_blank\" href=" + ScreenshotUtil.screenshotName + "><img src=" + ScreenshotUtil.screenshotName
                    + " height=200 width=200></img></a>");
            Reporter.log("<br>");
            Reporter.log("<br>");
            // Extent Reports
            test.log(LogStatus.FAIL, " Verification failed with exception : " + t.getMessage());
            test.log(LogStatus.FAIL, test.addScreenCapture(ScreenshotUtil.screenshotName));

        }

    }

    @AfterSuite
    public void tearDown() {
        driver.quit();




        //log.debug("test execution completed !!!");
    }
}
