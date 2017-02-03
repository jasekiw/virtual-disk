package com.jasekiw.virtualdisk;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jasekiw.virtualdisk.console.Kernel;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Injector injector;
    public static String version = "0.0.1";
    public static void main( String[] args )
    {
        App.injector = Guice.createInjector();
        System.out.print(App.getInjector()
                .getInstance(Kernel.class).handle(args)
        );
    }

    /**
     * Gets the application DI
     * @return the DI injector
     */
    public static Injector getInjector() {
        return App.injector;
    }


}
