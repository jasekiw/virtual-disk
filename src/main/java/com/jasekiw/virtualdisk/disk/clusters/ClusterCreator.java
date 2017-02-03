package com.jasekiw.virtualdisk.disk.clusters;

import com.jasekiw.virtualdisk.disk.Disk;

/**
 * @author Jason Gallavin
 * @created 2/2/2017.
 */
public class ClusterCreator
{
    public Cluster getCluster(byte[] cluster, Disk disk)
    {
        switch ((int)cluster[0])
        {
            case 0: return new RootCluster(cluster, disk);
            case 1: return new EmptyCluster(cluster, disk);
            case 2: return new DamagedCluster(cluster, disk);
            case 3: return new FileHeaderCluster(cluster, disk);
            case 4: return new FileDataCluster(cluster, disk);
            default:return new EmptyCluster(cluster, disk);
        }
    }
}
