package com.jasekiw.virtualdisk.console.commands;


import com.google.inject.Inject;
import com.jasekiw.console.AppUsage;
import com.jasekiw.console.Command;

public class VersionCommand extends Command
{
    private AppUsage usage;

    @Inject()
    VersionCommand(AppUsage usage) {
        this.usage = usage;
    }
    @Override
    public String run()
    {
        return usage.getAppHeader();
    }

    @Override
    public String getSignature()
    {
        return "-v";
    }

    @Override
    public String getDescription()
    {
        return "Gets the application version.";
    }
}
