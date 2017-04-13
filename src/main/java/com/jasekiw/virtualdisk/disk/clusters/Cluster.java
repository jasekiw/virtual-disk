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
    public Cluster(byte[] cluster, Disk disk) {
        this.cluster = cluster;
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
    public byte[] getNibbleByteArray(int index)
    {
        return new byte[] { cluster[index], cluster[index + 1]};
    }

    public byte getByte(int index) {
        return hexToByte.getDecimalByteFromHexBytes(getNibbleByteArray(index));
    }


    public void setNibble(int index, byte nibble)
    {
        cluster[index] = nibble;
    }
    public void setByte(int index, byte[] data)
    {
        cluster[index] = data[0];
        cluster[index + 1] = data[1];
    }

    public void setByte(int index, byte data)
    {
        setByte(index, byteToHex.byteTohexArray(data));
    }
    public byte[] getBytes() {
        return cluster;
    }

    public int size() {
        return cluster.length;
    }
}
