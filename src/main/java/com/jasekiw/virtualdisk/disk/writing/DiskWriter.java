package com.jasekiw.virtualdisk.disk.writing;

import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.clusters.EmptyCluster;
import com.jasekiw.virtualdisk.disk.clusters.FileDataCluster;
import com.jasekiw.virtualdisk.disk.clusters.FileHeaderCluster;
import com.jasekiw.virtualdisk.disk.writing.exceptions.InsufficientDiskSpace;

public class DiskWriter
{
    protected Disk disk;
    public DiskWriter(Disk disk)
    {
        this.disk = disk;
    }
    public void writeFile(String filename, String data) throws InsufficientDiskSpace
    {
        EmptyCluster emptyCluster = disk.getRootCluster().getEmptyCluster();
        if(emptyCluster == null)
            throw new InsufficientDiskSpace();

        disk.getRootCluster().setEmptyCluster(emptyCluster.getNextEmptyCluster());

        //TODO: find empty cluster to store file header

        FileHeaderCluster cluster = new FileHeaderCluster(disk);
        disk.setCluster(emptyCluster.getAddress(), cluster);
        cluster.setNibble(0,1);
        cluster.setByte(1, 0);
        cluster.setFileName(filename);
        FileDataCluster[] dataClusters = cluster.setData(data);
        int currentDataClusterIndex = dataClusters.length - 1;
        while(currentDataClusterIndex >= 0)
        {
            //TODO: write data clusters to disk.
            currentDataClusterIndex--;
        }
    }

}
