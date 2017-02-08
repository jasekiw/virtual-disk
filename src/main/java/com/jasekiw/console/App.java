package com.jasekiw.console;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jasekiw.virtualdisk.console.Kernel;


public abstract class App
{
    private static Injector injector;
    public abstract String getVersion();
    protected abstract Class getKernel();

    /**
     * Gets the application DI
     * @return the DI injector
     */
    public static Injector getInjector() {
        return App.injector;
    }
    public App() {
        App.injector = Guice.createInjector();
    }

    public void run(String[] args) {
        System.out.print(((Kernel)App.getInjector().getInstance(getKernel())).handle(args));
    }
}
