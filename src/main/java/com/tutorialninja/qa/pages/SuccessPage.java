package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessPage {
	
	WebDriver driver;
	
	//Objects
	@FindBy(xpath="//div[@id=\"content\"]/h1")
	private WebElement verifyMessageAccountIsCreated;
	
	public SuccessPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Actions
	
	public String RetrieveMessageAccountIsCreated() {
		
		String accountIsCreatedMessage=verifyMessageAccountIsCreated.getText();
		return accountIsCreatedMessage;
	}

}
