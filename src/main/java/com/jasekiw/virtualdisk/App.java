package com.jasekiw.virtualdisk;

import com.jasekiw.virtualdisk.console.Kernel;

/**
 * The main application entry point
 */
public class App extends com.jasekiw.console.App
{
    public static void main( String[] args ) { new App().run(args); }

    /**
     * Gets the application version
     * @return The application version
     */
    @Override
    public String getVersion()
    {
        return "0.0.2";
    }

    /**
     * The kernel to handle commands
     * @return The kernel
     */
    @Override
    protected Class getKernel()
    {
        return Kernel.class;
    }
}
