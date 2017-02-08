package com.jasekiw.virtualdisk.disk;

import com.jasekiw.virtualdisk.disk.clusters.Cluster;
import com.jasekiw.virtualdisk.disk.clusters.RootCluster;
import com.jasekiw.virtualdisk.disk.reading.DiskReader;
import com.jasekiw.virtualdisk.disk.reading.usage.DiskUsageResult;

public class Disk
{
    Cluster[] disk;
    int nibbleSize;
    protected DiskFormatter formatter;
    protected DiskReader reader;

    public Disk(int clusters, int nibbles) {
        this.nibbleSize = nibbles;
        disk = new Cluster[clusters];
        formatter = new DiskFormatter();
        reader = new DiskReader();
    }

    public void formatDisk(String volumeName) {
        formatter.formatDisk(this, volumeName);
    }


    public Cluster getCluster(int index)
    {
        return disk[index];
    }
    public RootCluster getRootCluster() { return (RootCluster) disk[0]; }

    public void setCluster(int index, Cluster cluster) {disk[index] = cluster; }
    public int clusterLength() {
        return disk.length;
    }
    public int getClusterNibbleSize() {  return nibbleSize; }

    public String[] directoryListing() {
        return reader.directoryListing(this);
    }
    public boolean fileExists(String filename) {
        return reader.fileExists(this, filename);
    }

    public DiskUsageResult getDiskUsage() { return reader.getDiskUsage(this); }

    @Override
    public String toString()
    {
        return reader.outputDisk(this);
    }
}
