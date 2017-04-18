package com.jasekiw.virtualdisk.disk.exceptions;

public class IncorrectClusterSizeException extends DiskException
{
    public IncorrectClusterSizeException() {
        super("The cluster size must be divisible by 2");
    }
}
