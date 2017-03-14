package com.jasekiw.virtualdisk;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.jasekiw.console.AppUsage;
import com.jasekiw.virtualdisk.console.Kernel;


public class AppModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(com.jasekiw.console.Kernel.class).to(Kernel.class).in(Singleton.class);
        bind(com.jasekiw.console.App.class).to(App.class).in(Singleton.class);
        bind(AppUsage.class).in(Singleton.class);
    }
}
