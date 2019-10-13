package Muthu.Framework;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class initialVariables {
	
	protected static Properties property;
	static Workbook Wb;
	static Sheet sheet;
	static Row row;
	static Cell cell_Val;
	
	static Workbook WbTestData;
	static Sheet sheetTestData;
	static Row rowTestKey;
	static Row rowTestVal;
	static Cell cell_ValTestDatakey;
	static Cell cell_ValTestDataVal;
	static String TCname;
	
	protected static BufferedReader reader;
	protected static WebDriver driver;
	protected WebDriverWait waitdriver;
	
	protected HashMap<String,String> hashMapData;
	protected static int GCounter;
	protected static long StartTime;
	protected static String tcName; 
}
