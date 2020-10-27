package Testcases;

import Base.Constants;
import Base.TestBase;
import Listeners.ExtentReport;
import Utilities.DataProvider;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class RetrieveAvailablePetsTest {

    ExtentReport extentReport = new ExtentReport();


    @Test(dataProviderClass = DataProvider.class, dataProvider = "ReadData")
    public void retrieveAvailablePetsTest(Hashtable<String, String> data) throws InterruptedException {


        extentReport.start(data.get("Test_Case_Description"));
        try {
            given()
                    .when()
                    .get(Constants.availablePetsURL)
                    .then()
                    .statusCode(200);

            extentReport.success("Available Pets Successfully Retrieved");
        } catch (Exception e) {

            extentReport.failure(e.getMessage());
        }

    }

    @AfterTest
    public void tearDown(){
        extentReport.onFinish();
        //TestBase.driver.quit();
    }
}
