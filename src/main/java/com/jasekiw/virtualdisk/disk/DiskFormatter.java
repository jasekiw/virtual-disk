package com.jasekiw.virtualdisk.disk;

import com.jasekiw.virtualdisk.convertors.ByteToHex;
import com.jasekiw.virtualdisk.disk.clusters.EmptyCluster;
import com.jasekiw.virtualdisk.disk.clusters.RootCluster;

public class DiskFormatter
{
    protected ByteToHex hexConvertor;
    DiskFormatter() {
        hexConvertor = new ByteToHex();
    }
    public void formatDisk(Disk disk, String volumeName)
    {
        writeRootCluster(disk, volumeName);
        writeEmptyHeaders(disk);
    }

    protected void writeRootCluster(Disk disk, String volumeName)
    {
        disk.setCluster(0, new RootCluster(new byte[disk.getClusterNibbleSize()], disk));
        disk.getRootCluster().setNibble(2, (byte)1);
        byte[] nameBytes = volumeName.getBytes();
        int diskOffset = 7;
        if((nameBytes.length * 2) > (disk.getCluster(0).size() - 9))
            System.out.println("The Volume name is too large for a " + disk.getCluster(0).size() + " nibble cluster size. I has been concatenated");

        for(int i = 0; i < nameBytes.length && (diskOffset + (i * 2)) < disk.getCluster(0).size() - 1 ; i++)
            disk.getRootCluster().setByte(diskOffset + (i * 2),hexConvertor.byteTohexArray(nameBytes[i]));


    }

    protected void writeEmptyHeaders(Disk disk)
    {
        for(int i = 1; i < disk.clusterLength(); i++)
        {
            disk.setCluster(i, new EmptyCluster(new byte[disk.getClusterNibbleSize()], disk));
            disk.getCluster(i).setNibble(0, (byte)1);
            if( (i + 1) < (disk.clusterLength()))
            {
                byte[] nextAddress = hexConvertor.byteTohexArray((byte)(i + 1));
                disk.getCluster(i).setByte(1, nextAddress);
            }

        }
    }
}
