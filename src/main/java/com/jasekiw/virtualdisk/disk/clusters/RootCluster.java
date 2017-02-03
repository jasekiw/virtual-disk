package com.jasekiw.virtualdisk.disk.clusters;


import com.jasekiw.virtualdisk.disk.Disk;

public class RootCluster extends Cluster
{
    protected ClusterType clusterType = ClusterType.root;
    public RootCluster(byte[] cluster, Disk disk) {
        super(cluster, disk);
    }

    public FileHeaderCluster getFileHeaderCluster() {
        int index = (int)this.getByte(5);
        return (FileHeaderCluster) disk.getCluster(index);
    }


}
