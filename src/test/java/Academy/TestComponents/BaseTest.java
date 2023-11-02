package Academy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Academy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver; // Globally declaring
	public LandingPage landingPage; // Globally declaring
	public WebDriver initializeDriver() throws IOException {

		// properties class

		Properties prop = new Properties();
		// you cannot directly send the properties file to base test. It should be sent
		// to FileInputStream

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Academy\\resources\\GlobalData.properties");
		prop.load(fis);
		
		
//---------------------------------------------		
		//using ternerary operator if in runtime we want to change the browser
		String browserName = System.getProperty("browser") != null ?  System.getProperty("browser") : prop.getProperty("browser");
		
	//	 prop.getProperty("browser");
		//--------------------------------------------
		if (browserName.equalsIgnoreCase("Chrome")) // if browser is chrome it will initialize chrome, if edge then edge
//if (browserName.contains("Chrome"))	 if we are using headless browser		
		{
		//	ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
//			if(browserName.contains("headless"))
//			{
//			options.addArguments("headless"); // running tests is headless mode
//			}
//			
			driver = new ChromeDriver();
		//	driver.manage().window().setSize(new Dimension(1440,900)); // used for full screen mode

		}

		else if (browserName.equalsIgnoreCase("Firefox")) {
			// firefox browser initialization
		} else if (browserName.equalsIgnoreCase("Edge")) {
			// edge browser initialization
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver; // since after it has performed everything mentioned it should be sent further
	}
	
	//initialzing here so that it is accessible everywhere
	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException
	{
		//reading json to string
	String jsonContent =	FileUtils.readFileToString(new File(filePath));

	//String to HashMap - Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
		
	
	 List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
	 });	
	 
	 return data;
	 
	//{map, map1}
	}
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png");
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png";
	}
	
	
	
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		
//this should be created on top so that it is accessible LandingPage landingPage = new LandingPage(driver);  //hitting the url so it is common for every tc
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;                     //return because to use the object in main tc, Submittest
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	

}
