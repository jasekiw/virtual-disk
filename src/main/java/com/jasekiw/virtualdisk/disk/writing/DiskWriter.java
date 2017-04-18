package com.jasekiw.virtualdisk.disk.writing;

import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.clusters.Cluster;
import com.jasekiw.virtualdisk.disk.clusters.EmptyCluster;
import com.jasekiw.virtualdisk.disk.clusters.FileDataCluster;
import com.jasekiw.virtualdisk.disk.clusters.FileHeaderCluster;
import com.jasekiw.virtualdisk.disk.writing.exceptions.InsufficientDiskSpace;

import java.util.Arrays;

public class DiskWriter
{
    protected Disk disk;

    public DiskWriter(Disk disk)
    {
        this.disk = disk;
    }

    public void writeFile(String filename, byte[] data) throws InsufficientDiskSpace
    {
        if(!hasEnoughDiskSpace(filename.length(), data.length))
            throw new InsufficientDiskSpace();

        EmptyCluster firstEmptyCluster = disk.getRootCluster().getEmptyCluster();
        disk.getRootCluster().setEmptyCluster(firstEmptyCluster.getNextEmptyCluster());


        FileHeaderCluster fileHeader = new FileHeaderCluster(disk);
        disk.setCluster(firstEmptyCluster.getAddress(), fileHeader);
        fileHeader.setFileName(filename);
        FileDataCluster[] dataClusters = fileHeader.setData(data);
        for( int i = 0; i < dataClusters.length; i++)
        {
            EmptyCluster emptyCluster = disk.getRootCluster().getEmptyCluster();
            disk.getRootCluster().setEmptyCluster(emptyCluster.getNextEmptyCluster());
            disk.setCluster(emptyCluster.getAddress(), dataClusters[i]);
            if(i > 0)
                dataClusters[i - 1].setNextDataAddress(dataClusters[i].getAddress());
        }
        Arrays.stream(dataClusters).findFirst().ifPresent(fileDataCluster ->
                fileHeader.setNextDataAddress(fileDataCluster.getAddress()));
        FileHeaderCluster lastFileHeaderCluster = disk.getRootCluster().getLastFileHeaderCluster();
        if(lastFileHeaderCluster != null)
            lastFileHeaderCluster.setNextFileHeaderClusterAddress(fileHeader.getAddress());
        else
            disk.getRootCluster().setFileHeaderClusterAddress(fileHeader.getAddress());
    }

    /**
     * Checks if there is enough disk space to hold this file
     * @param filenameLength The length of the filename
     * @param dataLength The length of the data to be written
     * @return true if there is enough disk space, false if not
     */
    protected boolean hasEnoughDiskSpace(int filenameLength, int dataLength)
    {
        return !(disk.getReader().getEmptyClusters() < getClustersNeeded(filenameLength, dataLength));
    }

    protected int getClustersNeeded(int filenameLength, int dataLength) {
        int fileHeaderSpace = FileHeaderCluster.getUsableDataSpaceBytes(disk.getClusterNibbleSize(), filenameLength);
        int fileDataSpace = FileDataCluster.getUsableSpaceBytes(disk.getClusterNibbleSize());
        int remainingDataSize = dataLength - fileHeaderSpace;
        return (int) Math.ceil((double) remainingDataSize / (double) fileDataSpace);
    }

}
