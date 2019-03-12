
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String

import com.kms.katalon.core.testobject.TestObject


def static "finance.FinanceInvoiceGeneration.naviagteToInvoice"() {
    (new finance.FinanceInvoiceGeneration()).naviagteToInvoice()
}

def static "finance.FinanceInvoiceGeneration.invoiceGenerate"() {
    (new finance.FinanceInvoiceGeneration()).invoiceGenerate()
}

def static "finance.FinanceInvoiceGeneration.invoiceInquire"() {
    (new finance.FinanceInvoiceGeneration()).invoiceInquire()
}

def static "quickit.QuickitItemAddition.addQuickitItem"() {
    (new quickit.QuickitItemAddition()).addQuickitItem()
}

def static "quickit.QuickitItemAddition.addAdditionalInformation"() {
    (new quickit.QuickitItemAddition()).addAdditionalInformation()
}

def static "quickit.QuickitItemAddition.quickitallocate"() {
    (new quickit.QuickitItemAddition()).quickitallocate()
}

def static "common.ExcelOperations.updateTestResultsToExcel"(
    	String testCaseName	
     , 	String testStatus	) {
    (new common.ExcelOperations()).updateTestResultsToExcel(
        	testCaseName
         , 	testStatus)
}

def static "common.ExcelOperations.clearOldFile"() {
    (new common.ExcelOperations()).clearOldFile()
}

def static "quickit.QuickItOperation.packQuickItItem"(
    	String index	
     , 	String findex	
     , 	String xpath	) {
    (new quickit.QuickItOperation()).packQuickItItem(
        	index
         , 	findex
         , 	xpath)
}

def static "process.OperationsGenerateandProcessShipment.generateShipment"() {
    (new process.OperationsGenerateandProcessShipment()).generateShipment()
}

def static "process.OperationsGenerateandProcessShipment.processShipment"() {
    (new process.OperationsGenerateandProcessShipment()).processShipment()
}

def static "process.OperationsGenerateandProcessShipment.reviewshipment"() {
    (new process.OperationsGenerateandProcessShipment()).reviewshipment()
}

def static "process.OperationsGenerateandProcessShipment.packShipment"(
    	String index	
     , 	String findex	
     , 	String xpath	) {
    (new process.OperationsGenerateandProcessShipment()).packShipment(
        	index
         , 	findex
         , 	xpath)
}

def static "process.OperationsGenerateandProcessShipment.navigatetoShippingInformation"() {
    (new process.OperationsGenerateandProcessShipment()).navigatetoShippingInformation()
}

def static "process.OperationsGenerateandProcessShipment.stageprocess"(
    	String index	) {
    (new process.OperationsGenerateandProcessShipment()).stageprocess(
        	index)
}

def static "common.CommonKeywords.login"(
    	String UserName	
     , 	String Password	) {
    (new common.CommonKeywords()).login(
        	UserName
         , 	Password)
}

def static "common.CommonKeywords.LoginEnvironment"(
    	String environment	) {
    (new common.CommonKeywords()).LoginEnvironment(
        	environment)
}

def static "common.CommonKeywords.GetEnvironmentConfig"(
    	String environment	
     , 	String config	) {
    (new common.CommonKeywords()).GetEnvironmentConfig(
        	environment
         , 	config)
}

def static "common.CommonKeywords.Login"(
    	String userName	
     , 	String password	) {
    (new common.CommonKeywords()).Login(
        	userName
         , 	password)
}

def static "common.CommonKeywords.getTotalCountofElement"(
    	String xPath	) {
    (new common.CommonKeywords()).getTotalCountofElement(
        	xPath)
}

def static "common.CommonKeywords.clickonAllocatedlink"() {
    (new common.CommonKeywords()).clickonAllocatedlink()
}

def static "common.CommonKeywords.imageloadingIcon"() {
    (new common.CommonKeywords()).imageloadingIcon()
}

def static "common.CommonKeywords.setFilterinGrid"(
    	String index	
     , 	String filterText	) {
    (new common.CommonKeywords()).setFilterinGrid(
        	index
         , 	filterText)
}

def static "process.Sales.refreshBrowser"() {
    (new process.Sales()).refreshBrowser()
}

def static "process.Sales.clickElement"(
    	TestObject to	) {
    (new process.Sales()).clickElement(
        	to)
}

def static "process.Sales.getHtmlTableRows"(
    	TestObject table	
     , 	String outerTagName	) {
    (new process.Sales()).getHtmlTableRows(
        	table
         , 	outerTagName)
}

def static "process.Sales.navigatetoSalesOrder"() {
    (new process.Sales()).navigatetoSalesOrder()
}

def static "process.Sales.salesOrderGeneralInformation"() {
    (new process.Sales()).salesOrderGeneralInformation()
}

def static "process.Sales.uncheckhold"() {
    (new process.Sales()).uncheckhold()
}

def static "process.Sales.addItemInSalesOrder"() {
    (new process.Sales()).addItemInSalesOrder()
}

def static "process.Sales.checkForPopUp"() {
    (new process.Sales()).checkForPopUp()
}

def static "process.Sales.checkForPopUp_Gp"() {
    (new process.Sales()).checkForPopUp_Gp()
}

def static "process.Sales.save"() {
    (new process.Sales()).save()
}

def static "process.Sales.release"() {
    (new process.Sales()).release()
}

def static "process.Sales.itemQuickAddwithlotAllocation"() {
    (new process.Sales()).itemQuickAddwithlotAllocation()
}

def static "process.Sales.lotAllocationFromGrid"() {
    (new process.Sales()).lotAllocationFromGrid()
}

def static "process.Sales.addserializedItem"() {
    (new process.Sales()).addserializedItem()
}

def static "process.Sales.additemlotSupandSerialized"() {
    (new process.Sales()).additemlotSupandSerialized()
}

def static "process.Sales.lotAllocationFromGridLotSupandSer"() {
    (new process.Sales()).lotAllocationFromGridLotSupandSer()
}

def static "process.Sales.disableQuickit"() {
    (new process.Sales()).disableQuickit()
}

def static "process.Sales.setCustomeridNo"(
    	String CustomerItemNo	) {
    (new process.Sales()).setCustomeridNo(
        	CustomerItemNo)
}

def static "process.Sales.changeShipto"() {
    (new process.Sales()).changeShipto()
}

def static "process.Sales.navigatetodocumentspage"() {
    (new process.Sales()).navigatetodocumentspage()
}

def static "common.ValidationKeywords.validateAndUpdateExcelbyTextMatch"(
    	String valueToCompare	
     , 	String valueFromApp	
     , 	String statusMessage	) {
    (new common.ValidationKeywords()).validateAndUpdateExcelbyTextMatch(
        	valueToCompare
         , 	valueFromApp
         , 	statusMessage)
}

def static "common.ValidationKeywords.validateAndUpdateExcelbyVerifyElement"(
    	String xpath	
     , 	String statusMessage	) {
    (new common.ValidationKeywords()).validateAndUpdateExcelbyVerifyElement(
        	xpath
         , 	statusMessage)
}

def static "common.ValidationKeywords.validationForItemAdd"(
    	String ItemNum	) {
    (new common.ValidationKeywords()).validationForItemAdd(
        	ItemNum)
}
