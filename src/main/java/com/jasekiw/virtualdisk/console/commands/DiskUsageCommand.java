package com.jasekiw.virtualdisk.console.commands;

import com.jasekiw.console.Command;
import com.jasekiw.console.exceptions.ConsoleException;
import com.jasekiw.console.exceptions.ParameterException;
import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.DiskCreator;
import com.jasekiw.virtualdisk.disk.clusters.ClusterType;
import com.jasekiw.virtualdisk.disk.exceptions.IncorrectClusterSizeException;
import com.jasekiw.virtualdisk.disk.reading.usage.DiskUsageResult;

public class DiskUsageCommand extends Command
{
    @Override
    public String run() throws ConsoleException
    {
        DiskCreator diskCreator = new DiskCreator();
        Disk disk = null;
        try {
            disk = diskCreator.createDiskFromFile("disk.txt");
        } catch (IncorrectClusterSizeException e) {
            throw new ParameterException("The cluster size is incorrect. It must be divisible by 2");
        }
        DiskUsageResult result = disk.getReader().getDiskUsage();

        String output = "Disk Usage::\n";
        output += "State Count Percent\n";
        output += "Used  " + result.getUsedClustersCount() + "   " + result.getUsedClusterPercent() +  "\n";
        output += "Avail " + result.getClustersCount(ClusterType.empty) + "   " + result.getUsagePercent(ClusterType.empty) +  "\n";
        output += "Bad   " + result.getClustersCount(ClusterType.damaged)+ "   " + result.getUsagePercent(ClusterType.damaged) +  "\n";
        output += "Total number of clusters: " + result.getClustersCountTotal() + "\n";
        output += "Total number used: " + result.getUsedClustersCount() + "\n";

        if(result.getUsedClustersCount() == result.getClustersCountTotal())
            output += "***Disk full***\n";
        return output;
    }

    @Override
    public String getSignature()
    {
        return "-du";
    }

    @Override
    public String getDescription()
    {
        return "Gets the disk usage of the disk.";
    }
}
