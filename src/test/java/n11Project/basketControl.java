package n11Project;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class basketControl {
	
		WebDriver driver = null;
	
  @Test
  public void f() throws MalformedURLException {
	  
		
		 // Define Chrome Browser 
		//	System.setProperty("webdriver.chrome.driver","C:\\chrome\\chromedriver_win32\\chromedriver.exe"); 
	    //  WebDriver driver = new ChromeDriver();
	  	
	  	//Define Remote Chrome Web Driver
	  	String nodeURL = "http://192.168.1.3:4444/wd/hub";
	  	System.setProperty("webdriver.chrome.driver", "C:\\chrome\\chromedriver_win32\\chromedriver.exe");
	  	DesiredCapabilities capability = DesiredCapabilities.chrome();
	  	capability.setBrowserName("chrome");
	  	capability.setPlatform(Platform.WIN10);
	  	driver = new RemoteWebDriver(new URL(nodeURL), capability);

		// Maximize the window and open the link
		driver.manage().window().maximize();
		driver.get("https://www.n11.com/");

		// Manage time outs
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		// Check the link
		if (driver.getCurrentUrl().contains("https://www.n11.com/")) {
			System.out.println("n11.com sayfasina giris yapildi.");
			System.out.println("-----------------------------");
		} else {
			throw new WebDriverException("n11.com sayfasi acilamadi.");
		}
		
		//Close the pop-up
		driver.findElement(By.className("seg-popup-close")).click();
		
		//Search Data
		driver.findElement(By.id("searchData")).sendKeys("Bluetooth speaker");
		
		//Click on the "Search" button
		driver.findElement(By.cssSelector(".iconSearch")).click();
		
		//Click on the "Listeyi Uzat" button for more brands
		driver.findElement(By.xpath("html//div[@id='contentListing']//div[@class='filterArea']/section[4]/span[@class='filterItem moreBtn']")).click();
		
		//Select "Bose" item in the brand list
		driver.findElement(By.cssSelector("input#brand-m-Bose")).click();
		
		//Check that correct option is selected
		if (driver.getCurrentUrl().contains("Bluetooth+speaker&m=Bose")) {
			System.out.println("Bose marka bluetooth speaker icin arama yapildi.");
			System.out.println("-----------------------------");

		} else {
			System.out.println("Bose marka bluetooth speaker icin arama yapilamadi.");
		}
		
		//Open the third product detail page
		driver.findElement(By.cssSelector("li:nth-of-type(3) > .columnContent .productName")).click();
		
		//Add 2 product to basket
		driver.findElement(By.cssSelector(".unf-p-lBox .spinnerArrow.spinnerUp")).click();
		driver.findElement(By.cssSelector(".proDetailArea.unf-p-left a[title='Sepete Ekle']")).click();
		System.out.println("1 adet urun sepete eklenmistir.");
		System.out.println("-----------------------------");
		
		//Open the "Sepetim" page
		driver.findElement(By.cssSelector("a[title='Sepete Git']")).click();
		System.out.println("Sepetim sayfasina gecis yapilmistir.");
		System.out.println("-----------------------------");
		
		//Check that the number of products is 2 in the basket
		WebElement productNumberInBasket = driver.findElement(By.cssSelector(".box.box--small.orderSummaryBox  b"));
		String productNumberBasketPage = productNumberInBasket.getText();
		System.out.println("Sepette " + productNumberBasketPage + "bulunmaktadir.");
		System.out.println("-----------------------------");
		
		//Close the window
		driver.quit();
  }
}

