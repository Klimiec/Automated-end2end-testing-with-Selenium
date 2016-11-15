package common.logging;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.configuration.Configuration;
import org.apache.commons.io.FileUtils;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ReportLogging {

    private static ExtentReports extentReport;
    private static String reportDirectory;

    public static void createReport() {

        String reportPath = createReportDirectoryAndFullReportPath();
        createReportDirectory();
        extentReport = new ExtentReports(reportPath, true);
        environmentInfo(extentReport);
    }

    public static void closeReport() {
        extentReport.close();
    }

    public static void succeeded(String[] testCategories, Description description) {

        if (!reportExists())
            return;

        final ExtentTest test = beginTest(testName(description), testCategories, description.getDisplayName());
        test.log(LogStatus.PASS, "-");
        endTest(test);
    }

    public static void failed(String[] testCategories, Description description, String exceptionStackTrace, WebDriver driver) {

        if (!reportExists())
            return;

        final ExtentTest test = beginTest(testName(description), testCategories, description.getDisplayName());
        test.log(LogStatus.FAIL, "Failure trace Selenium: " + exceptionStackTrace);
        takeScreenshot(driver, test, description.getMethodName());
        endTest(test);
    }

    public static void skipped(String[] testCategories, Description description) {

        if (!reportExists())
            return;

        final ExtentTest test = beginTest(testName(description), testCategories, description.getDisplayName());
        test.log(LogStatus.SKIP, "-");
        endTest(test);
    }

    private static ExtentTest beginTest(String testName, String[] testCategories, String testDetails) {
        return extentReport
                .startTest(testName, testDetails)
                .assignCategory(testCategories);
    }

    private static void endTest(ExtentTest test) {
        extentReport.endTest(test);
        extentReport.flush();
    }

    private static String createReportDirectoryAndFullReportPath() {

        String date = LocalDate.now().toString().replace(":", ".");
        String time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString().replace(":", ".");


        String REPORT_SUB_DIRECTORY_LOCATION_FORMAT = "%s/%s_%s_%s_%s";
        String reportSubDir = String.format(REPORT_SUB_DIRECTORY_LOCATION_FORMAT,
                Configuration.environment().toString(),
                Configuration.country(),
                Configuration.language(),
                date,
                time
                );

        String REPORT_FILE_NAME_FORMAT = "/report_%s_%s_%s_%s.html";
        String reportName = String.format(REPORT_FILE_NAME_FORMAT,
                Configuration.environment(),
                Configuration.country(),
                date,
                time);

        reportDirectory = Configuration.reportDirectoryPath() + reportSubDir;
        String fullReportPath = reportDirectory + reportName;


        return fullReportPath;
    }

    private static void createReportDirectory() {
        new File(reportDirectory).mkdirs();
    }

    private static void environmentInfo(ExtentReports extent) {
        extent.addSystemInfo("Environment", Configuration.environment().toString());
        extent.addSystemInfo("Country", Configuration.country());
        extent.addSystemInfo("E-commerce platform", Configuration.ecommerceName());
        extent.addSystemInfo("Browser", Configuration.browser().getName());
    }

    private static void takeScreenshot(WebDriver driver, ExtentTest test, String fileName)  {

        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = reportDirectory  + "/" + scrFile.getName();
            FileUtils.copyFile(scrFile, new File(screenshotPath));
            test.log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture(scrFile.getName()));
        } catch (IOException e) {
            throw new RuntimeException("Not able to take screenshot ", e);
        }
    }

    private static boolean reportExists() {
        if (extentReport != null) {
            return true;
        }
        return false;
    }

    private static String testName(Description description) {
        // class name without package
        final String[] splitClassName = description.getTestClass().toString().split("\\.");
        String classNameWithoutPackage = splitClassName[splitClassName.length - 1];

        // method name turned into sentence
        String methodName = description.getMethodName().split("\\(")[0];
        if(methodName.contains(":")) {
            methodName = methodName.split(":")[0].trim() + "]";
        }
        methodName = String.join(" ", methodName.split("(?=[A-Z])")).toLowerCase();

        return classNameWithoutPackage + ": " + methodName;
    }

}
