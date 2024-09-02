package com.tutorialninja.qa.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.SearchPage;

//Updated comment

public class SearchTest extends Base {

	public WebDriver driver;
	SearchPage searchPage;

	public SearchTest() throws IOException {

		super();
	}

	@BeforeMethod
	public void Setup() {

		driver = InitializeBrowserAnsOpenApplicationURL(prop.getProperty("browserName"));
	}

	@AfterMethod
	public void TearDown() {

		driver.quit();
	}

	@Test(priority = 1)
	public void VerifySearchWithValidProduct() {
		
		HomePage homePage=new HomePage(driver);
		homePage.EnterValueIntoSearchBoxField(dataProp.getProperty("validProduct"));
		//driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		searchPage=homePage.ClickOnSearchButton();
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();		
		Assert.assertTrue(searchPage.DisplayStatusOfValidProduct(), "Valid product HP is not displayed.");		
		//Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(), "Valid product HP is not displayed.");

	}

	@Test(priority = 2)
	public void VerifySearchWithInvalidProduct() {
		
		HomePage homePage=new HomePage(driver);
		homePage.EnterValueIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
		//driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));		
		searchPage=homePage.ClickOnSearchButton();
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();		
		String actualSearchMessage = searchPage.DisplayInvalidPorductMessage();		
		//String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		String expectedSearchMessage ="abc";
		//dataProp.getProperty("expectedSearchMessageForInvalidProduct")
		Assert.assertEquals(actualSearchMessage, expectedSearchMessage,
				"Search message of invalid product is displayed.");

	}

	@Test(priority = 3, dependsOnMethods= {"VerifySearchWithInvalidProduct"})
	public void VerifySearchWithoutAnyProduct() {
		
		HomePage homePage=new HomePage(driver);
		searchPage=homePage.ClickOnSearchButton();
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		String actualSearchMessage = searchPage.DisplayInvalidPorductMessage();	
		//String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		String expectedSearchMessage = dataProp.getProperty("expectedSearchMessageForInvalidProduct");
		Assert.assertEquals(actualSearchMessage, expectedSearchMessage,
				"Search message of invalid product is displayed.");

	}

}
