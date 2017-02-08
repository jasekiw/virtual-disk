package com.jasekiw.virtualdisk.console.commands;

import com.jasekiw.console.Command;
import com.jasekiw.virtualdisk.disk.Disk;


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
