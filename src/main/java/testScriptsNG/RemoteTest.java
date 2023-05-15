package testScriptsNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class RemoteTest {
	WebDriver driver;
  @Test
  public void test() {
	  ChromeOptions options = new ChromeOptions();
	  options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
	  String strHub = "";
	  driver = new RemoteWebDriver(new URL(strHub), options);
	  
	  
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		searchBox.submit();
		System.out.println("Page Title is ..." + driver.getTitle());
		Asssert.assertEquals(driver.getTitle(),"Java Tutorial - Google Search")
  }
}
