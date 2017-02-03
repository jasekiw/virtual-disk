package com.jasekiw.virtualdisk.disk.clusters;

import com.jasekiw.virtualdisk.disk.Disk;

public class EmptyCluster extends Cluster
{
    protected ClusterType clusterType = ClusterType.empty;
    public EmptyCluster(byte[] cluster, Disk disk) {
        super(cluster, disk);
    }
}
