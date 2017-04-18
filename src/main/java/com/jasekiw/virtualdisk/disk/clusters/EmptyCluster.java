package com.jasekiw.virtualdisk.disk.clusters;

import com.jasekiw.virtualdisk.disk.Disk;

public class EmptyCluster extends Cluster
{
    @Override
    protected ClusterType clusterType()
    {
        return ClusterType.empty;
    }

    public EmptyCluster(byte[] cluster, Disk disk) {
        super(cluster, disk);
    }

    public EmptyCluster getNextEmptyCluster() {
        int index = (int)this.getByte(1);
        Cluster cluster = disk.getCluster(index);
        if(cluster == null)
            return null;
        return (EmptyCluster)cluster;
    }


    public void setNextEmptyCluster(EmptyCluster cluster) {
        this.setByte(1 ,cluster.address);
    }

    public void setNextEmptyClusterAddress(int address)
    {
        this.setByte(1, address);
    }

}
