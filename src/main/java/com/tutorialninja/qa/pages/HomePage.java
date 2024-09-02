package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	//Objects
	@FindBy(xpath="//span[text()=\"My Account\"]")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement RegisterOption;
	
	@FindBy(name="search")
	private WebElement SearchBoxField;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement SearchButton;
	
	
	
	public HomePage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Actions
	public void clickOnMyAccount() {
		
		myAccountDropMenu.click();
	}
	
	public LoginPage clickOnLoginOption() {
		
		loginOption.click();
		return new LoginPage(driver);
		
	}
	
	public RegisterPage clickOnRegisterOption() {
		
		RegisterOption.click();
		return new RegisterPage(driver);
	}
	
	public void EnterValueIntoSearchBoxField(String searchText ) {
		
		SearchBoxField.sendKeys(searchText);
		
	}
	
	public SearchPage ClickOnSearchButton() {
		
		SearchButton.click();
		return new SearchPage(driver);
	}

}
