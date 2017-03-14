package com.jasekiw.virtualdisk.console.commands;

import com.jasekiw.console.Command;

public class AddFileCommand extends Command
{
    @Override
    public String run()
    {
        return null;
    }

    @Override
    public String getSignature()
    {
        return "-add <filename>";
    }

    @Override
    public String getDescription()
    {
        return "Adds a file to the disk";
    }

}
