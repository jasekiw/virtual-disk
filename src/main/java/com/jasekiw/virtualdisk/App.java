package com.jasekiw.virtualdisk;

import com.google.inject.*;
import com.jasekiw.virtualdisk.console.Kernel;

/**
 * The main application entry point
 */
@Singleton
public class App extends com.jasekiw.console.App
{
    /**
     * Set the kernel to be used
     * @param kernel
     */
    @Inject
    public App(Kernel kernel)
    {
        super(kernel);
    }

    public static void main(String[] args ) {
        App.setInjector(Guice.createInjector(new AppModule()));
        App.getInjector().getInstance(App.class).run(args);
    }

    /**
     * Gets the application header
     * @return The application header
     */
    public static String getVersion()
    {
        return "0.0.3";
    }
}
