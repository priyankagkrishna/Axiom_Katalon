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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Keys as Keys
import groovy.ui.text.TextEditor.TabAction as TabAction

WebUI.comment('-------User Login--------')

CustomKeywords.'common.CommonKeywords.LoginEnvironment'(GlobalVariable.environment)

WebUI.comment('------SalesOrder Naviagtion-----')

CustomKeywords.'process.Sales.navigatetoSalesOrder'()

WebUI.comment('---------SalesOrderGeneral Information-------')

CustomKeywords.'process.Sales.salesOrderGeneralInformation'()

WebUI.comment('---------ItemAddition--------')

CustomKeywords.'quickit.QuickitItemAddition.addQuickitItem'()

CustomKeywords.'quickit.QuickitItemAddition.addAdditionalInformation'()

WebUI.comment('----------Save -----------------')

CustomKeywords.'process.Sales.save'()

WebUI.comment('--------ChangeShipToStatus-----')

CustomKeywords.'process.Sales.changeShipto'()

WebUI.comment('-----------Release-----------')

CustomKeywords.'process.Sales.release'()

WebUI.comment('----------AllocateQuickitItem----------')

CustomKeywords.'quickit.QuickitItemAddition.quickitallocate'()

WebUI.comment('---------------OperationGeneralShipment--------')

CustomKeywords.'process.OperationsGenerateandProcessShipment.generateShipment'()

WebUI.comment('--------------ProcessShipment---------')

CustomKeywords.'process.OperationsGenerateandProcessShipment.processShipment'()

WebUI.comment('-------ReviewShipment--------')

CustomKeywords.'process.OperationsGenerateandProcessShipment.reviewshipment'()

WebUI.comment('------PackShipment--------')

CustomKeywords.'quickit.QuickItOperation.packQuickItItem'()
