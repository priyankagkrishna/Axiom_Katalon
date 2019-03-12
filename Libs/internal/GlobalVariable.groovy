package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object orderNum
     
    /**
     * <p></p>
     */
    public static Object environment
     
    /**
     * <p></p>
     */
    public static Object itemtype
     
    /**
     * <p></p>
     */
    public static Object testResultNum
     

    static {
        def allVariables = [:]        
        allVariables.put('default', ['orderNum' : '000', 'environment' : 'Stage_AWS', 'itemtype' : 0, 'testResultNum' : 0])
        
        String profileName = RunConfiguration.getExecutionProfile()
        
        def selectedVariables = allVariables[profileName]
        orderNum = selectedVariables['orderNum']
        environment = selectedVariables['environment']
        itemtype = selectedVariables['itemtype']
        testResultNum = selectedVariables['testResultNum']
        
    }
}
