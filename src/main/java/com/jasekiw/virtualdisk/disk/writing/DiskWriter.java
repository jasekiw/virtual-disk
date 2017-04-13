package com.jasekiw.virtualdisk.disk.writing;

import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.clusters.FileDataCluster;
import com.jasekiw.virtualdisk.disk.clusters.FileHeaderCluster;

public class DiskWriter
{
    public void writeFile(Disk disk, String filename, String data)
    {
        //TODO: find empty cluster to store file header
        FileHeaderCluster cluster = (FileHeaderCluster) disk.convertCluster(1, FileHeaderCluster.class);
        cluster.setNibble(0, (byte) 1);
        cluster.setByte(1, new byte[]{(byte) 0, (byte) 0});
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
