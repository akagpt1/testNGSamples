package testScriptsNG;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


public class LoginTest {
	WebDriver driver;
	Properties prop;
	@Parameters("Browser")
	@BeforeMethod
//	public void setup(String strBrowser) {
//		if (strBrowser.equalsIgnoreCase("Chrome")) {
//			driver=new ChromeDriver();
//		}
//		else if (strBrowser.equalsIgnoreCase("edge")) {
//			driver=new EdgeDriver();	
//		}
//		
//		
//	
//		driver.manage().window().maximize();	
//	}

	public void setup() throws IOException {
		String path = System.getProperty("user.dir") +
		"//src//test//resources//configFiles//config.properties";
		FileInputStream fin = new FileInputStream(path);
		prop = new Properties();
		prop.load(fin);
		String strBrowser = prop.getProperty("browser");
		if (strBrowser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if (strBrowser.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();	
			driver.manage().window().maximize();	
	}		
		
	}
	
	
	
	@Test(dataProvider="loginData")
  public void validLogin(String strUser, String strPwd) {
	  
	  	driver.get(prop.getProperty("url"));
		driver.findElement(By.id("username")).sendKeys(strUser);
		driver.findElement(By.name("password")).sendKeys(strPwd);
		//driver.findElement(By.className("radius")).click();
		WebElement btn = driver.findElement(By.tagName("button"));
		//btn.click();
		boolean isDisp = driver.findElement(By.cssSelector("div.flash.success")).isDisplayed();
		Assert.assertTrue(isDisp);
  }
	
	
//To use excel as Obj Repo

public String 
	
	
	
@DataProvider(name="loginData")
	public Object[][] getData() throws CsvValidationException, IOException{
	String path = System.getProperty("user.dir") +
			"//src//test//resources//datafile//loginPage.csv";
			CSVReader reader = new CSVReader(new FileReader(path));
			String cols[];
			ArrayList<Object> dataList = new ArrayList<Object>();
			while((cols = reader.readNext()) != null) {
				Object record[] = {cols[0],cols[1]};
				dataList.add(record);
				
			}
			
		reader.close();
		return dataList.toArray(new Object[dataList.size()][]);
}
 
@AfterMethod
public void teardown()
{
	driver.close();
}
		
}
