package com.crm.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.base.Base;

public class Util extends Base {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	public static String Excel_Path = "C:\\Users\\Gopi Srinivas Putta\\eclipse-workspace\\Maven\\src\\main\\java\\com\\crm\\testdata\\Data.xlsx";
	static Workbook book;
	static Sheet sheet;

	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}

	public Object[][] getExceldata(String sheetName) throws EncryptedDocumentException, IOException {

		//initiating excel file
		File file = new File(Excel_Path);
		book = WorkbookFactory.create(file);
		sheet = book.getSheet(sheetName);

		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		//getting data from each cell
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();

			}
		}
		return data;
	}
	
public static void takesScreenShotAtEndOfTest() {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		try {
			FileUtils.copyFile(srcFile, new File(currentDir+"\\screenshots\\"+System.currentTimeMillis() + ".png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}
	

}
