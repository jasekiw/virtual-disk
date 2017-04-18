package com.jasekiw.virtualdisk.disk.clusters;

import com.jasekiw.virtualdisk.disk.Disk;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
        if(cluster == null)
            return null;

        return (FileHeaderCluster)cluster;
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
        if(currentByteIndex < size() - 1)
            setNibble(currentByteIndex, 0);
        else
            setByte(size() - 2, 0);

    }

    public FileDataCluster[] setData(String data)
    {
        byte[] dataBytes;
        try {
            dataBytes = data.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new FileDataCluster[0];
        }
        int currentByteIndex = 5;
        while(getNibble(currentByteIndex) != 0 )
            currentByteIndex++;
        currentByteIndex++;
        if(currentByteIndex >= size() - 1)
            return makeFileDataClusters(dataBytes);
        else
        {
            int currentFileDataPos = 0;
            while(currentFileDataPos < dataBytes.length && currentByteIndex < size() - 1)
            {
                setByte(currentByteIndex, dataBytes[currentFileDataPos]);
                currentFileDataPos++;
                currentByteIndex += 2;
            }
            byte[] remainingBytes = new byte[dataBytes.length - currentFileDataPos];
            System.arraycopy(dataBytes,currentFileDataPos, remainingBytes, 0, remainingBytes.length);
            return makeFileDataClusters(remainingBytes);
        }

    }



    protected FileDataCluster[] makeFileDataClusters(byte[] data)
    {
        int dataClusterMaxSize = (size() - 4);
        int amountOfClustersToMake = data.length / dataClusterMaxSize;
        FileDataCluster[] fileDataClusters = new FileDataCluster[amountOfClustersToMake];
        int currentCluster = 0;
        while(currentCluster < amountOfClustersToMake)
        {
            byte[] clusterBytes = new byte[dataClusterMaxSize];
            System.arraycopy(data, currentCluster * amountOfClustersToMake, clusterBytes, 0, dataClusterMaxSize);
            fileDataClusters[currentCluster] = makeFileDataCluster(clusterBytes);
            currentCluster++;
        }
        return fileDataClusters;
    }

    protected FileDataCluster makeFileDataCluster(byte[] data) {
        FileDataCluster cluster = new FileDataCluster(new byte[size()], disk);
        cluster.setData(data);
        return cluster;
    }

    public String getFileName() {
        int currentByteIndex = 5;
        boolean nullFound = false;
        ArrayList<Byte> fileNameBytes = new ArrayList<>();
        while(!nullFound && currentByteIndex < this.size())
        {
            byte currentByte = 0;
            try
            {
                currentByte = this.getByte(currentByteIndex);
            }
            catch(Exception e)
            {}

            if(currentByte == 0)
                nullFound = true;
            else
                fileNameBytes.add(currentByte);
            currentByteIndex += 2;
        }
        byte[] fileNamePrimitiveBytes = new byte[fileNameBytes.size()];
        for(int i =0; i < fileNameBytes.size(); i++)
            fileNamePrimitiveBytes[i] = fileNameBytes.get(i);
        try {
            return new String(fileNamePrimitiveBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
