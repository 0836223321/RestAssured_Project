package Testcases;

import Base.Constants;
import Listeners.ExtentReport;
import Utilities.DataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class VerifySuccessfulMessageTest {

    ExtentReport extentReport = new ExtentReport();


    @Test(dataProviderClass = DataProvider.class, dataProvider = "ReadData")
    public void verifySuccessfulMessageTest(Hashtable<String, String> data) throws InterruptedException {


        extentReport.start(data.get("Test_Case_Description"));

        Response response =
                given()
                        .when()
                        .get(Constants.verifySuccessfulMessageURL)
                        .then()
                        .log().body()
                        .extract().response();
        String json =  response.asString();
        Assert.assertEquals(json.contains("success"), true);

        extentReport.success("Successful Message Returned");
    }

    @AfterTest
    public void tearDown(){
        extentReport.onFinish();
    }
}
