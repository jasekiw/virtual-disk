package com.jasekiw.virtualdisk.disk.reading;

import com.jasekiw.virtualdisk.convertors.ByteToHex;
import com.jasekiw.virtualdisk.convertors.HexToByte;
import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.DiskFile;
import com.jasekiw.virtualdisk.disk.clusters.Cluster;
import com.jasekiw.virtualdisk.disk.clusters.EmptyCluster;
import com.jasekiw.virtualdisk.disk.clusters.FileHeaderCluster;
import com.jasekiw.virtualdisk.disk.clusters.RootCluster;
import com.jasekiw.virtualdisk.disk.exceptions.DiskFileNotFound;
import com.jasekiw.virtualdisk.disk.reading.usage.DiskUsageResult;

import java.util.ArrayList;

public class DiskReader
{
    protected HexToByte hexConvertor;
    protected ByteToHex byteConvertor;
    protected Disk disk;
    public DiskReader(Disk disk) {
        this.disk = disk;
        hexConvertor = new HexToByte();
        byteConvertor = new ByteToHex();
    }
    public String outputDisk() {
        String output = "";
        for(byte i = 0; i < disk.clusterLength(); i++)
            output += outputCluster(i == 0 ? disk.getRootCluster() : disk.getCluster(i));
        return output;
    }

    protected String outputCluster(Cluster cluster) {
        String output = "";
        output += hexConvertor.getHexStringFromHexBytes(byteConvertor.byteToHexArray(cluster.getAddress())) + ":";
        output += hexConvertor.getHexStringFromHexBytes(cluster.getNibble(0));
        for(int x =1; x < cluster.size(); x+= 2)
        {
            if(x + 1 >= cluster.size())
                output += hexConvertor.getHexStringFromHexBytes(cluster.getNibble(x));
            else
                output += hexConvertor.getHexStringFromHexBytes(cluster.getNibbleArray(x));
        }
        return output +  "\n";
    }

    public String[] directoryListing() {
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

    public boolean fileExists(String filename) {
       String[] files =  directoryListing();
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

    public DiskUsageResult getDiskUsage()
    {
        DiskUsageResult result = new DiskUsageResult();
        for(int i =0; i < disk.clusterLength(); i++)
            result.incrementClustersCount(disk.getCluster(i).getType());
        return result;
    }

    public int getEmptyClusters() {
        EmptyCluster cluster = disk.getRootCluster().getEmptyCluster();
        int amount = 0;
        if(cluster == null)
            return 0;
        while(cluster != null)
        {
            amount++;
            cluster = cluster.getNextEmptyCluster();
        }
        return amount;
    }

    public DiskFile readFile(FileHeaderCluster cluster)
    {
        DiskFile file = new DiskFile();
        file.filename = cluster.getFileName();
        file.data = cluster.getData();
        return file;
    }

    public DiskFile readFile(String filename) throws DiskFileNotFound
    {
       FileHeaderCluster cluster = disk.getRootCluster().getFileHeaderCluster();
        String aFileName = cluster.getFileName();
       while(cluster != null && !cluster.getFileName().equals(filename))
           cluster = cluster.getNextFileHeaderCluster();
        if(cluster == null)
            throw new DiskFileNotFound(filename);
        return readFile(cluster);
    }


}
