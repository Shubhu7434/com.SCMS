package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	private static ExtentReports extent;
	
	private static String reportPath ;

	public static ExtentReports getReportInstance() {
		if (extent == null) {

			// Report Path
			
			//String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

			reportPath = "C:\\Reports\\SCMS\\AUtomationReport.html";

			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

			spark.config().setReportName("SCMS Automation Test Result");
			spark.config().setDocumentTitle("Test Execution Report");
			

			extent = new ExtentReports();
			extent.attachReporter(spark);

			// System Info
			extent.setSystemInfo("Tester", "Shubham");
			extent.setSystemInfo("Project_Name", "SCMS");
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("Browser", "Chrome");
		}
		
		return extent;
	}
	
	public static String getReportPath() {
		return reportPath ;
	}

}
