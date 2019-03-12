
package process
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
import com.kms.katalon.core.testdata.InternalData
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import common.CommonKeywords
import common.ValidationKeywords

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


class Sales {
	common.CommonKeywords commonkeywords = new common.CommonKeywords()
	common.ExcelOperations operations=new common.ExcelOperations()
	common.ValidationKeywords validationKeywords = new common.ValidationKeywords()
	/**
	 * Refresh browser
	 */

	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}
	//Navigate to Sales	Order
	@Keyword
	def navigatetoSalesOrder(){
		WebUI.click(findTestObject('Trading/select_trading'))
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Sales']))
		WebUI.click(findTestObject('Common/element_with_text', [('text') : 'Sales Order']))
		WebUI.waitForElementPresent(findTestObject('Common/input_with_id', [('id') : 'SL_SO_ADD']), 10)
		validationKeywords.validateAndUpdateExcelbyVerifyElement("//input[@id='SL_SO_ADD']","Naviagted To SalesOrder Page")
		WebUI.click(findTestObject('Common/input_with_id', [('id') : 'SL_SO_ADD']))
		WebUI.delay(5)

	}
	//SalesOrderGeneralInformation

	@Keyword
	def salesOrderGeneralInformation(){

		InternalData data = findTestData('SalesOrderGeneralInfo')
		String CustomerName = data.internallyGetValue("Customer", 0)
		String OEMCustomerName = data.internallyGetValue("OEM_Customer", 0)
		WebUI.waitForElementPresent(findTestObject('Common/input_with_id', [('id') : 'SL_SO_HEDR_CUSTID']), 10)
		WebUI.delay(2)
		WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'SL_SO_HEDR_CUSTID']), CustomerName)
		WebUI.click(findTestObject('Trading/dropdown_select', [('idlist') : 'SL_SO_HEDR_CUSTID_listbox']))
		WebUI.waitForElementPresent(findTestObject('Trading/verify_elementpresent'), 25)
		try {
			WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'SL_SO_HEDR_OEMCUSTID']), OEMCustomerName)
			WebUI.click(findTestObject('Trading/dropdown_select', [('idlist') : 'SL_SO_HEDR_OEMCUSTID_listbox']))
		} catch (Exception e) {
			WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'SL_SO_HEDR_OEMCUSTID']), OEMCustomerName)
			WebUI.waitForElementVisible(findTestObject('Trading/dropdown_select', [('idlist') : 'SL_SO_HEDR_OEMCUSTID_listbox']), 10)
			WebUI.click(findTestObject('Trading/dropdown_select', [('idlist') : 'SL_SO_HEDR_OEMCUSTID_listbox']))
		}
		String orderNum = WebUI.getText(findTestObject('Object Repository/Trading/label_orderNo'))
		WebUI.comment(orderNum)
		GlobalVariable.orderNum = orderNum
		WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'SL_SO_HEDR_STRIPCUSTPO']), orderNum)
		WebUI.click(findTestObject('Common/input_with_id', [('id') : 'SL_SO_HEDR_REORDERDT']))
		try {
			WebUI.waitForJQueryLoad(5)
		} catch (Exception e) {
			WebUI.delay(1)
		}
		uncheckhold()

	}


	@Keyword
	def uncheckhold(){
		int checkboxCount = commonkeywords.getTotalCountofElement('//input[@checked=\'checked\']')

		if (checkboxCount > 0) {
			WebUI.click(findTestObject('Object Repository/Common/input_with_id', [('id') : 'SL_SO_HEDR_SHIPHOLD']))
		}
		WebUI.waitForPageLoad(20)
	}



	//Item Addition
	@Keyword
	def addItemInSalesOrder(){

		InternalData itemdata = findTestData('ItemAddDetails')
		String ItemNum = itemdata.internallyGetValue("Item_No", 0)
		String Orderqty = itemdata.internallyGetValue("Order_Quantity", 0)
		String Unitcost = itemdata.internallyGetValue("Unit_Cost", 0)
		String CustomerItemNo = itemdata.internallyGetValue("Customer_Item_No", 0)
		String DateCode = itemdata.internallyGetValue("Date_Code_Req", 0)
		WebUI.waitForElementVisible(findTestObject('Common/input_with_id_and_index', [('id') : 'SL_SO_ADD', ('index') : '2']),10)
		WebUI.click(findTestObject('Common/input_with_id_and_index', [('id') : 'SL_SO_ADD', ('index') : '2']))
		commonkeywords.imageloadingIcon()
		validationKeywords.validateAndUpdateExcelbyVerifyElement("//label[@id='LBL_SL_SO_DETL_ITEMID']","Header Information Added")
		try {

			WebUI.setText(findTestObject('Common/input_with_id', [('id') : '_MFG']), ItemNum)
			WebUI.click(findTestObject('Trading/dropdown_select', [('idlist') : '_MFG_listbox']))
		}
		catch (Exception e) {
			WebUI.setText(findTestObject('Common/input_with_id', [('id') : '_MFG']), ItemNum)
			WebUI.click(findTestObject('Trading/dropdown_select', [('idlist') : '_MFG_listbox']))

		}
		WebUI.delay(7)
		WebUI.sendKeys(findTestObject('Common/textarea_with_id', [('id') : 'SL_SO_DETL_ITEMDESC']), Keys.chord(Keys.TAB))
		WebUI.delay(2)
		WebUI.setText(findTestObject('Common/input_quantity', [('value') : 'order_qty', ('id') : 'SL_SO_SCH_ORDQTY']), Orderqty)
		WebUI.waitForElementVisible(findTestObject('Common/input_quantity', [('value') : 'order_qty', ('id') : 'SL_SO_SCH_ORDQTY']),10)
		checkForPopUp()
		checkForPopUp()
		WebUI.sendKeys(findTestObject('Common/textarea_with_id', [('id') : 'SL_SO_DETL_ITEMDESC']), Keys.chord(Keys.TAB))
		WebUI.delay(2)
		WebUI.setText(findTestObject('Common/input_quantity', [('value') : 'order_qty', ('id') : 'SL_SO_SCH_ORDQTY']), Orderqty)
		WebUI.waitForElementVisible(findTestObject('Common/input_quantity', [('value') : 'order_qty', ('id') : 'SL_SO_SCH_ORDQTY']),10)
		WebUI.selectOptionByValue(findTestObject('Common/select_with_id',[('sid'): 'SL_SO_DETL_WHSEID']), '191', false)
		checkForPopUp()
		checkForPopUp()
		disableQuickit()
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'):"//div[@id=\'confirm_popup_window_validation\']//input[@value=\'No\']"]), 10)
		checkForPopUp()
		allocationProcess()
		WebUI.waitForElementClickable(findTestObject('Trading/input_unitcost'), 10)
		WebUI.delay(5)
		WebUI.click(findTestObject('Trading/input_unitcost'))
		WebUI.setText(findTestObject('Trading/input_set_unitcost'), Unitcost)
		setCustomeridNo(CustomerItemNo)
		WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'SL_SO_DETL_REQUESTDC']), DateCode)
		WebUI.click(findTestObject('Trading/item_save'))
		checkForPopUp_Gp()
		validationKeywords.validationForItemAdd(ItemNum)
		navigatetodocumentspage()
	}





	//Allocation Process

	def allocationProcess(){
		WebUI.delay(1)
		WebUI.waitForElementClickable(findTestObject('Common/link_with_title', [('link') : 'Allocated']), 10)
		commonkeywords.imageloadingIcon()
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/Common/id_common', [('id') : 'itemallocationPopup']))
		WebUI.delay(2)
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Trading/item_Allocate'))
		WebUI.click(findTestObject('Common/input_with_id_and_index', [('id') : 'SL_SO_SAVE', ('index') : '3']))
	}




	/*
	 * Check for popup
	 * 
	 */

	@Keyword
	def checkForPopUp(){
		WebUI.waitForElementVisible(findTestObject('Trading/confirmation_popup', [('variable') : 'No']), 5)
		String activeConfirmationPopupPath = "//div[contains(@id,'confirm_popup_window')][contains(@style,'display: block')]"
		int popupCount = commonkeywords.getTotalCountofElement(activeConfirmationPopupPath)
		if(popupCount>0){
			WebUI.click(findTestObject('Object Repository/Trading/confirmation_popup', [('variable') : 'No']))
		}
		commonkeywords.imageloadingIcon()
	}


	//Check for popup regarding gp


	@Keyword
	def checkForPopUp_Gp(){
		WebUI.waitForElementVisible(findTestObject('Trading/confirmation_popup', [('variable') : 'Yes']), 5)
		String activeConfirmationPopupPath = "//div[contains(@id,'confirm_popup_window')][contains(@style,'display: block')]"
		int popupCount = commonkeywords.getTotalCountofElement(activeConfirmationPopupPath)
		if(popupCount>0){
			WebUI.click(findTestObject('Object Repository/Trading/confirmation_popup', [('variable') : 'Yes']))
		}
		commonkeywords.imageloadingIcon()
	}

	@Keyword
	def save(){
		WebUI.delay(3)
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[text()='General']"]))
		WebUI.waitForPageLoad(10)
		WebUI.waitForElementPresent(findTestObject("BillingandShipment/status_field"), 10)
		String status = WebUI.getText(findTestObject('BillingandShipment/status_field'))
		if ((status.equalsIgnoreCase('Incomplete'))||(status.equalsIgnoreCase('UnIssued'))) {
			WebUI.delay(2)
			try{
				commonkeywords.imageloadingIcon()
				WebUI.click(findTestObject('Common/id_common', [('id') : 'SL_SO_SAVE']))}
			catch(Exception e){
				WebUI.click(findTestObject('Common/id_common', [('id') : 'SL_SO_SAVE']))
			}
		}
		WebUI.verifyElementPresent(findTestObject('BillingandShipment/confirmationPopUp_billingandshipment'), 10)
		WebUI.delay(2)
		commonkeywords.imageloadingIcon()
		int confirmationpopuppresent=commonkeywords.getTotalCountofElement("//div[@id='confirm_popup_window'][contains(@style,'display: block;')]//input[@value='Yes']")
		if(confirmationpopuppresent>0){
			commonkeywords.imageloadingIcon()
			WebUI.click(findTestObject('BillingandShipment/confirmationPopUp_billingandshipment'))
		}
		WebUI.waitForElementPresent(findTestObject('Common/input_with_id', [('id') : '_HOLD_RELEASE']), 30)
		int release = commonkeywords.getTotalCountofElement('//input[@id=\'_HOLD_RELEASE\']')
		if (release > 0) {
			WebUI.click(findTestObject('Common/input_with_id', [('id') : '_HOLD_CHECKBOXALL']))
			WebUI.click(findTestObject('Common/input_with_id', [('id') : '_HOLD_RELEASE']))
			commonkeywords.imageloadingIcon()
			WebUI.delay(1)
			WebUI.click(findTestObject('BillingandShipment/button_backToOrder'))
		}
		commonkeywords.imageloadingIcon()
	}

	@Keyword
	def release(){

		InternalData data = findTestData('SalesOrderGeneralInfo')
		String MailId = data.internallyGetValue("Issue_Mail", 0)
		String Subject=data.internallyGetValue("Issue_MailSubject", 0)

		String status = WebUI.getText(findTestObject('BillingandShipment/status_field'))
		if (status.equalsIgnoreCase('UnIssued')) {
			WebUI.waitForElementPresent(findTestObject('Object Repository/Common/div_loading_image_with_index',[('index'):'1']), 10)
			WebUI.waitForElementNotPresent(findTestObject('Object Repository/Common/div_loading_image_with_index',[('index'):'1']), 20)
			WebUI.click(findTestObject('Common/input_with_title', [('title') : 'Email']))
			WebUI.click(findTestObject('Common/input_with_id', [('id') : 'common_popup_button_ok']))
			WebUI.waitForElementPresent(findTestObject('Object Repository/Common/div_loading_image_with_index',[('index'):'1']), 10)
			WebUI.waitForElementNotPresent(findTestObject('Object Repository/Common/div_loading_image_with_index',[('index'):'1']), 20)
			WebUI.setText(findTestObject('Common/textarea_with_id',[('id'):'txtTo']), MailId)
			WebUI.setText(findTestObject('Common/input_with_id', [('id') : '_SUBJECT']), Subject)

			WebUI.click(findTestObject('Common/input_with_id',[('id'): '_SEND']))
		}
		status = WebUI.getText(findTestObject('BillingandShipment/status_field'))
		validationKeywords.validateAndUpdateExcelbyTextMatch(status, "Issued", "Item Succesfully Issued")
	}



	//Lot Allocation

	@Keyword
	def itemQuickAddwithlotAllocation(){

		InternalData itemdata = findTestData('ItemAddDetails')

		String ItemNum = itemdata.internallyGetValue("Item_No", 1)
		String Orderqty = itemdata.internallyGetValue("Order_Quantity", 1)
		String Unitcost = itemdata.internallyGetValue("Unit_Cost", 1)
		String CustomerItemNo = itemdata.internallyGetValue("Customer_Item_No", 1)
		String DateCode = itemdata.internallyGetValue("Date_Code_Req", 1)
		WebUI.waitForElementVisible(findTestObject('Common/input_with_id_and_index', [('id') : 'SL_SO_ADD', ('index') : '2']),10)
		WebUI.click(findTestObject('Common/input_with_id_and_index', [('id') : 'SL_SO_ADD', ('index') : '2']))
		commonkeywords.imageloadingIcon()
		validationKeywords.validateAndUpdateExcelbyVerifyElement("//label[@id='LBL_SL_SO_DETL_ITEMID']","Header Information Added")
		try {

			WebUI.setText(findTestObject('Common/input_with_id', [('id') : '_MFG']), ItemNum)
			WebUI.click(findTestObject('Trading/dropdown_select', [('idlist') : '_MFG_listbox']))
		}
		catch (Exception e) {
			WebUI.delay(2)
			WebUI.setText(findTestObject('Common/input_with_id', [('id') : '_MFG']), ItemNum)
			WebUI.click(findTestObject('Trading/dropdown_select', [('idlist') : '_MFG_listbox']))
		}
		WebUI.delay(7)
		WebUI.sendKeys(findTestObject('Common/textarea_with_id', [('id') : 'SL_SO_DETL_ITEMDESC']), Keys.chord(Keys.TAB))
		WebUI.delay(3)
		WebUI.setText(findTestObject('Common/input_quantity', [('value') : 'order_qty', ('id') : 'SL_SO_SCH_ORDQTY']), Orderqty)
		WebUI.waitForElementVisible(findTestObject('Common/input_quantity', [('value') : 'order_qty', ('id') : 'SL_SO_SCH_ORDQTY']),10)

		checkForPopUp()
		checkForPopUp()
		WebUI.selectOptionByValue(findTestObject('Common/select_with_id',[('sid'): 'SL_SO_DETL_WHSEID']), '191', false)
		disableQuickit()
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'):"//div[@id=\'confirm_popup_window_validation\']//input[@value=\'No\']"]), 10)
		checkForPopUp()
		WebUI.waitForElementClickable(findTestObject('Trading/input_unitcost'), 10)
		WebUI.delay(5)
		checkForPopUp()
		WebUI.waitForElementClickable(findTestObject('Trading/input_unitcost'), 10)
		WebUI.click(findTestObject('Trading/input_unitcost'))
		WebUI.setText(findTestObject('Trading/input_set_unitcost'), Unitcost)
		setCustomeridNo(CustomerItemNo)
		WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'SL_SO_DETL_REQUESTDC']), DateCode)
		checkForPopUp_Gp()
		WebUI.click(findTestObject('Trading/item_save'))
		checkForPopUp_Gp()
		validationKeywords.validationForItemAdd(ItemNum)

	}


	//



	@Keyword
	def lotAllocationFromGrid(){

		WebUI.click(findTestObject('Common/link_with_class',[('class') :'handpointer bluecolor soallocate']))
		commonkeywords.imageloadingIcon()
		WebUI.delay(3)
		//WebUI.click(findTestObject('LotSupressed/click_allocatedfield'))
		WebUI.setText(findTestObject('Common/input_with_id',[('id'):'SL_SO_ALLC_STOCKALLOCATED']), '100')
		WebUI.click(findTestObject('LotSupressed/save_lotsupressed'))
		navigatetodocumentspage()

	}


	@Keyword
	def addserializedItem(){

		InternalData itemdata = findTestData('ItemAddDetails')

		String ItemNum = itemdata.internallyGetValue("Item_No", 2)
		String Orderqty = itemdata.internallyGetValue("Order_Quantity", 2)
		String Unitcost = itemdata.internallyGetValue("Unit_Cost", 2)
		String CustomerItemNo = itemdata.internallyGetValue("Customer_Item_No", 2)
		String DateCode = itemdata.internallyGetValue("Date_Code_Req", 2)
		WebUI.waitForElementVisible(findTestObject('Common/input_with_id_and_index', [('id') : 'SL_SO_ADD', ('index') : '2']),10)
		WebUI.click(findTestObject('Common/input_with_id_and_index', [('id') : 'SL_SO_ADD', ('index') : '2']))
		commonkeywords.imageloadingIcon()
		validationKeywords.validateAndUpdateExcelbyVerifyElement("//label[@id='LBL_SL_SO_DETL_ITEMID']","Header Information Added")

		try {

			WebUI.setText(findTestObject('Common/input_with_id', [('id') : '_MFG']), ItemNum)
			WebUI.click(findTestObject('Trading/dropdown_select', [('idlist') : '_MFG_listbox']))
		}
		catch (Exception e) {
			WebUI.setText(findTestObject('Common/input_with_id', [('id') : '_MFG']), ItemNum)
			WebUI.click(findTestObject('Trading/dropdown_select', [('idlist') : '_MFG_listbox']))
		}

		WebUI.delay(7)


		WebUI.sendKeys(findTestObject('Common/textarea_with_id', [('id') : 'SL_SO_DETL_ITEMDESC']), Keys.chord(Keys.TAB))
		WebUI.delay(2)
		WebUI.setText(findTestObject('Common/input_quantity', [('value') : 'order_qty', ('id') : 'SL_SO_SCH_ORDQTY']), Orderqty)
		WebUI.waitForElementVisible(findTestObject('Common/input_quantity', [('value') : 'order_qty', ('id') : 'SL_SO_SCH_ORDQTY']),10)


		checkForPopUp()
		checkForPopUp()
		//checkForPopUp()


		WebUI.selectOptionByValue(findTestObject('Common/select_with_id',[('sid'): 'SL_SO_DETL_WHSEID']), '191', false)

		disableQuickit()
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'):"//div[@id=\'confirm_popup_window_validation\']//input[@value=\'No\']"]), 10)
		checkForPopUp()
		allocationProcess()
		/*WebUI.waitForElementClickable(findTestObject('Common/link_with_title', [('link') : 'Allocated']), 10)
		 WebUI.delay(5)
		 WebUI.click(findTestObject('Object Repository/Common/id_common', [('id') : 'itemallocationPopup']))
		 WebUI.delay(1)
		 commonkeywords.imageloadingIcon()
		 //WebUI.click(findTestObject('Object Repository/Common/id_common', [('id') : 'itemallocationPopup']))
		 WebUI.click(findTestObject('Trading/item_Allocate'))
		 WebUI.click(findTestObject('Common/input_with_id_and_index', [('id') : 'SL_SO_SAVE', ('index') : '3']))
		 */
		WebUI.waitForElementClickable(findTestObject('Trading/input_unitcost'), 10)
		WebUI.delay(5)
		WebUI.click(findTestObject('Trading/input_unitcost'))
		WebUI.setText(findTestObject('Trading/input_set_unitcost'), Unitcost)
		setCustomeridNo(CustomerItemNo)
		WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'SL_SO_DETL_REQUESTDC']), DateCode)
		WebUI.click(findTestObject('Trading/item_save'))
		checkForPopUp_Gp()
		validationKeywords.validationForItemAdd(ItemNum)
		navigatetodocumentspage()

	}


	@Keyword
	def additemlotSupandSerialized(){
		InternalData itemdata = findTestData('ItemAddDetails')

		String ItemNum = itemdata.internallyGetValue("Item_No", 3)
		String Orderqty = itemdata.internallyGetValue("Order_Quantity", 3)
		String Unitcost = itemdata.internallyGetValue("Unit_Cost", 3)
		String CustomerItemNo = itemdata.internallyGetValue("Customer_Item_No", 3)
		String DateCode = itemdata.internallyGetValue("Date_Code_Req", 3)


		WebUI.waitForElementVisible(findTestObject('Common/input_with_id_and_index', [('id') : 'SL_SO_ADD', ('index') : '2']),10)
		WebUI.click(findTestObject('Common/input_with_id_and_index', [('id') : 'SL_SO_ADD', ('index') : '2']))
		commonkeywords.imageloadingIcon()
		validationKeywords.validateAndUpdateExcelbyVerifyElement("//label[@id='LBL_SL_SO_DETL_ITEMID']","Header Information Added")
		try {

			WebUI.setText(findTestObject('Common/input_with_id', [('id') : '_MFG']), ItemNum)
			WebUI.click(findTestObject('Trading/dropdown_select', [('idlist') : '_MFG_listbox']))
		}
		catch (Exception e) {
			commonkeywords.imageloadingIcon()
			WebUI.delay(2)
			WebUI.setText(findTestObject('Common/input_with_id', [('id') : '_MFG']), ItemNum)
			WebUI.click(findTestObject('Trading/dropdown_select', [('idlist') : '_MFG_listbox']))
		}

		WebUI.delay(7)
		WebUI.sendKeys(findTestObject('Common/textarea_with_id', [('id') : 'SL_SO_DETL_ITEMDESC']), Keys.chord(Keys.TAB))
		WebUI.delay(2)
		WebUI.setText(findTestObject('Common/input_quantity', [('value') : 'order_qty', ('id') : 'SL_SO_SCH_ORDQTY']), Orderqty)
		WebUI.waitForElementVisible(findTestObject('Common/input_quantity', [('value') : 'order_qty', ('id') : 'SL_SO_SCH_ORDQTY']),10)

		checkForPopUp()
		checkForPopUp()

		WebUI.selectOptionByValue(findTestObject('Common/select_with_id',[('sid'): 'SL_SO_DETL_WHSEID']), '191', false)
		disableQuickit()
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'):"//div[@id=\'confirm_popup_window_validation\']//input[@value=\'No\']"]), 10)
		checkForPopUp()

		WebUI.waitForElementClickable(findTestObject('Trading/input_unitcost'), 10)
		WebUI.delay(5)
		WebUI.click(findTestObject('Trading/input_unitcost'))
		WebUI.setText(findTestObject('Trading/input_set_unitcost'), Unitcost)
		setCustomeridNo(CustomerItemNo)
		WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'SL_SO_DETL_REQUESTDC']), DateCode)
		checkForPopUp_Gp()
		WebUI.click(findTestObject('Trading/item_save'))
		checkForPopUp_Gp()
		validationKeywords.validationForItemAdd(ItemNum)


	}


	@Keyword
	def lotAllocationFromGridLotSupandSer(){

		WebUI.click(findTestObject('Common/link_with_class',[('class') :'handpointer bluecolor soallocate']))
		commonkeywords.imageloadingIcon()
		WebUI.delay(3)
		WebUI.setText(findTestObject('Common/input_with_id',[('id'):'SL_SO_ALLC_STOCKALLOCATED']), '1')
		WebUI.click(findTestObject('LotSupressed/save_lotsupressed'))

	}
	//Disabling  Quickit
	@Keyword
	def disableQuickit(){
		int quickiticon = commonkeywords.getTotalCountofElement("//input[@id='SL_SO_DETL_QUICKKIT'][@checked='checked']")

		if (quickiticon > 0){
			WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath', [('xpath') : "//input[@id='SL_SO_DETL_QUICKKIT'][@checked='checked']"]))
		}
	}
	//Set CustomerId Number
	@Keyword
	def setCustomeridNo(String CustomerItemNo){

		int cusitemno = commonkeywords.getTotalCountofElement('//*[@title=\'Customer Item No\']//parent::*[contains(@style,\'display: block;\')]')

		if (cusitemno > 0) {
			WebUI.selectOptionByIndex(findTestObject('Common/id_common', [('id') : 'SL_SO_DETL_CUSTITEMID']), 0)
		} else {
			WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'SL_SO_DETL_CUSTITEMNO']),CustomerItemNo )
		}
	}


	@Keyword
	def changeShipto(){
		WebUI.click(findTestObject('Object Repository/Common/element_with_text',[('text'): 'Billing / Shipping']))
		WebUI.delay(3)
		WebUI.selectOptionByValue(findTestObject('Common/select_with_id',[('sid'): 'SL_SO_HEDR_SHIPOPTID']), '1', false)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[@data-key='General']"]))
		WebUI.delay(2)
	}



	@Keyword
	def navigatetodocumentspage(){
		String userDir = System.getProperty("user.dir")
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//a[text()='Documents']"]))
		commonkeywords.imageloadingIcon()
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//span[text()='Customer PO']"]))
		commonkeywords.imageloadingIcon()
		WebUI.delay(2)
		WebUI.uploadFile(findTestObject('Object Repository/Common/input_with_id',[('id'): 'doc-upload']), userDir+"/Data Files/Attachments/Documents.doc")
		WebUI.click(findTestObject('Object Repository/Common/input_with_id',[('id'): 'SL_SO_SAVE']))
		WebUI.click(findTestObject('Object Repository/Common/elem_with_dynamicXpath',[('xpath'): "//div[@id='confirm_popup_window']//input[@value='Yes']"]))
		WebUI.delay(2)

	}
	
}


