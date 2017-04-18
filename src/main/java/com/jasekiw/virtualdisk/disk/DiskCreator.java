package com.jasekiw.virtualdisk.disk;

import com.jasekiw.virtualdisk.convertors.HexToByte;
import com.jasekiw.virtualdisk.disk.clusters.ClusterCreator;
import com.jasekiw.virtualdisk.disk.exceptions.IncorrectClusterSizeException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DiskCreator
{
    HexToByte hexToByte;
    public DiskCreator() {
        hexToByte = new HexToByte();
    }
    public Disk createDiskFromString(String hd) throws IncorrectClusterSizeException
    {
        String lines[] = hd.split("\\r?\\n");
        ClusterCreator creator = new ClusterCreator();
        Disk disk = new Disk(lines.length, lines[0].length());
        for(int clusterIndex = 0; clusterIndex < lines.length; clusterIndex++)
            disk.setCluster(clusterIndex, creator.getCluster(hexToByte.getDecimalBytesFromHexString(lines[clusterIndex]), disk));



        return disk;
    }

    public Disk createDiskFromFile(String filename) throws IncorrectClusterSizeException
    {
        try {
            FileReader freader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(freader);
            String currentLine;
            String wholeFile = "";
            while ((currentLine = reader.readLine()) != null) {
                wholeFile += currentLine + "\n";
            }
            reader.close();
            freader.close();
            return createDiskFromString(wholeFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
