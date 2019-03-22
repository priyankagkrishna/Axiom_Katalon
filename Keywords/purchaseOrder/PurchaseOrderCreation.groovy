package purchaseOrder

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
import com.kms.katalon.core.testdata.InternalData
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.server.commandhandler.UploadFile
import org.stringtemplate.v4.compiler.STParser.ifstat_return
import org.openqa.selenium.WebDriver
import org.junit.After
import org.openqa.selenium.By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import org.openqa.selenium.Keys as Keys
import groovy.ui.text.TextEditor.TabAction as TabAction


import internal.GlobalVariable

public class PurchaseOrderCreation {

	common.CommonKeywords commonkeywords = new common.CommonKeywords()

	@Keyword
	def navigateToPurchaseOrder(){
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//div[contains(@class,'trading')]"]))
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'):  "//a[text()='Purchasing']"]))
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[text()='Purchase Order']"]))
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[text()='Entry']"]))
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_ADD']))
		commonkeywords.imageloadingIcon()
	}

	@Keyword
	def purchaseOrderGeneralinfo(){

		InternalData purchasedata = findTestData('PurchaseOrderGeneralInfo')
		String VendorName= purchasedata.internallyGetValue("Vendor", 0)
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_VENDOR']), VendorName)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//ul[@id='PO_PO_VENDOR_listbox']/li[1]"]))
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Common/input_with_value',[('val'): 'Open New PO']))
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'PO_PO_HEDR_SHIPVIAID']), '121' , false)
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'PO_PO_HEDR_SHIPTERMSID']), '868' , false)
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'PO_PO_HEDR_FRTPAYTERMS']), 'D' , false)
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'PO_PO_HEDR_PAYTERMSID']), '2', false)
		WebUI.click(findTestObject('Object Repository/Common/input_with_id_and_index',[('id'): 'PO_PO_ADD',('index'): '2']))
	}

	@Keyword
	def ItemAdditioninPurchaseOrder(){

		InternalData purchasedata = findTestData('PurchaseOrderGeneralInfo')
		String item= purchasedata.internallyGetValue("Item_No", 0)
		String quantity= purchasedata.internallyGetValue("Quantity_In", 0)
		String unitcost= purchasedata.internallyGetValue("Unit_Cost_In", 0)

		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_ITEMNO']), item)
		WebUI.delay(1)
		WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_ITEMNO']), Keys.chord(Keys.TAB))
		//WebUI.sendKeys(findTestObject('Object Repository/Common/textarea_with_id',[('id'): 'PO_PO_DETL_ITEMDESCRIPTION']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
		WebUI.delay(2)
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_SCH_ORDQTY']), quantity)
		WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_SCH_ORDQTY']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
		WebUI.delay(4)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "(//input[contains(@class,' unit_cost ')])[1]"]))
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_POCOST']), unitcost)
		WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_POCOST']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
		WebUI.delay(2)
		//WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//div[@id='order_line_add']//input[@id='SM_MA_CUST_SAVEANDADD']"]))
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id') : '_SAVE']))
		commonkeywords.imageloadingIcon()
		getPurchaseOrderNumber()
		saveitem()
		issueingPurchaseOrder()
	}

	@Keyword
	def nonInventoryitem(){

		InternalData purchasedata = findTestData('PurchaseOrderGeneralInfo')
		String itemnoninventoy= purchasedata.internallyGetValue("Item_NonIn", 0)
		String quantitynoninventory= purchasedata.internallyGetValue("Quantity_NonIn", 0)
		String unitcostnoninventory= purchasedata.internallyGetValue("Unit_Cost_NonIn", 0)

		commonkeywords.imageloadingIcon()
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid') : 'PO_PO_DETL_LINETYPE']), 'N', false)
		commonkeywords.imageloadingIcon()
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_NONINVENDORYVENDITEMNO']), itemnoninventoy)
		WebUI.delay(1)
		WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_NONINVENDORYVENDITEMNO']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
		WebUI.delay(1)
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_SCH_ORDQTY']), quantitynoninventory)
		WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_SCH_ORDQTY']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
		WebUI.delay(5)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "(//input[contains(@class,' unit_cost ')])[1]"]))
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_POCOST']), unitcostnoninventory)
		//WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_POCOST']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id') : '_SAVE']))
	}

	@Keyword
	def saveitem(){
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_MODIFY']))
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "(//div[@id='confirm_popup_window']/div/input[contains(@class,'buttonok')])[1]"]))
		commonkeywords.imageloadingIcon()
		WebUI.delay(2)
	}

	@Keyword
	def lineDetailsPage(){


		InternalData purchasedata = findTestData('PurchaseOrderGeneralInfo')
		String itemlinedetails= purchasedata.internallyGetValue("Item_LineDeatils", 0)
		String quantitylinedetails= purchasedata.internallyGetValue("Quantity_Line", 0)

		commonkeywords.imageloadingIcon()
		try{
			WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[text()='Line Detail']"]))
			commonkeywords.imageloadingIcon()
		}
		catch(Exception e){
			WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[text()='Line Detail']"]))
			commonkeywords.imageloadingIcon()
		}

		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath') : "//div[@class='LineDetailControls']//input[@id='PO_PO_ADD']"]))
		WebUI.delay(5)
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_ITEMNO']))
		commonkeywords.imageloadingIcon()
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_ITEMNO']), itemlinedetails)
		WebUI.delay(2)
		WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_ITEMNO']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/textarea_with_id',[('id'): 'PO_PO_DETL_ITEMDESCRIPTION']))
		WebUI.sendKeys(findTestObject('Object Repository/Common/textarea_with_id',[('id'): 'PO_PO_DETL_ITEMDESCRIPTION']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
		WebUI.delay(2)
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_SCH_ORDQTY']), quantitylinedetails)
		WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_SCH_ORDQTY']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
	}

	@Keyword
	def postReciptOperation(){

		WebUI.click(findTestObject('Object Repository/Common/input_with_class',[('class'): 'left button_icon icon-add  operation_add   basetooltip']))
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'PO_PO_DETL_OPERATIONCATEGORY']), '2', false)
		commonkeywords.imageloadingIcon()
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'PO_PO_DETL_OPERATION']), '122', false)
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//input[contains(@class,'operation_save_add')]"]))
		commonkeywords.imageloadingIcon()
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'PO_PO_DETL_OPERATIONCATEGORY']), '9', false)
		commonkeywords.imageloadingIcon()
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'PO_PO_DETL_OPERATION']), '128', false)
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath') : "//input[contains(@class,'operation_save ')]"]))
	}

	@Keyword
	def additionalInspection(){

		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath') : "//input[contains(@class,'inspection_add')]"]))
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'PO_PO_DETL_QC_QCINSPECTION']), '13', false)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath') : "//input[contains(@class,'inspection_save_add ')]"]))
		commonkeywords.imageloadingIcon()
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'PO_PO_DETL_QC_QCINSPECTION']), '54', false)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath') : "//input[contains(@class,'inspection_save ')]"]))
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_MODIFY']))
	}

	@Keyword
	def otherChargespage(){

		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath') : "//a[text()='Other Charges']"]))
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath') : "//input[contains(@class,' icon-add  action_button_othercharge')]"]))
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'PO_PO_OC_DESCRIPTION']), '6', false)
		//WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'PO_PO_OC_RELATEDTOLINENO']), '1593', false)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath') : "(//input[contains(@class,'k-formatted-value ')])[3]"]))
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id') : 'PO_PO_OC_EXTCOST']), '10')
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath') : "//input[contains(@class,'saveandadd_othercharge')]"]))
		commonkeywords.imageloadingIcon()
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'PO_PO_OC_DESCRIPTION']), '6', false)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath') : "(//input[contains(@class,'k-formatted-value ')])[3]"]))
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id') : 'PO_PO_OC_EXTCOST']), '15')
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath') : "//input[contains(@class,'save_othercharge')]"]))
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_MODIFY']))
	}

	@Keyword
	def commentspage(){


		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath') : "//a[text()='Comments']"]))
		commonkeywords.imageloadingIcon()
		WebUI.setText(findTestObject('Object Repository/Common/textarea_with_id',[('id'): 'PO_PO_HEDR_VENDCOMMENTS']), "Vendor")
		WebUI.setText(findTestObject('Object Repository/Common/textarea_with_id',[('id'): 'PO_PO_HEDR_WHSECOMMENTS']), "Warehouse")
		WebUI.setText(findTestObject('Object Repository/Common/textarea_with_id',[('id'): 'PO_PO_HEDR_INTERNALCOMMENTS']), "InternalComments")
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_MODIFY']))
	}


	@Keyword
	def issueingPurchaseOrder(){

		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_FAX']))
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'common_popup_button_ok']))
		commonkeywords.imageloadingIcon()
		String  statusXpath = "//label[@id='LBL_PO_PO_HEDR_STATUS']/../following-sibling::li/label[contains(@class,'txt disabled')]"
		String status = WebUI.getText(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'):statusXpath]))
		WebUI.comment(status)

		if(status.equalsIgnoreCase("Issued")){
			WebUI.comment("status is -  Issued")
		}
	}



	@Keyword
	def getPurchaseOrderNumber(){

		String PONumber=WebUI.getAttribute(findTestObject('Object Repository/Common/input_with_id',[('id'):'PO_PO_HEDR_PONO']), 'value')
		//String PONumber=WebUI.getText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_HEDR_PONO']))
		GlobalVariable.poNum = PONumber
		println PONumber
	
		
		
	}
}


