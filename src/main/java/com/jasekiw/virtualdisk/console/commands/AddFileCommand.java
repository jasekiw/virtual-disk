package com.jasekiw.virtualdisk.console.commands;

import com.jasekiw.console.Command;
import com.jasekiw.console.exceptions.ConsoleException;
import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.clusters.FileDataCluster;
import com.jasekiw.virtualdisk.disk.exceptions.IncorrectClusterSizeException;
import com.jasekiw.virtualdisk.disk.writing.exceptions.InsufficientDiskSpace;

import java.io.*;

public class AddFileCommand extends Command
{
    @Override
    public String run() throws ConsoleException
    {
        if(this.getOption(0) == null)
            return "<filename> must be given";
        File file = new File(this.getOption(0));
        if(!file.exists() && !file.isDirectory())
            throw new ConsoleException("The given file does not exist");
        Disk disk = null;
        try {  disk = new Disk(32,64); } catch (IncorrectClusterSizeException e) {}
        disk.formatDisk("VirtualDisk");
        String data = readFile(file.getAbsolutePath());
        if(data == null)  return null;
        System.out.println("Formatted Disk:");
        System.out.println(disk.toString());
        try {
            byte[] dataBytes = data.getBytes("UTF-8");
            disk.getWriter().writeFile(file.getName(), dataBytes);
        } catch (InsufficientDiskSpace insufficientDiskSpace) {
            throw new ConsoleException("There is not enough disk space for that file");
        }
        catch (UnsupportedEncodingException e) {
            throw new ConsoleException("The file contents does not adhere to UTF-8 encoding");
        }
        System.out.println("Writing file to disk:");
        return disk.toString();
    }

    @Override
    public String getSignature()
    {
        return "-add <filename>";
    }

    @Override
    public String getDescription()
    {
        return "Adds a file to the disk";
    }

    protected String readFile(String fileName) {
        String data = "";
        try {
            String line = null;
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                data += line + "\n";
            }

            // Always close files.
            bufferedReader.close();
            return data;
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        return null;
    }
}
