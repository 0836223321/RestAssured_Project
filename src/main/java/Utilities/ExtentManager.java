package Utilities;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;
import java.util.Date;

public class ExtentManager {


    private static ExtentReports extentReports;


    public static ExtentReports getInstance(){

        if(extentReports==null){

            Date date = new Date();
            String  fileName = date.toString().replace(":", "_").replace("", "_")+".html";
            extentReports = new ExtentReports(System.getProperty("user.dir") + "\\src\\test\\reports"+fileName+"", true, DisplayOrder.OLDEST_FIRST);
            extentReports.loadConfig(new File(System.getProperty("user.dir")+"\\src\\main\\java\\ExtentReportConfig\\extent-config.xml"));

        }

        return extentReports;

    }
}
