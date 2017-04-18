package com.jasekiw.virtualdisk.disk;

import com.jasekiw.virtualdisk.disk.clusters.Cluster;
import com.jasekiw.virtualdisk.disk.clusters.RootCluster;
import com.jasekiw.virtualdisk.disk.exceptions.IncorrectClusterSizeException;
import com.jasekiw.virtualdisk.disk.reading.DiskReader;
import com.jasekiw.virtualdisk.disk.writing.DiskWriter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Disk
{
    Cluster[] disk;
    int nibbleSize;
    protected DiskFormatter formatter;
    protected DiskReader reader;
    protected DiskWriter writer;

    public Disk(int clusters, int nibbles) throws IncorrectClusterSizeException
    {
        if(nibbles % 2 > 0)
            throw new IncorrectClusterSizeException();
        this.nibbleSize = nibbles;
        disk = new Cluster[clusters];
        formatter = new DiskFormatter();
        reader = new DiskReader(this);
        writer = new DiskWriter(this);
    }

    public void formatDisk(String volumeName) {
        formatter.formatDisk(this, volumeName);
    }


    public Cluster getCluster(int index)
    {
        if(index >= disk.length)
            return null;
        if(index == 0)
            return null;
        return disk[index];
    }

    public RootCluster getRootCluster() { return (RootCluster) disk[0]; }

    public void setCluster(int index, Cluster cluster) {
        disk[index] = cluster;
        cluster.setAddress(index);
    }
    public int clusterLength() {
        return disk.length;
    }
    public int getClusterNibbleSize() {  return nibbleSize; }

    public DiskReader getReader() {
        return this.reader;
    }

    public DiskWriter getWriter() {
        return this.writer;
    }

    @Override
    public String toString()
    {
        return reader.outputDisk();
    }
}
