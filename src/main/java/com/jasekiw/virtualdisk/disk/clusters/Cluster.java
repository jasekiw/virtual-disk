package com.jasekiw.virtualdisk.disk.clusters;

import com.jasekiw.virtualdisk.convertors.ByteToHex;
import com.jasekiw.virtualdisk.convertors.HexToByte;
import com.jasekiw.virtualdisk.disk.Disk;

public abstract class Cluster
{
    protected abstract ClusterType clusterType();
    private byte[] cluster;
    protected Disk disk;
    protected HexToByte hexToByte;
    protected ByteToHex byteToHex;
    protected int address;
    public Cluster(byte[] cluster, Disk disk) {
        this.cluster = cluster;
        this.disk = disk;
        hexToByte = new HexToByte();
        this.byteToHex = new ByteToHex();
    }

    public Cluster(Disk disk) {
        this.cluster = new byte[disk.getClusterNibbleSize()];
        this.disk = disk;
        hexToByte = new HexToByte();
        this.byteToHex = new ByteToHex();
    }
    public ClusterType getType() {
       return clusterType();
    }

    public byte getNibble(int index)
    {
        return cluster[index];
    }
    public byte[] getNibbleArray(int index)
    {
        return new byte[] { cluster[index], cluster[index + 1]};
    }

    public byte getByte(int index) {
        return hexToByte.getDecimalByteFromHexNibbles(getNibbleArray(index));
    }


    public void setNibble(int index, byte nibble)
    {
        cluster[index] = nibble;
    }
    public void setNibble(int index, int nibble)
    {
        cluster[index] = (byte)nibble;
    }
    public void setByte(int index, byte[] data)
    {
        cluster[index] = data[0];
        cluster[index + 1] = data[1];
    }



    public void setByte(int index, byte data)
    {
        setByte(index, byteToHex.byteToHexArray(data));
    }

    public void setByte(int index, int data)
    {
        setByte(index, byteToHex.byteToHexArray(data));
    }
    public byte[] getBytes() {
        return cluster;
    }


    public int getAddress() {
        return address;
    }

    public void setAddress(int address)
    {
        this.address = address;
    }

    public int size() {
        return cluster.length;
    }
}
