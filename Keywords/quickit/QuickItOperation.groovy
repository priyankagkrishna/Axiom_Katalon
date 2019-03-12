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
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class QuickItOperation {
	common.CommonKeywords commonkeywords = new common.CommonKeywords()
	@Keyword
	def packQuickItItem(String index,String findex,String xpath){

		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Common/select_with_id', [('sid') : 'WH_SHIP_SHP_HEDR_OPERATION']))
		WebUI.click(findTestObject('Pack_Operation/select_packoption'))
		commonkeywords.imageloadingIcon()
		WebUI.click(findTestObject('Common/filter_RefNo'))
		WebUI.delay(2)
		String orderpackNum = GlobalVariable.orderNum
		WebUI.waitForElementClickable(findTestObject('ReviewandPull/order_no_isequalto',[('index'): index]), 20)
		WebUI.delay(4)
		WebUI.setText(findTestObject('ReviewandPull/order_no_isequalto',[('index'): index]), orderpackNum)
		WebUI.click(findTestObject('Pack_Operation/click_packfilter',[('findex'): findex]))
		WebUI.click(findTestObject('ReviewandPull/click_edit'))
		WebUI.click(findTestObject('Pack_Operation/Common/select_Pick_Pack_InspectBy',[('id'):'WH_SHIP_SHP_HEDR_PICKEDBYID']))
		WebUI.click(findTestObject('Pack_Operation/Common/select_Pick_Pack_InspectBy',[('id'):'WH_SHIP_SHP_HEDR_PACKEDBYID']))
		WebUI.click(findTestObject('Pack_Operation/Common/select_Pick_Pack_InspectBy',[('id'):'WH_SHIP_SHP_HEDR_INSPBYID']))
		WebUI.click(findTestObject('Pack_Operation/item_edit'))
		commonkeywords.imageloadingIcon()
	}
}
