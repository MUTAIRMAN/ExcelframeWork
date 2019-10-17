package Muthu.Framework;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrameWorkUtil extends initialVariables {

	public static void DropDownSelectByValue(WebElement Element, String val) {
		Select sel = new Select(Element);
		sel.selectByValue("");
	}

	public static void DropDownSelectByIndex(WebElement Element, int index) {
		Select sel = new Select(Element);
		sel.selectByIndex(index);
	}

	public static void MoveToElement(WebElement Element) throws AWTException {
		Actions act = new Actions(driver);
		act.moveToElement(Element);
		act.build().perform();

	}

	public static void MoveToElementAndClick(WebElement Element) throws AWTException {
		Actions act = new Actions(driver);
		act.moveToElement(Element);
		act.click();
		act.build().perform();

	}

	public static void CopyStringClipBoard(String s) {
		StringSelection selection = new StringSelection(s);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);

	}

	public static void PasteString() throws AWTException {
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0056')).perform();

	}

	public static void ScrolltoViewElement(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("argument[0].scrollIntoView(true);",element);
	}

	
	
	
	
	public static void main(String a[]) throws AWTException, InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Muthukumar\\Downloads\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://google.com");
		driver.manage().window().maximize();

		Thread.sleep(2000);
		CopyStringClipBoard("muthukumar");
		driver.findElement(By.name("q")).click();
		// PasteString(driver);
		WebElement element = driver
				.findElement(By.xpath("//div[@class=\"FPdoLc VlcLAe\"]/center/input[@class=\"RNmpXc\"]"));
		// element.click();
		Thread.sleep(2000);
		driver.quit();
	}

}
