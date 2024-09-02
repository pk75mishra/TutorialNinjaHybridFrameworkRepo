package com.tutorialninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pages.AccountPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.LoginPage;
import com.tutorialninja.qa.util.Utilities;

public class LoginTest extends Base {
	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;

	public LoginTest() throws IOException {

		super();
	}

	@BeforeMethod
	public void SetUp() {

		driver = InitializeBrowserAnsOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		// driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		loginPage = homePage.clickOnLoginOption();
		// driver.findElement(By.linkText("Login")).click();

	}

	@AfterMethod
	public void TearDown() {

		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void VerifyLoginWithValidCredentials(String email, String password) {

		
		loginPage.EnterEmailAddress(email);
		// driver.findElement(By.id("input-email")).sendKeys(email);
		loginPage.EnterPassword(password);
		// driver.findElement(By.id("input-password")).sendKeys(password);
		accountPage=loginPage.ClickOnLoginButton();
		// driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();		
		Assert.assertTrue(accountPage.getDisplayStatusOfeditYourAccountInformationText());

	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {

		Object[][] data = null;
		try {
			data = Utilities.GetTestDataFromExcel("Login");
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

	@Test(priority = 2)
	public void VerifyLoginWithInvalidCredentials() {
		
		loginPage.EnterEmailAddress(Utilities.GenerateTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.GenerateTimeStamp());
		loginPage.EnterPassword(dataProp.getProperty("invalidPassword"));
		//driver.findElement(By.id("input-password")).sendKeys("1234");
		loginPage.ClickOnLoginButton();
		//driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		String actualWarningMessage = loginPage.RetrivEmailPasswordNotMatchingWarningMessage();
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
		

	}

	@Test(priority = 4)
	public void VerifyLoginWithInvalidEmailAndValidPassword() {
		
		
		loginPage.EnterEmailAddress(Utilities.GenerateTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.GenerateTimeStamp());
		loginPage.EnterPassword(prop.getProperty("validPassword"));
		
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		loginPage.ClickOnLoginButton();
		//driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualWarningMessage = loginPage.RetrivEmailPasswordNotMatchingWarningMessage();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		//String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, \"alert-dismissible\")]")).getText();
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
		//System.out.println(actualWarningMessage);

	}

	@Test(priority = 5)
	public void VerifyLoginWithOutEmailAndPassword() {
		
		
		loginPage.ClickOnLoginButton();
		//driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		String actualWarningMessage = loginPage.RetrivEmailPasswordNotMatchingWarningMessage();
		//String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, \"alert-dismissible\")]")).getText();
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
		//System.out.println(actualWarningMessage);

	}

	@Test(priority = 3)
	public void VerifyLogout() {
		
		driver.findElement(By.id("input-email")).sendKeys("pk75mishra@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Logout")).click();

	}

}
