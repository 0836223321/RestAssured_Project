package Utilities;

import Base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreenshotUtil extends TestBase {

    public static String screenshotPath;
    public static String screenshotName;

    public static void captureScreenshot() throws IOException {

       /* File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
        FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
*/
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        screenshotName = date.toString().replace(":", "_").replace("_", "_");
        FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "\\src\\test\\reports" +screenshotName+".jpg"));
    }


}
