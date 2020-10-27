package Testcases;

import Base.Constants;
import Listeners.ExtentReport;
import Utilities.DataProvider;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class VerifyPetByIDTest {


    ExtentReport extentReport = new ExtentReport();


    @Test(dataProviderClass = DataProvider.class, dataProvider = "ReadData")
    public void verifyPetByIDTest(Hashtable<String, String> data) throws InterruptedException {


        given()
                .when()
                .get(Constants.verifyPetByIDURL)
                .then()
                .statusCode(200);

        extentReport.start(data.get("Test_Case_Description"));

        try {


        } catch (Exception e) {

            extentReport.failure(e.getMessage());
        }

        extentReport.success("Pet Successfully Retrieved By ID");
    }

    @AfterTest
    public void tearDown(){
        extentReport.onFinish();

    }
}
