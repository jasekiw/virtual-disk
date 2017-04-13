package com.jasekiw.virtualdisk.console.commands;

import com.jasekiw.console.Command;
import com.jasekiw.virtualdisk.disk.Disk;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class AddFileCommand extends Command
{
    @Override
    public String run() throws Exception
    {
        if(this.getOption(0) == null)
            return "<filename> must be given";
        File file = new File(this.getOption(0));
        if(!file.exists() && !file.isDirectory())
            throw new FileNotFoundException("The given file does not exist");
        Disk disk = new Disk(32,64);
        disk.formatDisk("VirtualDisk");
        String data = readFile(file.getAbsolutePath());
        if(data == null)  return null;
        System.out.println("Formatted Disk:");
        System.out.println(disk.toString());
        disk.writeFile(file.getName(), data);
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
