package Utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import static Base.TestBase.excel;

public class DataProvider {

    @org.testng.annotations.DataProvider(name = "ReadData")
    public Object[][] ReadData(Method m) {

        String sheetName = m.getName();
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object[][] data = new Object[rows - 1][1];

        Hashtable<String,String> table = null;

        for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

            table = new Hashtable<String,String>();

            for (int colNum = 0; colNum < cols; colNum++) {

                // data[0][0]
                table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
                data[rowNum - 2][0] = table;
            }

        }

        return data;

    }


    public static boolean isTestRunnable(String testName, ExcelReader excel){

        String sheetName="test_suite";
        int rows = excel.getRowCount(sheetName);


        for(int rNum=2; rNum<=rows; rNum++){

            String testCase = excel.getCellData(sheetName, "TC_ID", rNum);

            if(testCase.equalsIgnoreCase(testName)){

                String runmode = excel.getCellData(sheetName, "Runmode", rNum);

                if(runmode.equalsIgnoreCase("Y"))
                    return true;
                else
                    return false;
            }


        }
        return false;
    }
}
