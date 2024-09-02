package com.tutorialninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialninja.qa.util.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public Base() throws IOException {

		// File f= new
		// File(System.getProperty("user.dir")+"src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");
		// E:\\SeleniumTesting\LiveProject\TutorialNinjaProject\src\main\java\com\tutorialninja\qa\config\config.properties
		File f = new File(
				"E:\\SeleniumTesting\\LiveProject\\TutorialNinjaProject\\src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(f);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File fData = new File(
				"E:\\SeleniumTesting\\LiveProject\\TutorialNinjaProject\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\testdata.properties");
		FileInputStream fisData;
		try {
			fisData = new FileInputStream(fData);
			dataProp = new Properties();
			dataProp.load(fisData);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public WebDriver InitializeBrowserAnsOpenApplicationURL(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicit_wait_time));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.page_load_time));
		driver.get(prop.getProperty("url"));
		return driver;

	}

}
