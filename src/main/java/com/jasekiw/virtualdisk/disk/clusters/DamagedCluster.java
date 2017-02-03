package com.jasekiw.virtualdisk.disk.clusters;

import com.jasekiw.virtualdisk.disk.Disk;

public class DamagedCluster extends Cluster
{
    private ClusterType clusterType = ClusterType.damaged;
    public DamagedCluster(byte[] cluster, Disk disk) {
        super(cluster, disk);
    }
}
