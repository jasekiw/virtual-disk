package com.jasekiw.virtualdisk.disk.clusters;

import com.google.common.primitives.Bytes;
import com.jasekiw.virtualdisk.disk.Disk;

import java.util.ArrayList;

public class FileDataCluster extends Cluster
{

    @Override
    protected ClusterType clusterType()
    {
        return ClusterType.fileData;
    }

    public FileDataCluster(byte[] cluster, Disk disk) {
        super(cluster, disk);
        setNibble(0, 4);
    }

    public FileDataCluster(Disk disk) {
        super(disk);
        setNibble(0, 4);
    }

    public void setData(byte[] data)
    {
        int currentDataIndex = 0;
        int currentNibbleIndex = 3;
        while(currentDataIndex < data.length && currentNibbleIndex < size() - 1)
        {
            setByte(currentNibbleIndex, data[currentDataIndex]);
            currentDataIndex++;
            currentNibbleIndex += 2;
        }
    }

    public void setNextDataAddress(int address) {
        setByte(1, address);
    }

    public ArrayList<Byte> getDataAsList() {
        int currentByteIndex = 3;
        ArrayList<Byte> bytes = new ArrayList<>();
        while(currentByteIndex < size() - 1 && getByte(currentByteIndex) != 0)
        {
            bytes.add( getByte(currentByteIndex));
            currentByteIndex += 2;
        }
        return bytes;
    }

    public FileDataCluster getNextFileDataCluster() {
        int index = (int)this.getByte(1);
        Cluster cluster = null;
        if(index != 0)
            cluster = disk.getCluster(index);
        return cluster == null ? null : (FileDataCluster) cluster;
    }

    public static int getUsableSpaceBytes(int usableNibbles)
    {
        return (usableNibbles / 2)  - 3;
    }
}
