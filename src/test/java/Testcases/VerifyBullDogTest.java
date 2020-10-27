package Testcases;

import Base.Constants;
import Listeners.ExtentReport;
import Utilities.DataProvider;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import java.util.Hashtable;

import static io.restassured.RestAssured.given;
public class VerifyBullDogTest {

    ExtentReport extentReport = new ExtentReport();


    @Test(dataProviderClass = DataProvider.class, dataProvider = "ReadData")
    public void verifyBullDogTest(Hashtable<String, String> data) throws InterruptedException {


        extentReport.start(data.get("Test_Case_Description"));

        try {
            given()
            .contentType("application/json")
            .when()
            .get(Constants.verifyBullDogURL)
            .then()
            .statusCode(200)
            .body(".message.bulldog[0]", equalTo("boston"))
            .body(".message.bulldog[1]", equalTo("english"))
            .body(".message.bulldog[2]", equalTo("french"));

                }catch (Exception e){

            extentReport.failure("Failed To Verify Bulldog");
            }
            extentReport.success("Bulldog Successfully Verified");
    }

    @AfterTest
    public void tearDown() {
        extentReport.onFinish();

    }
}
