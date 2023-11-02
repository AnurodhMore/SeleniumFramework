package Academy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Academy.pageobjects.CartPage;
import Academy.pageobjects.OrderPage;

public class AbstractComponent 
{
	WebDriver driver;
	
     public AbstractComponent(WebDriver driver)
     {
		// TODO Auto-generated constructor stub
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
	}


   
     @FindBy(css="button[routerlink='/dashboard/cart']")
     WebElement cartHeader;
     
     @FindBy(css="//button[@routerlink='/dashboard/myorders']")
     WebElement orderHeader;
     
     
	//By.cssSelector(".mb-3") is a By element and not a web element
	public void waitForElementToAppear(By findBy)
	{
	    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));  // Explicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
	    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));  // Explicit wait
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	public void waitForElementToDisappear(WebElement ele) 
	{
	//	Thread.sleep(1000); // applcn is low as not real-time
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));  // Explicit wait
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	}
	
	//cart is present in header of the screen and is common for all so adding it in abstract class
	
	public void waitForElementToBeClickable() 
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));  // Explicit wait
		wait.until(ExpectedConditions.elementToBeClickable
				(driver.findElement(By.cssSelector("a[class='btnn action__submit ng-star-inserted']"))));			
	}
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);   
		return cartPage;
	}
	
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);   
		return orderPage;
		
	}
	
	
}
