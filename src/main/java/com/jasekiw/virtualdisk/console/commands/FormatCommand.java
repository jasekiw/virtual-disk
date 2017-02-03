package com.jasekiw.virtualdisk.console.commands;

import com.jasekiw.virtualdisk.disk.Disk;

/**
 * @author Jason Gallavin
 * @created 2/3/2017.
 */
public class FormatCommand extends Command
{

    @Override
    public String getSignature() { return "-format"; }

    @Override
    public String run()
    {
        Disk disk = new Disk(Integer.parseInt(this.getOption(1)),Integer.parseInt(this.getOption(2)));
        disk.formatDisk(this.getOption(0));
        return disk.toString();
    }
}
