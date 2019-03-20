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
		commonkeywords.imageloadingIcon()
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_SCH_ORDQTY']), quantity)
		WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_SCH_ORDQTY']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
		WebUI.delay(3)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "(//input[contains(@class,' unit_cost ')])[1]"]))
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_POCOST']), unitcost)
		WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_POCOST']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//div[@id='order_line_add']//input[@id='SM_MA_CUST_SAVEANDADD']"]))
		nonInventoryitem()
		saveitem()
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
		WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_NONINVENDORYVENDITEMNO']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_SCH_ORDQTY']), quantitynoninventory)
		WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_SCH_ORDQTY']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
		WebUI.delay(3)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "(//input[contains(@class,' unit_cost ')])[1]"]))
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_POCOST']), unitcostnoninventory)
		WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_DETL_POCOST']), Keys.chord(Keys.TAB))
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id') : '_SAVE']))
		
		
	}
	
	@Keyword
	def saveitem(){
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'PO_PO_MODIFY']))
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "(//div[@id='confirm_popup_window']/div/input[contains(@class,'buttonok')])[1]"]))
	}
}
