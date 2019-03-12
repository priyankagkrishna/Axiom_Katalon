package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.configuration.RunConfiguration
import internal.GlobalVariable
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {


	@Keyword
	public void updateTestResultsToExcel(String testCaseName, String testStatus) throws IOException{
		int rowNumber = GlobalVariable.testResultNum+1
		String userDir = System.getProperty("user.dir")
		FileInputStream fis = new FileInputStream(userDir+"/Data Files/TestResult.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row row = sheet.createRow(rowNumber);
		Cell cell0 = row.createCell(0);
		cell0.setCellType(cell0.CELL_TYPE_STRING);
		cell0.setCellValue(rowNumber);
		Cell cell1 = row.createCell(1);
		cell1.setCellType(cell1.CELL_TYPE_STRING);
		cell1.setCellValue(testCaseName);
		Cell cell2 = row.createCell(2);
		cell2.setCellType(cell2.CELL_TYPE_STRING);
		cell2.setCellValue(testStatus);
		FileOutputStream fos = new FileOutputStream(userDir+"/Data Files/TestResult.xlsx");
		workbook.write(fos);
		fos.close();
		GlobalVariable.testResultNum = rowNumber
	}


	@Keyword
	def clearOldFile(){
		FileOutputStream out = null;
		try{
			XSSFWorkbook oldFile = new XSSFWorkbook();
			Sheet sheet = oldFile.createSheet("Sheet1");

			int rowNumber = GlobalVariable.testResultNum
			Row row = sheet.createRow(rowNumber);
			Cell cell0 = row.createCell(0);
			cell0.setCellType(cell0.CELL_TYPE_STRING);
			cell0.setCellValue("SL");
			Cell cell1 = row.createCell(1);
			cell1.setCellType(cell1.CELL_TYPE_STRING);
			cell1.setCellValue("Testcase Name");
			Cell cell2 = row.createCell(2);
			cell2.setCellType(cell2.CELL_TYPE_STRING);
			cell2.setCellValue("Status");


			String userDir = System.getProperty("user.dir")
			out = new FileOutputStream(userDir+"/Data Files/TestResult.xlsx");
			oldFile.write(out);
			out.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}