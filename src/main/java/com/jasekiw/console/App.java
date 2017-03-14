package com.jasekiw.console;

import com.google.inject.*;
import com.jasekiw.virtualdisk.console.Kernel;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class App
{
    private static Injector injector;
    public static String getVersion() {
        throw new NotImplementedException();
    }
    protected Kernel kernel;
    /**
     * Gets the application DI
     * @return the DI injector
     */
    public static Injector getInjector() {
        return injector;
    }

    public static void setInjector(Injector injector) { App.injector = injector; }

    @Inject
    public App(Kernel kernel) {
        this.kernel = kernel;
    }

    public void run(String[] args) {
        System.out.print(this.kernel.handle(args));
    }
}
