package com.jasekiw.virtualdisk.console.commands;

import com.google.inject.Inject;
import com.jasekiw.console.AppUsage;
import com.jasekiw.console.Command;
import com.jasekiw.console.exceptions.ConsoleException;
import com.jasekiw.virtualdisk.App;

public class DefaultCommand extends Command
{
    protected AppUsage usage;
    @Inject
    public DefaultCommand(AppUsage usage) {
        this.usage = usage;
    }
    @Override
    public String run() throws ConsoleException
    {
        String usage = "Virtual Hard Disk.  Version " + App.getVersion() + "\n";
        usage += this.usage.getUsage();
        return usage;
    }

    @Override
    public String getSignature()
    {
        return null;
    }

    @Override
    public String getDescription()
    {
        return null;
    }
}
