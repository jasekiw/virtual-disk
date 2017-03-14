package com.jasekiw.virtualdisk;

import com.jasekiw.console.App;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends AppTestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }


    public void testSingleton()
    {
        com.jasekiw.console.App app1 = App.getInjector().getInstance(com.jasekiw.console.App.class);
        assertTrue(app1.equals(app));
    }
}
