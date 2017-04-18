package com.jasekiw.virtualdisk.console.commands;

import com.jasekiw.console.Command;
import com.jasekiw.console.exceptions.ConsoleException;
import com.jasekiw.console.exceptions.ParameterException;
import com.jasekiw.virtualdisk.disk.Disk;
import com.jasekiw.virtualdisk.disk.exceptions.IncorrectClusterSizeException;


public class FormatCommand extends Command
{

    @Override
    public String getSignature() { return "-format"; }

    @Override
    public String getDescription()
    {
        return "formats the disk and displays the output.";
    }

    @Override
    public String run() throws ConsoleException
    {
        Disk disk = null;
        try {
            disk = new Disk(this.getInt(0),this.getInt(1));
        } catch (IncorrectClusterSizeException e) {
            throw new ParameterException("The cluster size must be divisible by 2");
        }
        disk.formatDisk(this.getOption(0));
        return disk.toString();
    }
}
