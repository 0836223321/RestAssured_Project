package Testcases;

import Base.Constants;
import Listeners.ExtentReport;
import Utilities.DataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class RetrievePetTest {

    ExtentReport extentReport = new ExtentReport();


    @Test(dataProviderClass = DataProvider.class, dataProvider = "ReadData")
    public void retrievePetTest(Hashtable<String, String> data) throws InterruptedException {


        extentReport.start(data.get("Test_Case_Description"));

        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .get(Constants.retrievePetURL)
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();


        extentReport.success("Pet Successfully Retrieved Using ID");
    }

    @AfterTest
    public void tearDown(){
        extentReport.onFinish();

    }
}
