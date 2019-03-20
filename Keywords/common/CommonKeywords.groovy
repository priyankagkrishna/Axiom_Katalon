package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.InternalData
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import java.awt.Robot
import java.awt.Toolkit
import java.awt.Desktop.Action
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.events.EventFiringWebDriver
import internal.GlobalVariable
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import internal.GlobalVariable

public class CommonKeywords {

	common.ExcelOperations operations=new common.ExcelOperations()
	common.ValidationKeywords validationKeywords = new common.ValidationKeywords()

	//To login to the application
	@Keyword
	def login(String UserName,String Password){
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl('http://10.10.0.105/')
		WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'UserName']), UserName)
		WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'Password']), Password)
		WebUI.click(findTestObject('Common/input_with_class', [('class') : 'log_button']))
		WebUI.waitForElementPresent('Trading/select_trading', 10)
	}

	@Keyword
	def LoginEnvironment(String environment){
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(GetEnvironmentConfig(environment, 'Address'))
		def username = GetEnvironmentConfig(environment, 'Environment_username')
		def password = GetEnvironmentConfig(environment, 'Environment_password')
		Login(username, password)
		WebUI.waitForElementVisible(findTestObject('Trading/select_trading'),10)
		try {
			WebUI.verifyElementPresent(findTestObject('Trading/select_trading'), 10)
			operations.clearOldFile()
			operations.updateTestResultsToExcel( "SUCCESFUL:Logged In To Application ","Pass")
		} catch (Exception e) {
			operations.clearOldFile()
			operations.updateTestResultsToExcel("FAILED: Logged In To Application  ","Fail")
		}
	}



	/*
	 * Pull single value from the Environment data table based on the environment and column name
	 */
	@Keyword
	def GetEnvironmentConfig(String environment, String config){
		InternalData data = findTestData('EnvironmentDetails')
		for (def rowNum = 0; rowNum <data.getRowNumbers(); rowNum++) {
			if (data.internallyGetValue("Environment", rowNum) == environment){
				return data.internallyGetValue(config, rowNum)
			}
		}
	}


	/*
	 * Login to the application using provided credentials
	 */
	@Keyword
	def Login(String userName, String password) {
		WebUI.verifyElementVisible(findTestObject('Common/input_with_id',[('id'):'UserName']))
		WebUI.setText(findTestObject('Common/input_with_id',[('id'):'UserName']), userName)
		WebUI.setText(findTestObject('Common/input_with_id',[('id'):'Password']), password)
		WebUI.click(findTestObject('Object Repository/Common/input_with_class',[('class'):'log_button']))
	}


	@Keyword
	def getTotalCountofElement(String xPath){
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> element = driver.findElements(By.xpath(xPath))
		//println (element.size())
		return element.size()
	}

	@Keyword
	def clickonAllocatedlink(){
		WebDriver driver = DriverFactory.getWebDriver()
		int x=driver.findElement(By.xpath("//a[@title='Allocated']")).getLocation().getX()
		int y=driver.findElement(By.xpath("//a[@title='Allocated']")).getLocation().getY()

		//println (xy)
		Actions builder = new Actions(driver);
		Action mouseOver = builder
				.moveByOffset(x, y)
				.doubleClick()
				.build().perform();

	}
	//Waitforpageload

	@Keyword()
	def imageloadingIcon(){
		WebUI.waitForElementPresent(findTestObject('Object Repository/Common/div_loading_image_with_index', [('index') : '1']),
		10)
		WebUI.waitForElementNotPresent(findTestObject('Object Repository/Common/div_loading_image_with_index', [('index') : '1']),
		25)
	}


	@Keyword
	def setFilterinGrid(String index,String filterText){
		WebUI.click(findTestObject('Common/filter_RefNo'))
		WebUI.delay(2)
		WebUI.waitForElementClickable(findTestObject('ReviewandPull/order_no_isequalto',[('index'): index]), 20)
		WebUI.delay(2)
		WebUI.setText(findTestObject('ReviewandPull/order_no_isequalto',[('index'): index]), filterText)

	}

}
