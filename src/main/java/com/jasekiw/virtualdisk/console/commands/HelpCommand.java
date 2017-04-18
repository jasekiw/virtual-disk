package com.jasekiw.virtualdisk.console.commands;


import com.google.inject.Inject;
import com.jasekiw.console.AppUsage;
import com.jasekiw.console.Command;
import com.jasekiw.console.exceptions.ConsoleException;

public class HelpCommand extends Command
{

    private AppUsage usage;

    @Inject()
    HelpCommand(AppUsage usage) {
        this.usage = usage;
    }

    @Override
    public String run() throws ConsoleException
    {
        return usage.getUsage();
    }

    @Override
    public String getSignature()
    {
        return "-?";
    }

    @Override
    public String getDescription()
    {
        return "Get help.";
    }
}
