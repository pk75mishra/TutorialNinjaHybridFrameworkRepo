package com.tutorialninja.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() {

		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File("E:\\SeleniumTesting\\LiveProject\\TutorialNinjaProject\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialNinja Test Automation Results Report");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("DD/MM/YYYY hh:mm:ss");

		extentReport.attachReporter(sparkReporter);
		Properties configProp = new Properties();
		File configFile = new File(
				"E:\\SeleniumTesting\\LiveProject\\TutorialNinjaProject\\src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");
		FileInputStream fisConfigProp;
		try {
			fisConfigProp = new FileInputStream(configFile);
			configProp.load(fisConfigProp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		extentReport.setSystemInfo("OS Name", System.getProperty("os.name"));
		extentReport.setSystemInfo("Java  Version", System.getProperty("java.version"));
		extentReport.setSystemInfo("Username", System.getProperty("user.name"));

		return extentReport;

	}

}
