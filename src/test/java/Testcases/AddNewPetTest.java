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

public class AddNewPetTest {

    ExtentReport extentReport = new ExtentReport();


    @Test(dataProviderClass = DataProvider.class, dataProvider = "ReadData")
    public void addNewPetTest(Hashtable<String, String> data) throws InterruptedException {

        extentReport.start(data.get("Test_Case_Description"));

        try {

            HashMap data_2 = new HashMap();
            data_2.put("id", data.get("ID"));
            data_2.put("name", data.get("Name"));
            data_2.put("status", data.get("Status"));

            Response response =
                    given()
                            .contentType("application/json")
                            .body(data_2)
                            .when()
                            .post(Constants.addNewPetURL)
                            .then()
                            .statusCode(200)
                            .log().body()
                            .extract().response();
            String json = response.asString();
            Assert.assertEquals(json.contains("https://petstore.swagger.io/v2/pet"), true);
        }catch (Exception e){

            extentReport.failure(e.getMessage());
        }
        extentReport.success("Available Pets Successfully Retrieved");
    }

    @AfterTest
    public void tearDown(){
        extentReport.onFinish();

    }
}
