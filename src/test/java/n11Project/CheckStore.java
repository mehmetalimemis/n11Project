package n11Project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class CheckStore {

	
	@Test
	public void f() throws IOException {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Stores");
		
		WebDriver driver = null;
		
		int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
	
		 // Define Chrome Browser 
		//	System.setProperty("webdriver.chrome.driver","C:\\chrome\\chromedriver_win32\\chromedriver.exe"); 
	    //  WebDriver driver = new ChromeDriver();
		 
		
	  	//Define Remote Chrome Browser
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
			System.out.println("n11.com sayfasi acilamadi.");
		}

		// Close the pop-up
		driver.findElement(By.className("seg-popup-close")).click();

		// Open the "Mağazaları Gör" page
		driver.findElement(By.cssSelector("span[title='Mağazalar']")).click();
		;
		driver.findElement(By.cssSelector("a[title='Mağazaları Gör']")).click();
		
		//Write store names to Excel 	(not completed)			
		WebElement storeList = driver.findElement(By.cssSelector(".allSellers.tabPanel > .sellerListHolder"));			
		
		for(int i=1;i<=rowCount;i++) {
			sheet.getRow(i).getCell(i).setCellValue(storeList.getText());		
		}
				
		FileOutputStream fout = new FileOutputStream("C:\\Users\\mehmetali.memis\\Desktop\\Stores.xlsx");
		workbook.write(fout);
		workbook.close();
		
		// Write comments number
		driver.findElement(By.cssSelector("a[title='alisverisburada']")).click();
		driver.findElement(By.cssSelector("a[title='Mağaza Yorumları']")).click();
		WebElement commentNumber = driver.findElement(By.cssSelector(".selectedReview"));
		String writeCommentNumber = commentNumber.getText();
		System.out.println("Bu magaza icin " + writeCommentNumber);
		System.out.println("-----------------------------");
		
		//Close the window
		driver.quit();
	}
}
