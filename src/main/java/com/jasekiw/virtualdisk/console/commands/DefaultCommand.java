package com.jasekiw.virtualdisk.console.commands;

import com.google.inject.Inject;
import com.jasekiw.console.Command;
import com.jasekiw.virtualdisk.actions.ApplicationActions;


public class DefaultCommand extends Command
{

    private ApplicationActions actions;
    @Inject()
    DefaultCommand(ApplicationActions actions) {
        this.actions = actions;
    }
    @Override
    public String run()
    {
        return actions.version() + actions.getUsage();
    }

    @Override
    public String getSignature()
    {
        return null;
    }
}
