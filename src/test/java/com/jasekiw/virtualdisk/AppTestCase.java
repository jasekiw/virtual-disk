package com.jasekiw.virtualdisk;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;

public abstract class AppTestCase extends TestCase
{
    protected App app;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTestCase( String testName )
    {
        super( testName );
    }


    public void setUp() throws Exception
    {
        super.setUp();
        App.setInjector(Guice.createInjector(new AppModule()));
        app = App.getInjector().getInstance(App.class);
    }
}
