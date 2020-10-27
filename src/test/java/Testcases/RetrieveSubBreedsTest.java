package Testcases;

import Base.Constants;
import Listeners.ExtentReport;
import Utilities.DataProvider;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class RetrieveSubBreedsTest {

    ExtentReport extentReport = new ExtentReport();


    @Test(dataProviderClass = DataProvider.class, dataProvider = "ReadData")
    public void retrieveSubBreedsTest(Hashtable<String, String> data) throws InterruptedException {


        extentReport.start(data.get("Test_Case_Description"));

        given()
                .when()
                .get(Constants.retrieveSubBreedsURL)
                .then()
                .statusCode(200)
                .log().body();

        extentReport.success("Sub Breeds For Bulldog Successfully Retrieved");
    }

    @AfterTest
    public void tearDown() {
        extentReport.onFinish();
    }
}
