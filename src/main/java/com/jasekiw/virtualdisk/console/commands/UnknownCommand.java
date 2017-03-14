package com.jasekiw.virtualdisk.console.commands;

import com.google.inject.Inject;
import com.jasekiw.console.AppUsage;
import com.jasekiw.console.Command;

public class UnknownCommand extends Command
{
    private AppUsage usage;
    @Inject()
    UnknownCommand(AppUsage usage) {
        this.usage = usage;
    }
    @Override
    public String run()
    {
       return "Unknown Command\n" + usage.getUsage();
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
