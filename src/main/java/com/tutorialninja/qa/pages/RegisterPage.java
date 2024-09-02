package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	//Objects
	
	@FindBy(id="input-firstname")
	private WebElement inputFirstName;
	
	@FindBy(id="input-lastname")
	private WebElement inputLastName;
	
	@FindBy(id="input-email")
	private WebElement inputEmailAddress;
	
	@FindBy(id="input-telephone")
	private WebElement inputTelephoneNumber;
	
	@FindBy(id="input-password")
	private WebElement inputPassword;
	
	@FindBy(id="input-confirm")
	private WebElement inputConfirmPassword;
	
	@FindBy(name="newsletter")
	private WebElement confirmNewsletter;
	
	@FindBy(name="agree")
	private WebElement clickOnConfirmPrivacyPolicyCheckbox;
	
	@FindBy(xpath="//input[@value=\"Continue\"]")
	private WebElement clickOnContinueButtonOnRegisterPage;
	
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	private WebElement warningEmailIsAlreadyRegistered;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	//Actions
	
	public void EnterFirstNameText(String firstName) {
		
		inputFirstName.sendKeys(firstName);
		
	}
	
	public void EnterLastNameText(String lastName) {
		
		inputLastName.sendKeys(lastName);
	}
	
	public void EnterEmailAddressText(String emailAddress) {
		
		inputEmailAddress.sendKeys(emailAddress);
	}
	
	public void EnterTelephoneNumber(String telephoneNumber) {
		
		inputTelephoneNumber.sendKeys(telephoneNumber);
	}
	
	public void EnterPasswordText(String passwordText) {
		
		inputPassword.sendKeys(passwordText);
		
	}
	
	public void EnterConfirmPasswordText(String confirmPassword) {
		
		inputConfirmPassword.sendKeys(confirmPassword);
	}
	
	public void ClickOnConfirmNewsletter() {
		
		confirmNewsletter.click();
	}
	
	public void ClickOnConfirmPrivacyPolicyCheckbox() {
		
		clickOnConfirmPrivacyPolicyCheckbox.click();
	}
	
	public SuccessPage ClickOnContinueButton() {
		
		clickOnContinueButtonOnRegisterPage.click();
		return new SuccessPage(driver);
	}
	
	public String WarningMessageForExistingEmailId() {
		
		String warningForExistingEmailId=warningEmailIsAlreadyRegistered.getText();
		return warningForExistingEmailId;
	}
	
	

}
