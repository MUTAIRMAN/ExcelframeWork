package Muthu.Test;

import Muthu.Framework.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

public class GmailFunctionality extends FrameworkClass {
	@Test
	public void Login() throws InterruptedException, IOException {
		try {
			tcName=FrameworkClass.TCName();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitdriver.until(ExpectedConditions.presenceOfElementLocated(FrameworkClass.getObj("objUserName", "Login")));
		hashMapData = FrameworkClass.testDatahandler(tcName);
		
		driver.findElement(FrameworkClass.getObj("objU1serName", "Login")).sendKeys(hashMapData.get("Name"));

		driver.findElement(FrameworkClass.getObj("objNext", "Login")).click();

		waitdriver.until(ExpectedConditions.elementToBeClickable(FrameworkClass.getObj("objPassword", "Login")));

		driver.findElement(FrameworkClass.getObj("objPassword", "Login")).sendKeys(hashMapData.get("Pwd"));
		driver.findElement(FrameworkClass.getObj("objNext", "Login")).click();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		FrameworkClass.ValidationScreenshot();
		
		FrameworkClass.createHtml(1,"Nil");
		}
		catch(Exception e){
			
			FrameworkClass.createHtml(0,e.toString());
		
		}
	}



	@Test(dependsOnMethods="Login")
	public void PostLogin() throws InterruptedException, IOException {
	
		try {
			tcName=FrameworkClass.TCName();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		waitdriver.until(ExpectedConditions.presenceOfElementLocated(FrameworkClass.getObj("objGmailImage", "validate")));
		hashMapData = FrameworkClass.testDatahandler(tcName);
		driver.findElement(FrameworkClass.getObj("objPlus", "validate")).click();
		waitdriver.until(ExpectedConditions.presenceOfElementLocated(FrameworkClass.getObj("objTo", "validate")));
		driver.findElement(FrameworkClass.getObj("objTo", "validate")).sendKeys(hashMapData.get("Name"));
		driver.findElement(FrameworkClass.getObj("objSub", "validate")).sendKeys(hashMapData.get("Subject"));
		FrameworkClass.ValidationScreenshot();
		FrameworkClass.createHtml(1,"Nil");
	}
	catch(Exception e){
		FrameworkClass.createHtml(0,e.toString());
	
	}
	}

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws IOException, InterruptedException {
		
		
		FrameworkClass.ExcelHandler();
		
		StartTime=System.currentTimeMillis();
		
		String strURL = property.getProperty("URL");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Muthukumar\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver",
					"C:\\Users\\Muthukumar\\Downloads\\IEDriverServer_x64_3.13.0\\IEDriverServer.exe");

			driver = new InternetExplorerDriver();
		}
		driver.get(strURL);
		waitdriver = new WebDriverWait(driver, 20);
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
