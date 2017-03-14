package com.jasekiw.virtualdisk.console.commands;

import com.jasekiw.console.Command;
import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.DiskCreator;

public class FileTypeCommand extends Command
{
    @Override
    public String run()
    {
        DiskCreator diskCreator = new DiskCreator();
        Disk disk = diskCreator.createDiskFromFile("disk.txt");
        if(disk.fileExists(this.getOption(0)))
            return "(DOS) " + this.getOption(0);
        return "ERROR: File not found.";
    }

    @Override
    public String getSignature()
    {
        return "-type <filename>";
    }

    @Override
    public String getDescription()
    {
        return "Gets the type of a file.";
    }
}
