package com.jasekiw.virtualdisk.console.commands;

import com.jasekiw.console.Command;
import com.jasekiw.console.exceptions.ConsoleException;
import com.jasekiw.console.exceptions.ParameterException;
import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.DiskCreator;
import com.jasekiw.virtualdisk.disk.exceptions.IncorrectClusterSizeException;

public class FileTypeCommand extends Command
{
    @Override
    public String run() throws ConsoleException
    {
        DiskCreator diskCreator = new DiskCreator();
        Disk disk = null;
        try {
            disk = diskCreator.createDiskFromFile("disk.txt");
        } catch (IncorrectClusterSizeException e) {
            throw new ParameterException("The cluster size must be divisible by 2");
        }
        if(disk.getReader().fileExists(this.getOption(0)))
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
