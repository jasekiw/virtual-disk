package com.jasekiw.virtualdisk.actions;

import com.jasekiw.virtualdisk.App;


public class ApplicationActions
{
    public String version() {
        return "Virtual Hard Disk.  Version " + App.version + "\n";
    }

    public String getUsage() {
        return "-dir - List files on the disk \n" +
        "-type <filename> - Get the type of the file given. An error will be thrown if it does not exist.\n" +
        "-v - Get the version.\n" +
        "-? - Get help.\n";
    }
}
