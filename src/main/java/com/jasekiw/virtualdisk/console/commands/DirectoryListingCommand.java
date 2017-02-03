package com.jasekiw.virtualdisk.console.commands;

import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.DiskCreator;

public class DirectoryListingCommand extends Command
{
    @Override
    public String run()
    {
        DiskCreator diskCreator = new DiskCreator();
        Disk disk = diskCreator.createDiskFromFile("disk.txt");
        return String.join("\n",disk.directoryListing());
    }

    @Override
    public String getSignature()
    {
        return "-dir";
    }
}
