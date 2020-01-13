package SoapUI;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;
import com.eviware.soapui.support.SoapUIException;
import org.apache.xmlbeans.XmlException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SoapIntegration
{
    @Test
    public void soapTest() throws IOException, SoapUIException, XmlException {
        //EmployeePortal-soapui-project.xml - SOAP Request, EmployeeTestSuite
        //Library-Books-soapui-project.xml - REST Request, E2E - Add Find Delete Book

        //This is for running the test from personal laptop
        // WsdlProject project = new WsdlProject("C:\\Users\\Admin\\Documents\\SoapUI\\Library-Books-soapui-project.xml");

        //This is for Jenkins
        WsdlProject project = new WsdlProject("Library-Books-soapui-project.xml");
       
        
        WsdlTestSuite testSuite = project.getTestSuiteByName("E2E - Add Find Delete Book");
        System.out.println("Hello Count is "+ testSuite.getTestCaseCount());
        for(int i = 0 ; i < testSuite.getTestCaseCount(); i++)
        {
            WsdlTestCase testCase = testSuite.getTestCaseAt(i);
            TestRunner runner = testCase.run(new PropertiesMap(),false);
            System.out.println("status is " +runner.getStatus());
            Assert.assertEquals(Status.FINISHED,runner.getStatus());
        }

        System.out.println("Hello World");
    }

}
