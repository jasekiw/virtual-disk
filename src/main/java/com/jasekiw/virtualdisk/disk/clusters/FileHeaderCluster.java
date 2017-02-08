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
    }


    public FileHeaderCluster getNextFileHeaderCluster() {
        int index = (int)this.getByte(1);
        Cluster cluster = null;
        if(index != 0)
            cluster = disk.getCluster(index);
        if(cluster == null)
            return null;
        else
        return (FileHeaderCluster)cluster;
    }

    public String getFileName() {
        int currentByteIndex = 5;
        boolean nullFound = false;
        ArrayList<Byte> fileNameBytes = new ArrayList<Byte>();
        while(!nullFound && currentByteIndex < this.size())
        {
            byte currentByte = 0;
            try
            {
                currentByte = this.getByte(currentByteIndex);
            }
            catch(Exception e)
            {
                String test = "";
            }

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
