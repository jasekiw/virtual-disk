package com.jasekiw.virtualdisk.disk.writing.exceptions;

import com.jasekiw.virtualdisk.disk.exceptions.DiskException;

public class InsufficientDiskSpace extends DiskException
{
    public InsufficientDiskSpace() {
        super("Insufficient Disk Space");
    }
}
