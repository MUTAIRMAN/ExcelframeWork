package Muthu.Framework;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;

public class FrameworkClass extends initialVariables {

	public static void ReportFolderCreator() {

		// creating reports folder if not exists : As for every Run of this class it
		// will Create the report
		String projectPath = System.getProperty("user.dir");
		projectPath = projectPath + "\\Reports";
		File folder = new File(projectPath);
		if (!folder.exists())
			folder.mkdir();
	}

	/****
	 * 
	 * Method will handle excel(.xlsx or .xls)
	 */
	public static void ExcelHandler() throws IOException {
		FrameworkClass.ReportFolderCreator();
		reader = new BufferedReader(
				new FileReader("C:\\Users\\Muthukumar\\eclipse-workspace\\Framework\\FrameWorkProperties.properties"));
		property = new Properties();
		property.load(reader);
		String fpath = property.getProperty("ORPath");
		File file = new File(fpath);
		FileInputStream inputStream = new FileInputStream(file);

		Wb = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName = fpath.substring(fpath.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			Wb = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of HSSFWorkbook class

			Wb = new HSSFWorkbook(inputStream);

		}

		String fdpath = property.getProperty("testData");
		File file_data = new File(fdpath);
		FileInputStream inputStream_data = new FileInputStream(file_data);

		WbTestData = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName_data = fdpath.substring(fdpath.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName_data.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			WbTestData = new XSSFWorkbook(inputStream_data);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName_data.equals(".xls")) {

			// If it is xls file then create object of HSSFWorkbook class

			WbTestData = new HSSFWorkbook(inputStream_data);

		}

	}

	/****
	 * 
	 * @param obj
	 * @param sheetName
	 * @return obj
	 */

	public static By getObj(String obj, String sheetName) {
		String objValue = null;
		sheet = Wb.getSheet(sheetName);
		int cCount = 0;
		By ByEle = null;
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if (row.getCell(0).toString().equals(obj)) {
				for (int j = 1; j <= row.getLastCellNum(); j++) {
					cell_Val = row.getCell(j);
					if (cell_Val != null && cell_Val.getCellType() == Cell.CELL_TYPE_STRING) {
						objValue = cell_Val.toString();
						cCount = j;
						break;
						//
					}
				}
				break;
			}
		}
		
		
		// code to choose the By

		switch (cCount) {

		case 1:
			FrameworkClass.highlight(ByEle.id(objValue));
			return ByEle.id(objValue);
		case 2:
			FrameworkClass.highlight(ByEle.name(objValue));
			return ByEle.name(objValue);
		case 3:
			FrameworkClass.highlight(ByEle.xpath(objValue));

			return ByEle.xpath(objValue);
		case 4:
			FrameworkClass.highlight(ByEle.linkText(objValue));

			return ByEle.linkText(objValue);
		case 5:
			FrameworkClass.highlight(ByEle.partialLinkText(objValue));

			return ByEle.partialLinkText(objValue);
		case 6:
			FrameworkClass.highlight(ByEle.tagName(objValue));

			return ByEle.tagName(objValue);
		case 7:
			FrameworkClass.highlight(ByEle.className(objValue));

			return ByEle.className(objValue);
		default:
			return null;
		}

	}

	/***
	 * 
	 * @param tcName
	 * @return testData as HashMap
	 */
	public static HashMap testDatahandler(String tcName) {

		HashMap<String, String> hm = new HashMap<String, String>();

		sheetTestData = WbTestData.getSheetAt(0);

		int rowDatano = 0;
		for (int k = 0; k <= sheetTestData.getLastRowNum(); k++) {
			rowTestKey = sheetTestData.getRow(k);

			if (rowTestKey.getCell(0).toString().trim().equals(tcName)) {
				rowDatano = k;
				break;
			}
		}

		rowTestKey = sheetTestData.getRow(0);
		rowTestVal = sheetTestData.getRow(rowDatano);

		for (int j = 0; j < rowTestKey.getLastCellNum(); j++) {

			cell_ValTestDatakey = rowTestKey.getCell(j);
			cell_ValTestDataVal = rowTestVal.getCell(j, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);
			hm.put(cell_ValTestDatakey.toString().trim(), cell_ValTestDataVal.toString().trim());

		}

		return hm;

	}

	/**
	 * 
	 * @return Testcase Name by using the @test which is invoked
	 * @throws AWTException 
	 * @throws InterruptedException 
	 */
	public static String TCName() throws AWTException, InterruptedException {
		TCname = Thread.currentThread().getStackTrace()[2].getMethodName().toString();
		 SystemTray tray = SystemTray.getSystemTray();

	        //If the icon is a file
	        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
	        //Alternative (if the icon is on the classpath):
	        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

	        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
	        //Let the system resize the image if needed
	        trayIcon.setImageAutoSize(true);
	        //Set tooltip text for the tray icon
	        trayIcon.setToolTip("System tray icon demo");
	        tray.add(trayIcon);

	        trayIcon.displayMessage(TCname, "TestCaseName", MessageType.INFO);
	        Thread.sleep(2000);
	        tray.remove(trayIcon);   

		return TCname;
	}

	public static void ValidationScreenshot() throws IOException {
		String strPath = System.getProperty("user.dir");

		strPath = strPath + "\\Screenshots";
		File fpath = new File(strPath);

		if (fpath.exists())

		{
			strPath = strPath + "\\" + TCname;

			if (new File(strPath).exists()) {
				File fpath_sub = new File(strPath);
				for (File file : fpath_sub.listFiles()) {
					if (!file.isDirectory())
						file.delete();
				}
				File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File DestFile = new File(fpath_sub + "\\" + System.currentTimeMillis());

				FileHandler.copy(screenshotFile, DestFile);
			} else {
				new File(strPath).mkdir();
				File fpath_sub = new File(strPath);
				File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File DestFile = new File(fpath_sub + "\\" + System.currentTimeMillis());
				FileHandler.copy(screenshotFile, DestFile);
			}

		}

		else {
			fpath.mkdir();
			strPath = strPath + "\\" + TCname;
			new File(strPath).mkdir();
			File fpath_sub = new File(strPath);
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File DestFile = new File(fpath_sub + "\\" + System.currentTimeMillis());
			FileHandler.copy(screenshotFile, DestFile);

		}

	}

	public static void createHtml(int s3, String message) throws IOException {
		String flg;
		if (s3 == 0)
			flg = "Failed";
		else
			flg = "Passed";

		String s1 = Integer.toString(++GCounter);
		String projectPath = System.getProperty("user.dir");
		String fileName = "Muthu_" + StartTime + ".html";

		String tempFile = projectPath + File.separator + "Reports" + File.separator + fileName;
		File file = new File(tempFile);

		if (file.exists()) {
			Document doc = Jsoup.parse(file, "utf-8");
			doc.head().appendElement("title").text("Selenium Test");
			doc.head().appendElement("style")
					.text("table {\r\n" + "  font-family: arial, sans-serif;\r\n" + "  border-collapse: collapse;\r\n"
							+ "  width: 100%;\r\n" + "}\r\n" + "\r\n" + "td, th {\r\n"
							+ "  border: 1px solid #dddddd;\r\n" + "  text-align: left;\r\n" + "  padding: 8px;\r\n"
							+ "}\r\n" + "\r\n" + "th {\r\n" + "  background-color: #dddddd;\r\n" + "}");
			Element table = doc.body().getElementById("muthu");
			table.appendElement("tr").appendElement("td").text(s1).appendElement("td").text(tcName).appendElement("td")
					.text(flg).appendElement("td").text(message);

			OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
			Writer writer = new OutputStreamWriter(outputStream);
			writer.write("");
			writer.write(doc.toString());
			writer.close();

		}

		else {
			try {

				Document doc = Jsoup.parse("<html></html>");
				doc.head().appendElement("title").text("Selenium Test");
				doc.head().appendElement("style").text("table {\r\n" + "  font-family: arial, sans-serif;\r\n"
						+ "  border-collapse: collapse;\r\n" + "  width: 100%;\r\n" + "}\r\n" + "\r\n" + "td, th {\r\n"
						+ "  border: 1px solid #dddddd;\r\n" + "  text-align: left;\r\n" + "  padding: 8px;\r\n"
						+ "}\r\n" + "\r\n" + "th {\r\n" + "  background-color: #dddddd;\r\n" + "}");
				doc.body().appendElement("h1").attr("align", "center").appendElement("font").attr("face", "verdana")
						.attr("color", "green").text("Test Report");
				Element table = doc.body().appendElement("table").attr("border", "1").attr("bordercolor", "#000000")
						.attr("id", "muthu").attr("bgcolor", "#F5A9BC");
				table.appendElement("tr").appendElement("th").text("TestId").appendElement("th").text("TestName")
						.appendElement("th").text("TestResult").appendElement("th").text("Message");
				// table.appendElement("tr").appendElement("td").text(s1).appendElement("tr").appendElement("th").text("TestName").appendElement("tr").appendElement("th").text("TestResult");
				table.appendElement("tr").appendElement("td").text(s1).appendElement("td").text(tcName)
						.appendElement("td").text(flg).appendElement("td").text(message);

				OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
				Writer writer = new OutputStreamWriter(outputStream);
				writer.write(doc.toString());
				writer.close();
			} catch (IOException e) {
				globalFlag=false;

				e.printStackTrace();
			}

		}
	}
	
	public static void highlight(By Element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver; 
		 
		js.executeScript("arguments[0].setAttribute('style', 'border: thick solid #000000');", driver.findElement(Element));
		 
		try 
		{
		Thread.sleep(250);
		} 
		catch (InterruptedException e) {
			globalFlag=false;
		System.out.println(e.getMessage());
		
		} 
		 
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", driver.findElement(Element)); 
	}

}