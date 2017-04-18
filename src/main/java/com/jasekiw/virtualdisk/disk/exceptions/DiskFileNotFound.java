package com.jasekiw.virtualdisk.disk.exceptions;

public class DiskFileNotFound extends DiskException
{
    public DiskFileNotFound(String file)
    {
        super(file + " is not found");
    }
}
