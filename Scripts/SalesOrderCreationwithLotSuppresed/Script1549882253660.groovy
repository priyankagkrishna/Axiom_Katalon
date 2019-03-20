import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.ui.text.TextEditor.TabAction as TabAction

WebUI.comment('-------User Login--------')

CustomKeywords.'common.CommonKeywords.LoginEnvironment'(GlobalVariable.environment)

WebUI.comment('------SalesOrder Naviagtion-----')

CustomKeywords.'process.Sales.navigatetoSalesOrder'()

WebUI.comment('---------SalesOrderGeneral Information-------')

CustomKeywords.'process.Sales.salesOrderGeneralInformation'()

WebUI.comment('---------ItemAddition--------')

CustomKeywords.'process.Sales.itemQuickAddwithlotAllocation'()

WebUI.comment('--------------Allocation-----------')

CustomKeywords.'process.Sales.lotAllocationFromGrid'()

WebUI.comment('----------Save -----------------')

CustomKeywords.'process.Sales.save'()

WebUI.comment('-----------Release-----------')

CustomKeywords.'process.Sales.release'()

WebUI.comment('---------Operations-----------')

/*WebUI.callTestCase(findTestCase('Operations'), [('index') : '5', ('findex') : '3', ('xpath') : '//a[@class=\'display_inline  icon apply_icon icon-arrow_right\']'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.comment('------------------Finance---------------')

WebUI.callTestCase(findTestCase('FinanceProcess'), [:], FailureHandling.STOP_ON_FAILURE)
*/
