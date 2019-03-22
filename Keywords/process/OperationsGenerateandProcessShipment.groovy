package process

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After

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

import common.CommonKeywords
import internal.GlobalVariable

public class OperationsGenerateandProcessShipment {

	common.CommonKeywords commonkeywords = new common.CommonKeywords()
	common.ExcelOperations operations = new common.ExcelOperations()
	common.ValidationKeywords validationKeywords = new common.ValidationKeywords()



	@Keyword
	def generateShipment(){
		String refNum = GlobalVariable.orderNum
		WebUI.delay(4)
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Operations']))
		try {
			WebUI.click(findTestObject('GenerateShipment/wareHouse'))
		} catch (Exception e) {
			WebUI.delay(2)
			WebUI.click(findTestObject('GenerateShipment/wareHouse'))
		}
		WebUI.delay(1)
		int currentTab = WebUI.getWindowIndex()
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Generate Shipment']))
		WebUI.switchToWindowIndex(currentTab+1)
		WebUI.waitForElementVisible(findTestObject('Common/element_with_text', [('text') : 'Manual']), 10)
		WebUI.delay(2)
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Manual']))
		int count = commonkeywords.getTotalCountofElement("//li[@class='active']/a[text()='Manual']")
		if(count<1){
			WebUI.delay(2)
			WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Manual']))
		}
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Common/filter_RefNo'))
		WebUI.delay(2)
		WebUI.waitForElementClickable(findTestObject('GenerateShipment/order_setText'), 15)
		WebUI.setText(findTestObject('GenerateShipment/order_setText'), refNum)
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Filter']))
		WebUI.delay(1)
		String validate = WebUI.getAttribute(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[@class='blue  handpointer orderno align_right numeric'][@data-itemid]"]), 'data-itemid')
		validationKeywords.validateAndUpdateExcelbyTextMatch(refNum, validate, "Navigated to Generate Shipment Page")
		WebUI.click(findTestObject('Common/input_with_class', [('class') : 'WH_SHIP_CHECKBOX_ALL']))
		WebUI.click(findTestObject('Common/input_with_id', [('id') : 'WH_SHIP_SHP_DETL_GENERATESHIPMENT']))
		WebUI.click(findTestObject('GenerateShipment/click_Yes'))
	}

	@Keyword
	def processShipment() {
		String referNum = GlobalVariable.orderNum
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Operations']))
		WebUI.click(findTestObject('GenerateShipment/wareHouse'))
		WebUI.delay(1)
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Process Shipment']))
		commonkeywords.setFilterinGrid("1", referNum)
		WebUI.click(findTestObject('ProcessShipment/filter_Out'))
		String validate = WebUI.getText(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[@class=' icon order_no']/span"]))
		validationKeywords.validateAndUpdateExcelbyTextMatch(referNum, validate, "Item Navigation to Pull Operation")
		WebUI.click(findTestObject('Common/input_with_class', [('class') : 'WH_SHIP_CHECKBOX_ALL']))
		WebUI.click(findTestObject('Common/input_with_id', [('id') : 'WH_PROC_SHIP_PULL']))
		WebUI.click(findTestObject('ProcessShipment/select_location'))
		WebUI.selectOptionByIndex(findTestObject('ProcessShipment/select_location'), 1)
		WebUI.click(findTestObject('Common/input_with_id',[('id'): 'WH_SHIP_SAVE']))
	}

	@Keyword
	def reviewshipment(){

		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Common/select_with_id', [('sid') : 'WH_SHIP_SHP_HEDR_OPERATION']))
		WebUI.click(findTestObject('ReviewandPull/select_review'))
		commonkeywords.imageloadingIcon()
		WebUI.delay(5)
		String referanceNum = GlobalVariable.orderNum
		commonkeywords.setFilterinGrid('3', referanceNum)
		WebUI.click(findTestObject('ReviewandPull/text_filter_in_process_shipment', [('text') : 'Filter']))

		int reviewenabled=commonkeywords.getTotalCountofElement("//a[@title=' Edit']")
		if(reviewenabled>0) {
			WebUI.click(findTestObject('ReviewandPull/click_edit'))
			String shipstatus = WebUI.getText(findTestObject('ReviewandPull/ship_status'))
			if (shipstatus.equalsIgnoreCase('Pulled')){
				WebUI.click(findTestObject('Common/input_with_id', [('id') : 'WH_SHIP_REVIEW_CHECKBOX_ALL']))
				WebUI.click(findTestObject('Common/input_with_id', [('id') : 'WH_SHIP_SHP_HEDR_COMPLETEREVIEW']))
			}
			WebUI.click(findTestObject('ReviewandPull/click_yes'))
		}
	}
	@Keyword
	def packShipment(String index,String findex,String xpath) {
		String orderpackNum = GlobalVariable.orderNum
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Common/select_with_id', [('sid') : 'WH_SHIP_SHP_HEDR_OPERATION']))
		WebUI.click(findTestObject('Pack_Operation/select_packoption'))
		commonkeywords.imageloadingIcon()
		commonkeywords.setFilterinGrid(index, orderpackNum)
		WebUI.click(findTestObject('Pack_Operation/click_packfilter',[('findex'): findex]))

		String validate = WebUI.getText(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[@class=' icon order_no']/span"]))
		validationKeywords.validateAndUpdateExcelbyTextMatch(orderpackNum, validate, "Item Navigation to Pack Operation")

		WebUI.click(findTestObject('ReviewandPull/click_edit'))
		String [] selectPickPack = ['WH_SHIP_SHP_HEDR_PICKEDBYID', 'WH_SHIP_SHP_HEDR_PACKEDBYID', 'WH_SHIP_SHP_HEDR_INSPBYID']
		for(int i=0;i<3;i++){
			WebUI.click(findTestObject('Pack_Operation/Common/select_Pick_Pack_InspectBy',[('id'):selectPickPack[i]]))
		}
		setAddressLabel()
		WebUI.click(findTestObject('Pack_Operation/item_edit'))
		commonkeywords.imageloadingIcon()
		String shipstatus = WebUI.getText(findTestObject('ReviewandPull/ship_status'))
		addserialNumber(shipstatus, xpath)
		WebUI.delay(4)
		shipstatus = WebUI.getText(findTestObject('ReviewandPull/ship_status'))
		validationKeywords.validateAndUpdateExcelbyTextMatch(shipstatus, "Packed", "Item packed")
	}


	def setAddressLabel(){
		WebUI.delay(2)
		WebUI.waitForElementPresent(findTestObject('Pack_Operation/address_Label'), 10)
		WebUI.click(findTestObject('Pack_Operation/address_Label'))
		WebUI.delay(3)
		WebUI.setText(findTestObject('Pack_Operation/set_AddressLabel'), '10')
		/*WebUI.doubleClick(findTestObject('Common/input_with_id',[('id'):'WH_PROC_SHIP_MODIFY']))
		 WebUI.click(findTestObject('Pack_Operation/click_yes'))*/
	}


	def addserialNumber(String shipstatus, String xpath){
		if (shipstatus.equalsIgnoreCase('Reviewed')||shipstatus.equalsIgnoreCase('Pulled')){
			int elementCount=commonkeywords.getTotalCountofElement(xpath)
			if (elementCount>0){
				WebUI.click(findTestObject('Common/link_with_class',[('class'):'display_inline  icon apply_icon icon-arrow_right']))
			}else {
				WebUI.click(findTestObject('Common/link_with_title',[('link'):'Serial Numbers']))
				WebUI.waitForElementVisible(findTestObject('Common/elem_with_dynamicXpath',[('xpath'): "//h5[text()='Serial Numbers']"]), 10)
				WebUI.click(findTestObject('Common/elem_with_dynamicXpath',[('xpath') : "(//input[@id='WH_SHIP_SERIALNUMBER_CHECKBOX'])[1]"]))
				WebUI.click(findTestObject('Common/input_with_id',[('id'): 'WH_SHIP_SAVE']))
				WebUI.delay(2)
				commonkeywords.imageloadingIcon()
			}
			try{
				WebUI.doubleClick(findTestObject('Common/input_with_id',[('id'):'WH_PROC_SHIP_MODIFY']))
			}
			catch(Exception e){
				WebUI.doubleClick(findTestObject('Common/input_with_id',[('id'):'WH_PROC_SHIP_MODIFY']))
			}
			WebUI.click(findTestObject('Pack_Operation/click_yes'))
			WebUI.waitForElementClickable(findTestObject('Common/input_with_id',[('id'):'WH_PROC_SHIP_MODIFY']), 10)
			WebUI.doubleClick(findTestObject('Common/input_with_id',[('id'):'WH_PROC_SHIP_MODIFY']))
			WebUI.waitForElementVisible(findTestObject('Pack_Operation/click_yes'), 10)
			WebUI.delay(1)
			WebUI.doubleClick(findTestObject('Pack_Operation/click_yes'))
			WebUI.click(findTestObject('Common/input_with_id',[('id'):'WH_SHIP_PROCESS']))
		}
	}



	@Keyword
	def navigatetoShippingInformation(){
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Common/element_with_text',[('text'):'Shipping Information']))
		commonkeywords.imageloadingIcon()
		WebUI.delay(6)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): '//span[@class="k-input"]']))
		try{
			WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): '//ul[@id="WH_SHIP_SHP_HEDR_SHIPTOATTENTION_listbox"]/li[3]']))
		}
		catch(Exception e){
			WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): '//ul[@id="WH_SHIP_SHP_HEDR_SHIPTOATTENTION_listbox"]/li[3]']))
		}

		WebUI.delay(3)
		WebUI.click(findTestObject('Common/input_with_id',[('id'): 'WH_SHIP_SHP_HEDR_SHIPPINGREFERENCE']))
		WebUI.delay(5)
		WebUI.doubleClick(findTestObject('Common/input_with_id',[('id'):'WH_PROC_SHIP_ADD']))
		commonkeywords.imageloadingIcon()
		WebUI.waitForElementVisible(findTestObject('Common/input_with_id',[('id'): 'WH_SHIP_SHP_TRACK_SHPTRACKNO']), 10)
		String trackno = GlobalVariable.orderNum
		WebUI.setText(findTestObject('Common/input_with_id',[('id'): 'WH_SHIP_SHP_TRACK_SHPTRACKNO']),trackno )
		commonkeywords.imageloadingIcon()
		WebUI.delay(1)
		WebUI.doubleClick(findTestObject('Common/input_with_id',[('id'): 'WH_SHIP_SAVE']))
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Common/input_with_id',[('id'): 'WH_PROC_SHIP_MODIFY']))
		WebUI.click(findTestObject('Pack_Operation/confirmation_yes'))
		commonkeywords.imageloadingIcon()
		WebUI.delay(1)
		String shipstatus = WebUI.getText(findTestObject('ReviewandPull/ship_status'))
		if(shipstatus.equalsIgnoreCase('Staged')){
			stageprocess('1')
			WebUI.delay(2)
			WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//select[@id='WH_SHIP_SHP_HEDR_OPERATION']/option[6]"]))
			commonkeywords.imageloadingIcon()
			String orderpackNum = GlobalVariable.orderNum
			commonkeywords.setFilterinGrid('3', orderpackNum)
			WebUI.click(findTestObject('Pack_Operation/click_packfilter',[('findex'): '2']))
			shipstatus = WebUI.getText(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[@class='StatusDescription handpointer']/span"]))
			validationKeywords.validateAndUpdateExcelbyTextMatch(shipstatus, "Shipped", "Item Shipped")
			WebUI.waitForElementPresent(findTestObject('Common/element_with_text', [('text') : 'Finance']), 10)
		}

		else if(shipstatus.equalsIgnoreCase('Staged')){
			validationKeywords.validateAndUpdateExcelbyTextMatch(shipstatus, "Shipped", "Item Shipped")
			WebUI.waitForElementPresent(findTestObject('Common/element_with_text', [('text') : 'Finance']), 10)
		}
	}





	@Keyword
	def stageprocess(String index){
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Operations']))
		WebUI.click(findTestObject('GenerateShipment/wareHouse'))
		WebUI.delay(1)
		int currentTab = WebUI.getWindowIndex()
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Process Shipment']))
		WebUI.switchToWindowIndex(currentTab+1)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath') : "//select[@id='WH_SHIP_SHP_HEDR_OPERATION']/option[5]"]))
		commonkeywords.imageloadingIcon()
		String orderpackNum = GlobalVariable.orderNum
		commonkeywords.setFilterinGrid(index, orderpackNum)
		WebUI.click(findTestObject('ProcessShipment/filter_Out'))
		String validate = WebUI.getText(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[@class=' icon order_no']/span"]))
		validationKeywords.validateAndUpdateExcelbyTextMatch(orderpackNum, validate, "Item Navigation to Ship Operation")
		WebUI.click(findTestObject('Common/input_with_class', [('class') : 'WH_SHIP_CHECKBOX_ALL']))
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'):'WH_PROC_SHIP_SHIP']))
	}
}


