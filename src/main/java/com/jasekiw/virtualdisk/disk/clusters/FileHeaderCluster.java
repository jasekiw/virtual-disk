package com.jasekiw.virtualdisk.disk.clusters;

import com.google.common.primitives.Bytes;
import com.jasekiw.virtualdisk.disk.Disk;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHeaderCluster extends Cluster
{
    @Override
    protected ClusterType clusterType()
    {
        return ClusterType.fileHeader;
    }

    public FileHeaderCluster(byte[] cluster, Disk disk) {
        super(cluster, disk);
        this.setNibble(0, 3);
    }
    public FileHeaderCluster(Disk disk) {
        super(disk);
        this.setNibble(0, 3);
    }

    public FileHeaderCluster getNextFileHeaderCluster() {
        int index = (int)this.getByte(1);
        Cluster cluster = null;
        if(index != 0)
            cluster = disk.getCluster(index);
        return cluster == null ? null : (FileHeaderCluster)cluster;
    }

    public FileDataCluster getNextFileDataCluster() {
        int index = (int)this.getByte(3);
        Cluster cluster = null;
        if(index != 0)
            cluster = disk.getCluster(index);
        return cluster == null ? null : (FileDataCluster) cluster;
    }

    public void setNextFileHeaderClusterAddress(int address) {
        this.setByte(1, address);
    }

    public void setFileName(String filename) {
        int currentByteIndex = 5;
        char[] filenameArr = filename.toCharArray();
        int currentFileNamePos = 0;
        while(currentFileNamePos < filenameArr.length && currentByteIndex < size() - 1)
        {
            setByte(currentByteIndex, (byte)filenameArr[currentFileNamePos]);
            currentFileNamePos++;
            currentByteIndex += 2;
        }
    }

    public FileDataCluster[] setData(byte[] data)
    {

        int currentByteIndex = getStartOfData();
        if(currentByteIndex >= size() - 1)
            return makeFileDataClusters(data);
        else
        {
            int currentFileDataPos = 0;
            while(currentFileDataPos < data.length && currentByteIndex < size() - 1)
            {
                setByte(currentByteIndex, data[currentFileDataPos]);
                currentFileDataPos++;
                currentByteIndex += 2;
            }
            byte[] remainingBytes = new byte[data.length - currentFileDataPos];
            System.arraycopy(data,currentFileDataPos, remainingBytes, 0, remainingBytes.length);
            return makeFileDataClusters(remainingBytes);
        }

    }

    public byte[] getData() {
        int currentByteIndex = getStartOfData();
        ArrayList<Byte> bytes = new ArrayList<>();
        while(currentByteIndex < size() - 1 && getByte(currentByteIndex) != 0)
        {
            bytes.add( getByte(currentByteIndex));
            currentByteIndex += 2;
        }
        FileDataCluster dataCluster = getNextFileDataCluster();
        while(dataCluster != null)
        {
            bytes.addAll(dataCluster.getDataAsList());
            dataCluster = dataCluster.getNextFileDataCluster();
        }
        return Bytes.toArray(bytes);
    }

    private int getStartOfData() {
        int currentByteIndex = 5;
        while(getByte(currentByteIndex) != 0 )
            currentByteIndex += 2;
        currentByteIndex += 2;

        return currentByteIndex;
    }

    public void setNextDataAddress(int address) {
        setByte(3, address);
    }


    protected FileDataCluster[] makeFileDataClusters(byte[] data)
    {
        int dataClusterMaxSize = (FileDataCluster.getUsableSpaceBytes(size()));
        int amountOfClustersToMake = (int)Math.ceil(data.length / dataClusterMaxSize);
        FileDataCluster[] fileDataClusters = new FileDataCluster[amountOfClustersToMake];
        int currentCluster = 0;
        while(currentCluster < amountOfClustersToMake)
        {
            byte[] clusterBytes = new byte[dataClusterMaxSize];
            System.arraycopy(data, currentCluster * dataClusterMaxSize, clusterBytes, 0, dataClusterMaxSize);
            FileDataCluster cluster = new FileDataCluster(disk);
            cluster.setData(clusterBytes);
            fileDataClusters[currentCluster] = cluster;
            currentCluster++;
        }
        return fileDataClusters;
    }


    public String getFileName() {
        int currentByteIndex = 5;
        boolean nullFound = false;
        ArrayList<Byte> fileNameBytes = new ArrayList<>();
        while(!nullFound && currentByteIndex < this.size())
        {
            byte currentByte = this.getByte(currentByteIndex);
            if(currentByte == 0)
                nullFound = true;
            else
                fileNameBytes.add(currentByte);
            currentByteIndex += 2;
        }
        try {
            return new String(Bytes.toArray(fileNameBytes), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getUsableDataSpaceBytes(int usableNibbles, int nameLength)
    {
        return ((usableNibbles / 2) - nameLength)  - 5;
    }
}
