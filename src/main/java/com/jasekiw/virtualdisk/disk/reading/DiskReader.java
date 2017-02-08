package com.jasekiw.virtualdisk.disk.reading;

import com.jasekiw.virtualdisk.convertors.ByteToHex;
import com.jasekiw.virtualdisk.convertors.HexToByte;
import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.clusters.Cluster;
import com.jasekiw.virtualdisk.disk.clusters.ClusterType;
import com.jasekiw.virtualdisk.disk.clusters.FileHeaderCluster;
import com.jasekiw.virtualdisk.disk.clusters.RootCluster;
import com.jasekiw.virtualdisk.disk.reading.usage.ClusterStatistics;
import com.jasekiw.virtualdisk.disk.reading.usage.DiskUsageResult;

import java.util.ArrayList;

public class DiskReader
{
    protected HexToByte hexConvertor;
    protected ByteToHex byteConvertor;
    public DiskReader() {
        hexConvertor = new HexToByte();
        byteConvertor = new ByteToHex();
    }
    public String outputDisk(Disk disk) {
        String output = "";
        for(byte i = 0; i < disk.clusterLength(); i++) {
            output += hexConvertor.getHexStringFromHexBytes(byteConvertor.byteTohexArray(i)) + ":";
            output += hexConvertor.getHexStringFromHexBytes(disk.getCluster(i).getNibble(0));
            for(int x =1; x < disk.getCluster(i).size(); x+= 2)
            {
                if(x + 1 >= disk.getCluster(i).size())
                    output += hexConvertor.getHexStringFromHexBytes(disk.getCluster(i).getNibble(x));
                else
                    output += hexConvertor.getHexStringFromHexBytes(disk.getCluster(i).getNibbleByteArray(x));
            }
            output += "\n";
        }
        return output;
    }

    public String[] directoryListing(Disk disk) {
        RootCluster rootCluster = disk.getRootCluster();
        FileHeaderCluster currentFileHeader = rootCluster.getFileHeaderCluster();
        ArrayList<String> files = new ArrayList<>();
        boolean hitEnd = false;
        while(!hitEnd)
        {
            if(currentFileHeader == null)
                hitEnd = true;
            else
            {
                files.add(currentFileHeader.getFileName());
                currentFileHeader = currentFileHeader.getNextFileHeaderCluster();
            }

        }
        String[] filesStringArray = new String[files.size()];
        files.toArray(filesStringArray);
        return filesStringArray ;
    }

    public boolean fileExists(Disk disk, String filename) {
       String[] files =  directoryListing(disk);
       boolean fileFound = false;
       int currentIndex = 0;
       while(!fileFound && currentIndex < files.length)
       {
           if(files[currentIndex].equals(filename))
               fileFound = true;
           else
            currentIndex++;
       }
       return fileFound;
    }

    public DiskUsageResult getDiskUsage(Disk disk)
    {
        DiskUsageResult result = new DiskUsageResult();
        for(int i =0; i < disk.clusterLength(); i++)
            result.incrementClustersCount(disk.getCluster(i).getType());
        return result;
    }

}
