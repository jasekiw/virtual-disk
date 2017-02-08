package com.jasekiw.virtualdisk.actions;

import com.google.inject.Inject;
import com.jasekiw.virtualdisk.App;


public class ApplicationActions
{
    protected App app;
    @Inject()
    ApplicationActions(App app) {
        this.app = app;
    }
    public String version() {
        return "Virtual Hard Disk.  Version " + app.getVersion() + "\n";
    }

    public String getUsage() {
        return "-dir - List files on the disk \n" +
        "-type <filename> - Get the type of the file given. An error will be thrown if it does not exist.\n" +
        "-v - Get the version.\n" +
        "-? - Get help.\n";
    }
}
