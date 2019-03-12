package finance

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

import org.openqa.selenium.Keys as Keys
import groovy.ui.text.TextEditor.TabAction as TabAction

public class FinanceInvoiceGeneration {

	common.CommonKeywords commonkeywords = new common.CommonKeywords()
	common.ExcelOperations operations=new common.ExcelOperations()
	common.ValidationKeywords validationKeywords = new common.ValidationKeywords()
	//Navigate to Invoice

	@Keyword
	def naviagteToInvoice(){

		WebUI.comment("-------Invoice Generation-------")

		commonkeywords.imageloadingIcon()
		WebUI.waitForPageLoad(50, FailureHandling.STOP_ON_FAILURE)
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Finance']))
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Accounts Receivable ']))
		int currentTab = WebUI.getWindowIndex()
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Invoices']))
		//WebUI.switchToWindowIndex(currentTab + 1)
		commonkeywords.imageloadingIcon()
		WebUI.delay(1)
		validationKeywords.validateAndUpdateExcelbyVerifyElement("//h2[text()='Invoices']", "Navigated to Finance->Invoice")
		/*try {
		 WebUI.verifyElementPresent(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//h2[text()='Invoices']"]), 10)
		 operations.updateTestResultsToExcel( "SUCCESFUL:Navigated to Finance->Invoice","Pass")
		 } catch (Exception e) {
		 operations.updateTestResultsToExcel("FAILED:Finance-Invoice Page Not Found","Fail")
		 }*/
		WebUI.waitForElementVisible(findTestObject('Finance/Common/wait_for_firstrow'), 10)
		WebUI.click(findTestObject('Finance/OrderNoFiltering'))
		commonkeywords.imageloadingIcon()
		String refNum = GlobalVariable.orderNum
		WebUI.waitForElementClickable(findTestObject('Finance/input_isequalto', [('index') : 1]), 10)
		WebUI.delay(2)
		WebUI.click(findTestObject('Finance/input_isequalto', [('index') : 1]))
		WebUI.delay(2)
		WebUI.setText(findTestObject('Finance/input_isequalto', [('index') : 2]), refNum)
		WebUI.delay(2)
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Filter']))
		validateFinanceVerifyProcess(refNum)


	}


	def validateFinanceVerifyProcess(String refNum){
		commonkeywords.imageloadingIcon()
		String validate = WebUI.getText(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[@class=' icon order_no']/span"]))
		validationKeywords.validateAndUpdateExcelbyTextMatch(refNum, validate, "Item present in Finance->Verify")
		WebUI.click(findTestObject('Common/input_with_class', [('class') : 'WH_SHIP_CHECKBOX_ALL']))
		WebUI.click(findTestObject('Common/input_with_id', [('id') : 'AR_INV_QUICKVERIFY']))
		WebUI.click(findTestObject('Finance/click_yes'))
	}
	//Finance Invoice Generation

	@Keyword
	def invoiceGenerate(){

		String refNum = GlobalVariable.orderNum
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Common/select_with_id', [('sid') : 'WH_SHIP_SHP_HEDR_OPERATION']))
		WebUI.click(findTestObject('Finance/Common/select_operation_with_index', [('index') : '2']))
		WebUI.waitForElementVisible(findTestObject('Finance/Common/wait_for_firstrow'), 10)
		WebUI.delay(3)
		WebUI.click(findTestObject('Finance/Finance_Generate/OrderNumberFiltering_Generate'))
		WebUI.delay(3)
		int inputFieldClickCount = commonkeywords.getTotalCountofElement("//span[contains(@class,\'k-numeric-wrap k-state-default\')]/input[@style=\'display: block;\']")
		WebUI.click(findTestObject('Finance/Finance_Generate/input_filterFieldClick'))
		WebUI.delay(2)
		int inputSetTextFieldCount = commonkeywords.getTotalCountofElement("//span[contains(@class,\'k-numeric-wrap k-state-default\')]/input[contains(@style,\'display: inline-block;\')]")
		WebUI.sendKeys(findTestObject('Finance/Finance_Generate/input_filterSetTextField'), refNum)
		WebUI.delay(2)
		WebUI.click(findTestObject('Finance/Finance_Generate/click_FieldFilter'))
		commonkeywords.imageloadingIcon()
		String validate = WebUI.getText(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[@class=' icon order_no']/span"]))
		validationKeywords.validateAndUpdateExcelbyTextMatch(refNum, validate, "Item present in Finance->Generate")
		WebUI.click(findTestObject('Common/input_with_class', [('class') : 'WH_SHIP_CHECKBOX_ALL']))
		WebUI.click(findTestObject('Common/input_with_id', [('id') : 'AR_INV_GENERATE']))
		WebUI.setText(findTestObject('Object Repository/Common/textarea_with_id', [('id') : 'WH_SHIP_SHP_HEDR_DEFAULTINVOICECOMMENTS']),
		'Comment')
		WebUI.click(findTestObject('Common/input_with_id', [('id') : 'GenerateInvoices']))
	}

	@Keyword
	def invoiceInquire(){


		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Finance/Common/select_operation_with_index', [('index') : '4']))
		commonkeywords.imageloadingIcon()
		WebUI.waitForElementVisible(findTestObject('Finance/Common/wait_for_firstrow'), 10)
		WebUI.delay(3)
		String refNum = GlobalVariable.orderNum
		WebUI.delay(3)
		WebUI.click(findTestObject('Common/input_with_id', [('id') : 'WH_SHIP_SHP_HEDR_SEARCH']))
		WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'WH_SHIP_SHP_HEDR_SEARCH']), refNum)
		WebUI.sendKeys(findTestObject('Common/input_with_id', [('id') : 'WH_SHIP_SHP_HEDR_SEARCH']), Keys.chord(Keys.ENTER))
		commonkeywords.imageloadingIcon()
		WebUI.delay(3)
		WebUI.waitForElementPresent(findTestObject('Finance/Finance_Inquiry/select_Inquiry'), 12)
		WebUI.click(findTestObject('Finance/Finance_Inquiry/select_Inquiry'))
		String shipstatus = WebUI.getText(findTestObject('ReviewandPull/ship_status'))
		validationKeywords.validateAndUpdateExcelbyTextMatch(shipstatus, "Delivered", "Item Delivered")


	}
}
