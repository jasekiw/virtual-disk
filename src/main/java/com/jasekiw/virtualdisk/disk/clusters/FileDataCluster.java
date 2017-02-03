package com.jasekiw.virtualdisk.disk.clusters;

import com.jasekiw.virtualdisk.disk.Disk;

public class FileDataCluster extends Cluster
{
    protected ClusterType clusterType = ClusterType.fileData;
    public FileDataCluster(byte[] cluster, Disk disk) {
        super(cluster, disk);
    }
}
