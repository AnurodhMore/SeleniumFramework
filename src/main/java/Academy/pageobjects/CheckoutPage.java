package Academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Academy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	 WebDriver driver;

	 @FindBy(css="input[placeholder='Select Country']")
	 String country;
	
//	 @FindBy(css=".action__submit")
//	 WebElement submit;
	//a[@class='btnn action__submit ng-star-inserted']/i
	 
//	 @FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']/i")
//	 WebElement submit;
//	 


	 
	 @FindBy(xpath="/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[2]/a")
	 WebElement submit;
	 
	 @FindBy(xpath="//button[contains(@class,'ta-item')][2]")
     WebElement selectCountry;
	
	 By results = By.cssSelector(".ta-results");
	 
//	 
//	 @FindBy(css="a[class='btnn action__submit ng-star-inserted']")
//	 WebElement actionClick;
	 
			 
	public CheckoutPage(WebDriver driver)  // CONSTRUCTOR FOR INITIALIZING DRIVER
	{        
		super(driver); // every child class has to serve parent with driver
		this.driver =driver;
		PageFactory.initElements(driver, this); // initializing here as constructor is the first method to execute
	}
	
	public void selectCountry(String cName)
	{
	    Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
	//	a1.sendKeys(country, cName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();	
	}
	
	public ConfirmationPage submitOrder() 
	{
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,400)");
//		waitForElementToBeClickable();
		Actions a2 = new Actions(driver);
		a2.moveToElement(submit).click().build().perform();
//		a2.moveToElement(submit).click().build().perform();
		submit.click();
		return new ConfirmationPage(driver);
	}

	
	
	
}
