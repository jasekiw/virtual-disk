package com.jasekiw.virtualdisk.disk;

import com.jasekiw.virtualdisk.disk.clusters.Cluster;
import com.jasekiw.virtualdisk.disk.clusters.RootCluster;
import com.jasekiw.virtualdisk.disk.reading.DiskReader;
import com.jasekiw.virtualdisk.disk.reading.usage.DiskUsageResult;
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

    public Disk(int clusters, int nibbles) {
        this.nibbleSize = nibbles;
        disk = new Cluster[clusters];
        formatter = new DiskFormatter();
        reader = new DiskReader();
        writer = new DiskWriter();
    }

    public void formatDisk(String volumeName) {
        formatter.formatDisk(this, volumeName);
    }


    public Cluster getCluster(int index)
    {
        return disk[index];
    }

    public Cluster convertCluster(int index, Class type)
    {
        Cluster cluster = getCluster(index);
        try {
            Constructor constructor = type.getConstructor(byte[].class, Disk.class);
            Cluster newCluster = (Cluster)constructor.newInstance(cluster.getBytes(), this);
            setCluster(index,newCluster);
            return newCluster;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            return null;
        }

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

    public void writeFile(String filename, String data) {
        this.writer.writeFile(this, filename, data);
    }
    @Override
    public String toString()
    {
        return reader.outputDisk(this);
    }
}
