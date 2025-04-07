package api.utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    // onStart is executed when the test suite starts.
    @Override
    public void onStart(ITestContext testContext) {
        // Get timestamp to create a unique report name
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";  // Define the report name with timestamp

        // Set up the location of the report
        sparkReporter = new ExtentSparkReporter("./reports/" + repName); // Specify location of the report

        // Configuring the ExtentSparkReporter (HTML report)
        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");  // Title of the report
        sparkReporter.config().setReportName("Pet Store Users API");  // Name of the report
        sparkReporter.config().setTheme(Theme.DARK);  // Theme of the report (can be LIGHT or DARK)

        // Initialize ExtentReports and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Adding system information to the report
        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "Gaurav");
    }

    // onTestStart is executed when a test method starts
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());  // Start a new test in the report
    }

    // onTestSuccess is executed when a test method passes
    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test passed: " + result.getName());
    }

    // onTestFailure is executed when a test method fails
    @Override
    public void onTestFailure(ITestResult result) {
        test.fail("Test failed: " + result.getName());
        test.fail(result.getThrowable());  // Attach the error stack trace to the report
    }

    // onTestSkipped is executed when a test method is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test skipped: " + result.getName());
    }

    // onFinish is executed when the test suite finishes
    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();  // Write everything to the report
    }
}
