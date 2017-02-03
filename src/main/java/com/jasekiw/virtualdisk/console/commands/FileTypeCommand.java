package com.jasekiw.virtualdisk.console.commands;


import com.google.inject.Inject;
import com.jasekiw.virtualdisk.actions.ApplicationActions;
import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.DiskCreator;

public class FileTypeCommand extends Command
{
    private ApplicationActions actions;

    @Inject()
    FileTypeCommand(ApplicationActions actions) {
        this.actions = actions;
    }

    @Override
    public String run()
    {
        DiskCreator diskCreator = new DiskCreator();
        Disk disk = diskCreator.createDiskFromFile("disk.txt");;
        if(disk.fileExists(this.getOption(0)))
            return "(DOS) " + this.getOption(0);
        return "ERROR: File not found.";
    }

    @Override
    public String getSignature()
    {
        return "-type";
    }
}
