package com.tutorialninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.RegisterPage;
import com.tutorialninja.qa.pages.SuccessPage;
import com.tutorialninja.qa.util.Utilities;

public class RegisterTest extends Base {

	public WebDriver driver;
	RegisterPage registerPage;
	SuccessPage successPage;

	public RegisterTest() throws IOException {

		super();
	}

	@BeforeMethod
	public void SetUp() {

		driver = InitializeBrowserAnsOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		// driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		registerPage = homePage.clickOnRegisterOption();
		// driver.findElement(By.linkText("Register")).click();
	}

	@AfterMethod
	public void TearDown() {

		driver.quit();
	}

	@Test(priority = 1)
	public void VerifyRegisteringAccountWithMandatoryFields() {

		registerPage.EnterFirstNameText(dataProp.getProperty("firstName"));
		// driver.findElement(By.id("input-firstname")).sendKeys("Pawan");
		registerPage.EnterLastNameText(dataProp.getProperty("lastName"));
		// driver.findElement(By.id("input-lastname")).sendKeys("mishra");
		registerPage.EnterEmailAddressText(Utilities.GenerateTimeStamp());
		// driver.findElement(By.id("input-email")).sendKeys(Utilities.GenerateTimeStamp());
		registerPage.EnterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		// driver.findElement(By.id("input-telephone")).sendKeys("1234561");
		registerPage.EnterPasswordText(dataProp.getProperty("inputPassword"));
		// driver.findElement(By.id("input-password")).sendKeys("12345");
		registerPage.EnterConfirmPasswordText(dataProp.getProperty("confirmPassword"));
		// driver.findElement(By.id("input-confirm")).sendKeys("12345");
		registerPage.ClickOnConfirmPrivacyPolicyCheckbox();
		// driver.findElement(By.name("agree")).click();
		successPage = registerPage.ClickOnContinueButton();
		// driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();

		String actualSuccessHeading = successPage.RetrieveMessageAccountIsCreated();
		// String actualSuccessHeading = //
		// driver.findElement(By.xpath("//div[@id=\"content\"]/h1")).getText();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!", "Account is not created.");
	}

	@Test(priority = 2)
	public void VerifyRegisteringAccountWithAllFields() {

		registerPage.EnterFirstNameText(dataProp.getProperty("firstName"));
		// driver.findElement(By.id("input-firstname")).sendKeys("Pawan");
		registerPage.EnterLastNameText(dataProp.getProperty("lastName"));
		// driver.findElement(By.id("input-lastname")).sendKeys("mishra");
		registerPage.EnterEmailAddressText(Utilities.GenerateTimeStamp());
		// driver.findElement(By.id("input-email")).sendKeys(Utilities.GenerateTimeStamp());
		registerPage.EnterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		// driver.findElement(By.id("input-telephone")).sendKeys("1234561");
		registerPage.EnterPasswordText(dataProp.getProperty("inputPassword"));
		// driver.findElement(By.id("input-password")).sendKeys("12345");
		registerPage.EnterConfirmPasswordText(dataProp.getProperty("confirmPassword"));
		// driver.findElement(By.id("input-confirm")).sendKeys("12345");
		registerPage.ClickOnConfirmNewsletter();
		// driver.findElement(By.name("newsletter")).click();
		registerPage.ClickOnConfirmPrivacyPolicyCheckbox();
		// driver.findElement(By.name("agree")).click();
		successPage = registerPage.ClickOnContinueButton();
		// driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
		String actualSuccessHeading = successPage.RetrieveMessageAccountIsCreated();
		// String actualSuccessHeading =
		// driver.findElement(By.xpath("//div[@id=\"content\"]/h1")).getText();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!", "Account is not created.");
	}

	@Test(priority = 3)
	public void VerifyRegisterAccountWithExistingEmailAddress() {

		registerPage.EnterFirstNameText(dataProp.getProperty("firstName"));
		// driver.findElement(By.id("input-firstname")).sendKeys("Pawan");
		registerPage.EnterLastNameText(dataProp.getProperty("lastName"));
		// driver.findElement(By.id("input-lastname")).sendKeys("mishra");
		registerPage.EnterEmailAddressText(dataProp.getProperty("existingEmailAddress"));
		// driver.findElement(By.id("input-email")).sendKeys(Utilities.GenerateTimeStamp());
		registerPage.EnterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		// driver.findElement(By.id("input-telephone")).sendKeys("1234561");
		registerPage.EnterPasswordText(dataProp.getProperty("inputPassword"));
		// driver.findElement(By.id("input-password")).sendKeys("12345");
		registerPage.EnterConfirmPasswordText(dataProp.getProperty("confirmPassword"));
		// driver.findElement(By.id("input-confirm")).sendKeys("12345");
		registerPage.ClickOnConfirmNewsletter();
		// driver.findElement(By.name("newsletter")).click();
		registerPage.ClickOnConfirmPrivacyPolicyCheckbox();
		// driver.findElement(By.name("agree")).click();
		successPage = registerPage.ClickOnContinueButton();
		// driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
		String expectedWarningMessage = "Warning: E-Mail Address is already registered!";
		String actualWarningMessage = registerPage.WarningMessageForExistingEmailId();
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Warning message related to duplicate emailId is not displayed.");

	}

	@Test(priority = 4)
	public void VerifyRegisterAccountWithoutFillingAnyDetails() {

		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();

		// Privacy policy
		String expectedWarningMessage = "Warning: You must agree to the Privacy Policy!";
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]"))
				.getText();

		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Warning message related to privacy policy is not displayed.");

		// First name
		String expectedFirstNameMessage = "First Name must be between 1 and 32 characters!";
		String actualFirstNameMessage = driver
				.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();

		Assert.assertTrue(actualFirstNameMessage.contains(expectedFirstNameMessage),
				"Error message related to first name is not displayed.");

		// Last name
		String expectedLastNameMessage = "Last Name must be between 1 and 32 characters!";
		String actualLastNameMessage = driver
				.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();

		Assert.assertTrue(actualLastNameMessage.contains(expectedLastNameMessage),
				"Error message related to last name is not displayed.");

		// E-Mail
		String expectedEmailMessage = "E-Mail Address does not appear to be valid!";
		String actuaEmailMessage = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div"))
				.getText();

		Assert.assertTrue(actuaEmailMessage.contains(expectedEmailMessage),
				"Error message related to Email is not displayed.");

		// Telephone
		String expectedTelephoneMessage = "Telephone must be between 3 and 32 characters!";
		String actuaTelephoneMessage = driver
				.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();

		Assert.assertTrue(actuaTelephoneMessage.contains(expectedTelephoneMessage),
				"Error message related to Telephone is not displayed.");

		// Password
		String expectedPasswordMessage = "Password must be between 4 and 20 characters!";
		String actuaPasswordMessage = driver
				.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();

		Assert.assertTrue(actuaPasswordMessage.contains(expectedPasswordMessage),
				"Error message related to Password is not displayed.");

	}

}
