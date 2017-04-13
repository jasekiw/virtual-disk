package com.jasekiw.virtualdisk.disk.clusters;

import com.jasekiw.virtualdisk.disk.Disk;

public class FileDataCluster extends Cluster
{
    @Override
    protected ClusterType clusterType()
    {
        return ClusterType.fileData;
    }

    public FileDataCluster(byte[] cluster, Disk disk) {
        super(cluster, disk);
        cluster[0] = (byte)4;
    }

    public void setData(byte[] data)
    {
        int currentDataIndex = 0;
        int currentNibbleIndex = 3;
        while(currentDataIndex < data.length && currentNibbleIndex < size() - 1)
        {
            setByte(currentNibbleIndex, data[currentDataIndex]);
            currentDataIndex++;
            currentNibbleIndex += 2;
        }
    }
}
