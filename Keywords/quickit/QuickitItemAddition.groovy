package quickit

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
import org.openqa.selenium.Keys as Keys
import groovy.ui.text.TextEditor.TabAction as TabAction
import com.kms.katalon.core.testdata.InternalData
import com.kms.katalon.core.testdata.TestData

import internal.GlobalVariable

public class QuickitItemAddition {

	common.CommonKeywords commonkeywords = new common.CommonKeywords()
	process.Sales salesprocess= new process.Sales()



	@Keyword
	def addQuickitItem(){


		InternalData itemdata = findTestData('ItemAddDetails')

		String ItemNum = itemdata.internallyGetValue("Item_No", 4)
		String Orderqty = itemdata.internallyGetValue("Order_Quantity", 4)

		WebUI.waitForElementVisible(findTestObject('Common/input_with_id_and_index', [('id') : 'SL_SO_ADD', ('index') : '2']),10)
		WebUI.click(findTestObject('Common/input_with_id_and_index', [('id') : 'SL_SO_ADD', ('index') : '2']))


		try {

			WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'SL_SO_DETL_ITEMID']), ItemNum)
			WebUI.click(findTestObject('Trading/dropdown_select', [('idlist') : '_MFG_listbox']))
		}
		catch (Exception e) {
			WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'SL_SO_DETL_ITEMID']), ItemNum)
			WebUI.click(findTestObject('Trading/dropdown_select', [('idlist') : '_MFG_listbox']))
		}

		WebUI.delay(7)


		WebUI.sendKeys(findTestObject('Common/textarea_with_id', [('id') : 'SL_SO_DETL_ITEMDESC']), Keys.chord(Keys.TAB))
		WebUI.delay(2)
		WebUI.setText(findTestObject('Common/input_quantity', [('value') : 'order_qty', ('id') : 'SL_SO_SCH_ORDQTY']), Orderqty)
		WebUI.waitForElementVisible(findTestObject('Common/input_quantity', [('value') : 'order_qty', ('id') : 'SL_SO_SCH_ORDQTY']),10)


		salesprocess.checkForPopUp()
		salesprocess.checkForPopUp()
	}


	@Keyword
	def addAdditionalInformation(){
		InternalData itemdata = findTestData('ItemAddDetails')
		String Unitcost = itemdata.internallyGetValue("Unit_Cost", 4)
		String CustomerItemNo = itemdata.internallyGetValue("Customer_Item_No", 4)
		String DateCode = itemdata.internallyGetValue("Date_Code_Req", 4)

		WebUI.waitForElementClickable(findTestObject('Trading/input_unitcost'), 10)
		WebUI.delay(5)
		WebUI.click(findTestObject('Trading/input_unitcost'))
		WebUI.setText(findTestObject('Trading/input_set_unitcost'), Unitcost)
		int cusitemno = commonkeywords.getTotalCountofElement('//*[@title=\'Customer Item No\']//parent::*[contains(@style,\'display: block;\')]')

		if (cusitemno > 0) {
			WebUI.selectOptionByIndex(findTestObject('Common/id_common', [('id') : 'SL_SO_DETL_CUSTITEMID']), 0)
		} else {
			WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'SL_SO_DETL_CUSTITEMNO']),CustomerItemNo )
		}
		WebUI.setText(findTestObject('Common/input_with_id', [('id') : 'SL_SO_DETL_REQUESTDC']), DateCode)

		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Trading/item_save'))
	}


	@Keyword
	def quickitallocate(){
		WebUI.click(findTestObject('Common/link_with_class',[('class'): 'detail_icon left icon edit center icon-edit']))
		//WebUI.waitForElementVisible(findTestObject('Common/label_with_id',[('id'): 'LBL_SL_SO_HEDR_ORDERNO']), 10)
		WebUI.scrollToElement(findTestObject('Object Repository/Common/link_with_class',[('class'): 'left icon editquickkit icon-edit ']), 30)
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/Common/link_with_class',[('class'): 'left icon editquickkit icon-edit ']))
		WebUI.click(findTestObject('QuickIt/click_allocatequickit'))
		WebUI.click(findTestObject('Object Repository/Common/link_with_class',[('class') : ' display_inline  icon apply_icon icon-arrow_right']))
		WebUI.click(findTestObject('QuickIt/click_saveallocatequickit'))
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('QuickIt/click_saveQuickitComponent'))
		String status = WebUI.getText(findTestObject('QuickIt/validate_statusfield'))
		if (status.equalsIgnoreCase('Issued')) {
			WebUI.comment("Issued Succesfully")
		}
	}



}
