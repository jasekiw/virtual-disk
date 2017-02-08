package com.jasekiw.virtualdisk.disk.clusters;

import com.jasekiw.virtualdisk.disk.Disk;

public class DamagedCluster extends Cluster
{
    @Override
    protected ClusterType clusterType()
    {
        return ClusterType.damaged;
    }

    public DamagedCluster(byte[] cluster, Disk disk) {
        super(cluster, disk);
    }
}
