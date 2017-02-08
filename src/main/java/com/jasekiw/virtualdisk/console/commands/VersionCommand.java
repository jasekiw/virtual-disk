package com.jasekiw.virtualdisk.console.commands;


import com.google.inject.Inject;
import com.jasekiw.console.Command;
import com.jasekiw.virtualdisk.actions.ApplicationActions;

public class VersionCommand extends Command
{
    private ApplicationActions actions;

    @Inject()
    VersionCommand(ApplicationActions actions) {
        this.actions = actions;
    }
    @Override
    public String run()
    {
        return actions.version();
    }

    @Override
    public String getSignature()
    {
        return "-v";
    }
}
