package com.jasekiw.virtualdisk.console.commands;

import com.jasekiw.console.Command;
import com.jasekiw.console.exceptions.ConsoleException;
import com.jasekiw.console.exceptions.ParameterException;
import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.DiskCreator;
import com.jasekiw.virtualdisk.disk.exceptions.IncorrectClusterSizeException;

public class DirectoryListingCommand extends Command
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
        return String.join("\n",disk.getReader().directoryListing());
    }

    @Override
    public String getSignature()
    {
        return "-dir";
    }

    @Override
    public String getDescription()
    {
        return "List files on the disk.";
    }
}
