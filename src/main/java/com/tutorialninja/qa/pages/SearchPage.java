package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	//Objects
	
	@FindBy(linkText="HP LP3065")
	private WebElement ValidHPProduct;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement InvalidProductMessage;
	
	public SearchPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean DisplayStatusOfValidProduct() {
		
		boolean displayStatus=ValidHPProduct.isDisplayed();
		return displayStatus;
		
	}
	
	public String DisplayInvalidPorductMessage() {
		
		String noProductMessage = InvalidProductMessage.getText();
		return noProductMessage;
		
	}

}
