package com.jasekiw.virtualdisk.console.commands;

import com.jasekiw.console.Command;
import com.jasekiw.console.exceptions.ConsoleException;
import com.jasekiw.console.exceptions.ParameterException;
import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.exceptions.DiskFileNotFound;
import com.jasekiw.virtualdisk.disk.exceptions.IncorrectClusterSizeException;
import com.jasekiw.virtualdisk.disk.writing.exceptions.InsufficientDiskSpace;

import java.io.UnsupportedEncodingException;

public class FileSizeCommand extends Command
{
    @Override
    public String run() throws ConsoleException
    {
        String data = "The quick brown fox jumps over the lazy dog. The quick brown dog jumps over the lazy fox.";
        byte[] dataBytes = new byte[0];
        try {
            dataBytes = data.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) { }
        String filename = "someFile.txt";
        Disk disk = null;
        try {  disk = new Disk(32,64); } catch (IncorrectClusterSizeException e) {}
        disk.formatDisk("VirtualDisk");
        try { disk.getWriter().writeFile(filename, dataBytes); } catch (InsufficientDiskSpace e) {}
        try {
            System.out.println("Disk:");
            System.out.println(disk.toString());
            int clustersUsed = disk.getReader().getFileSize(this.getOption(0));
            return "The file takes up " + clustersUsed + " cluster(s) and " + (disk.getClusterNibbleSize() / 2) * clustersUsed + " bytes";
        } catch (DiskFileNotFound diskFileNotFound) {
            throw new ParameterException("The given file is not found");
        }
    }

    @Override
    public String getSignature()
    {
        return "-filesize <filename>";
    }

    @Override
    public String getDescription()
    {
        return "Gets the amount of clusters and bytes that a file takes up";
    }
}
