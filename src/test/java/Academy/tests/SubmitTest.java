package Academy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Academy.TestComponents.BaseTest;
import Academy.pageobjects.CartPage;
import Academy.pageobjects.CheckoutPage;
import Academy.pageobjects.ConfirmationPage;
import Academy.pageobjects.LandingPage;
import Academy.pageobjects.OrderPage;
import Academy.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitTest extends BaseTest {
	String productName = "ZARA COAT 3"; // global declaration

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {

//	public void submitOrder(String email, String password, String productName) throws IOException   {

		// String productName = "ZARA COAT 3";
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
//		LandingPage landingPage = new LandingPage(driver); //Moving it to BaseTest as hitting the url will be common for all tc
//		landingPage.goTo();
//	    landingPage.loginApplication("anurodhmore18@gmail.com", "Anurodh18$"); 

// used BeforeMethod in BaseTest		LandingPage landingPage = launchApplication(); // using the method of initialization using landingPage object 

		// ProductCatalogue productCatalogue =
		// landingPage.loginApplication("anurodhmore18@gmail.com", "Anurodh18$");
//		 ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		// ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		// productCatalogue.getProductByName(productName);
		// productCatalogue.addProductToCart(productName);

		productCatalogue.addProductToCart(input.get("product")); // for hashmap

		// productCatalogue.goToCartPage(); // as per inheritance chld classes have
		// access to Parent Class, that why do defining separate object for abstarct
		// class

		CartPage cartPage = productCatalogue.goToCartPage();
		// CartPage cartPage = new CartPage(driver);
		// Boolean match = cartPage.verifyProductDisplay(productName);
		Boolean match = cartPage.verifyProductDisplay(input.get("product")); // for hashmap

		Assert.assertTrue(match); // validations cannot go in page object files. they will be here only
		CheckoutPage checkoutEle = cartPage.goToCheckout();
		checkoutEle.selectCountry("india");
		// ConfirmationPage confirmationPage = checkoutEle.submitOrder();
		// String confirmMessage = confirmationPage.getConfirmationMessage();
		// Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
//	//	driver.close();  Added afterMethod in BaseTest

	}

	// TO verify ZARA COAT 3 is displaying in the Orders Page

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTests() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("anurodhmore18@gmail.com", "Anurodh18$");

		OrderPage orderpage = productCatalogue.goToOrderPage();
		/// orderpage.verifyOrderDisplay(productName);
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
	}

	@DataProvider // this method will give the data for particular class
	public Object[][] getData() throws IOException { // creating 2 dimensional array which accepts multiple set so that
														// data will run on two different sets

		// HashMap<Object,Object> map = new HashMap<Object,Object>(); // hashmap can be
		// used as data provider

		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "anurodhmore18@gmail.com");
//		map.put("password", "Anurodh18$") ;
//		map.put("product", "ZARA COAT 3");
//		
//		
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "testuser18@gmail.com");
//		map1.put("password", "Anurodh18$") ;
//		map1.put("product", "ADIDAS ORIGINAL");
		List<HashMap<String, String>> data = getJsonDataToHashMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\Academy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
		// return new Object[][] {{map},{map1}};

		// @DataProvider // this method will give the data for particular class
		// public Object[][] getData()
		// {
		// return new Object[][] {{"anurodhmore18@gmail.com","Anurodh18$","ZARA COAT
		// 3"},{"testuser18@gmail.com","Anurodh18$","ADIDAS ORIGINAL"}}; // object is a
		// parent data type, it can cover int,float,string,char,etc
		// }

		// we can also data-driver the above mentioned data through external source

	}
}
