package Academy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	@FindBy(className = "hero-primary")
	WebElement confirmMessage;
	
	
	public ConfirmationPage(WebDriver driver)  // CONSTRUCTOR FOR INITIALIZING DRIVER
	{        
		super(driver); // every child class has to serve parent with driver
		this.driver =driver;
		PageFactory.initElements(driver, this); // initializing here as constructor is the first method to execute
	}
	
	
	public String getConfirmationMessage()
	{
		return confirmMessage.getText();
	}
	

}
