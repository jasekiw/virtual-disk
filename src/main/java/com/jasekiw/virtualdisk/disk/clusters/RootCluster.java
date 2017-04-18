package com.jasekiw.virtualdisk.disk.clusters;


import com.jasekiw.virtualdisk.disk.Disk;

public class RootCluster extends Cluster
{
    @Override
    protected ClusterType clusterType()
    {
        return ClusterType.root;
    }

    public RootCluster(byte[] cluster, Disk disk) {
        super(cluster, disk);
    }

    public FileHeaderCluster getFileHeaderCluster() {
        int index = (int)this.getByte(5);
        return (FileHeaderCluster) disk.getCluster(index);
    }

    public EmptyCluster getEmptyCluster() {
        int index = (int)this.getByte(1);
        Cluster cluster = disk.getCluster(index);
        if(cluster == null)
            return null;
        return (EmptyCluster)cluster;
    }

    public void setEmptyCluster(EmptyCluster cluster) {
        if(cluster == null)
            setByte(1, 0);
        else
            setByte(1, cluster.address);
    }


}
