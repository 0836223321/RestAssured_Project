package Listeners;

import Utilities.ExtentManager;
import Utilities.ScreenshotUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ExtentReport {

    public ExtentReports extentReports = ExtentManager.getInstance();
    public static ExtentTest extentTest;

    public void start(String result) {

        extentTest = extentReports.startTest(result);
        /*if(!DataProvider.isTestRunnable(result, excel)){

            throw new SkipException("Test:" +" "+ result +" "+ "Skipped");
        }*/

    }

    public void success(String result) {
        extentTest.log(LogStatus.PASS, result);
        extentReports.endTest(extentTest);

        try{

            ScreenshotUtil.captureScreenshot();
        }catch (IllegalArgumentException e){

            e.printStackTrace();

            extentTest.addScreenCapture(ScreenshotUtil.screenshotName);
            Reporter.log("Click Here To See Screenshot");
            Reporter.log("<a target=\"_blank\" href" + ScreenshotUtil.screenshotName+">Screenshot</a>");
            Reporter.log("<br>");
            Reporter.log("<br>");
            Reporter.log("<a target=\"_blank\" href=" + ScreenshotUtil.screenshotName+">img src=" + ScreenshotUtil.screenshotName);
            extentReports.endTest(extentTest);
            extentReports.flush();

        }catch (Exception e){

            e.getStackTrace();

        }

    }


    public void failure(String result) {
        extentTest.log(LogStatus.FAIL, result);
        extentReports.endTest(extentTest);


        try {
            ScreenshotUtil.captureScreenshot();
        }catch (IllegalArgumentException e){

            e.printStackTrace();

            extentTest.addScreenCapture(ScreenshotUtil.screenshotName);
            Reporter.log("Click Here To See Screenshot");
            Reporter.log("<a target=\"_blank\" href" + ScreenshotUtil.screenshotName+">Screenshot</a>");
            Reporter.log("<br>");
            Reporter.log("<br>");
            Reporter.log("<a target=\"_blank\" href=" + ScreenshotUtil.screenshotName+">img src=" + ScreenshotUtil.screenshotName);
            extentReports.endTest(extentTest);
            extentReports.flush();

        }catch (Exception e){

            e.getStackTrace()
            ;
        }

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish() {
        extentReports.flush();

    }
}
