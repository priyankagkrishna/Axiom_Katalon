package purchaseOrder

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Keys as Keys

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

public class OperationsReceiving {


	common.CommonKeywords commonkeywords = new common.CommonKeywords()
	purchaseOrder.PurchaseOrderCreation pocreation = new purchaseOrder.PurchaseOrderCreation()


	@Keyword
	def navigatetoReceivingPage(){
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[text()='Operations']"]))
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//span[@class='mainmenu_iconwrap icon icon-warehouse']"]))
		int currentTab = WebUI.getWindowIndex()
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[text()='Receiving']"]))
		WebUI.switchToWindowIndex(currentTab+1)
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'WH_REC_ADD']))
		String poNum = GlobalVariable.poNum
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'WH_REC_RCV_LIST_REFERENCENO']))
		WebUI.setText(findTestObject('Object Repository/Common/input_with_id',[('id'): 'WH_REC_RCV_LIST_REFERENCENO']), poNum)
		WebUI.delay(2)
		//WebUI.sendKeys(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): 'WH_REC_RCV_LIST_REFERENCENO_listbox']), Keys.chord(Keys.TAB))
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): '//ul[@id="WH_REC_RCV_LIST_REFERENCENO_listbox"]']))
		WebUI.delay(2)
		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'WH_REC_RCV_LIST_WAREHOUSE']), '191', false)
		WebUI.delay(1)
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'):'AddReceiving']))
		WebUI.delay(1)
		int count = commonkeywords.getTotalCountofElement("//input[@id='AddReceiving']")
		if(count>0){
			//WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'):'AddReceiving']))
			WebUI.sendKeys(findTestObject('Object Repository/Common/input_with_id',[('id'): 'AddReceiving']), Keys.chord(Keys.ENTER))

		}

		receivingGeneralInfo()
	}


	@Keyword
	def receivingGeneralInfo(){

		WebUI.selectOptionByValue(findTestObject('Object Repository/Common/select_with_id',[('sid'): 'WH_REC_RCV_HEDR_NEXTLOCID']), '106', false)
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[contains(@class,'display_inline icon apply_icon icon-arrow_right')]"]))
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[contains(@class,'location assignloc')]"]))
		commonkeywords.imageloadingIcon()
		int count = commonkeywords.getTotalCountofElement("//*[@id='WH_REC_ASSIGNLOCATIONGRID']/div[2]/table/tbody/tr/td[8]")
		if(count<=1){
			WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[contains(@class,'right selectloc ')]"]))
			WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//select[@id='WH_REC_ASSIGN_LOCATIONTYPE']/option[@value='STK']"]))
			commonkeywords.imageloadingIcon()
			WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'WH_REC_ADD']))
			commonkeywords.imageloadingIcon()
			WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'WH_REC_QUERY_LOCATIONS']))
			WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//span[text()='IND_STK_L2']"]))
			commonkeywords.imageloadingIcon()
			WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[contains(@class,' display_inline  icon apply_qty icon-arrow_right')]"]))
			WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//input[contains(@class,'_save_selectlocsave button')]"]))
			commonkeywords.imageloadingIcon()
		}
	
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'):'WH_REC_SAVE']))
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'):'WH_REC_MODIFY']))
		commonkeywords.imageloadingIcon()
		


	}

}
