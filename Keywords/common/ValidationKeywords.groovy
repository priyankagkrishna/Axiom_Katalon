package common

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

import internal.GlobalVariable

public class ValidationKeywords {

	common.ExcelOperations operations = new common.ExcelOperations()


	@Keyword
	def validateAndUpdateExcelbyTextMatch(String valueToCompare, String valueFromApp, String statusMessage){
		try {
			WebUI.verifyMatch(valueToCompare, valueFromApp, false)
			operations.updateTestResultsToExcel( "SUCCESSFUL: "+statusMessage,"Pass")
		} catch (Exception e) {
			operations.updateTestResultsToExcel("FAILED: "+ statusMessage,"Fail")
		}
	}

	@Keyword
	def validateAndUpdateExcelbyVerifyElement(String xpath,String statusMessage) {
		try {
			WebUI.verifyElementPresent(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): xpath]), 10)
			operations.updateTestResultsToExcel( "SUCCESFUL:"+statusMessage,"Pass")
		} catch (Exception e) {
			operations.updateTestResultsToExcel("FAILED: "+statusMessage ,"Fail")
		}
	}



	@Keyword
	def validationForItemAdd(String ItemNum){
		String validate=WebUI.getAttribute(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[@class='griditem handpointer ']"]), 'data-item')
		String status = "Fail"
		if(validate.contains(ItemNum)){
			status = "Pass"
		}
		validateAndUpdateExcelbyTextMatch(status, "Pass", "Item Added Succesfully")
	}
}
