package com.jasekiw.virtualdisk.console.commands;

import com.jasekiw.console.Command;
import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.DiskCreator;
import com.jasekiw.virtualdisk.disk.clusters.ClusterType;
import com.jasekiw.virtualdisk.disk.reading.usage.DiskUsageResult;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiskUsageCommand extends Command
{
    @Override
    public String run()
    {
        DiskCreator diskCreator = new DiskCreator();
        Disk disk = diskCreator.createDiskFromFile("disk.txt");
        DiskUsageResult result = disk.getDiskUsage();

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
