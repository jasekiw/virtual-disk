package com.jasekiw.virtualdisk.console.commands;


import com.google.inject.Inject;
import com.jasekiw.console.Command;
import com.jasekiw.virtualdisk.actions.ApplicationActions;

public class UnknownCommand extends Command
{
    private ApplicationActions actions;
    @Inject()
    UnknownCommand(ApplicationActions actions) {
        this.actions = actions;
    }
    @Override
    public String run()
    {
       return "Unknown Command\n" + actions.getUsage();
    }

    @Override
    public String getSignature()
    {
        return null;
    }
}
