package com.jasekiw.virtualdisk.disk;

import com.jasekiw.console.exceptions.ConsoleException;
import com.jasekiw.virtualdisk.AppTestCase;
import com.jasekiw.virtualdisk.disk.exceptions.DiskFileNotFound;
import com.jasekiw.virtualdisk.disk.exceptions.IncorrectClusterSizeException;
import com.jasekiw.virtualdisk.disk.writing.exceptions.InsufficientDiskSpace;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class DiskWriterTest extends AppTestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DiskWriterTest(String testName)
    {
        super(testName);
    }

    public void test_writeFile() {

        String data = "hello world!\nThe quick brown fox jumped over the lazy dog.\nThe quick brown dog jumped over the lazy fox";
        byte[] dataBytes = new byte[0];
        try {
            dataBytes = data.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            fail();
        }
        String filename = "someFile.txt";
        Disk disk = null;
        try {  disk = new Disk(32,64); } catch (IncorrectClusterSizeException e) {}
        disk.formatDisk("VirtualDisk");
        try {
            disk.getWriter().writeFile(filename, dataBytes);
        } catch (InsufficientDiskSpace  e) {
            fail();
        }
        try {
            byte[] outputData = disk.reader.readFile(filename).data;
//            assertEquals(dataBytes, outputData);
            String output = new String(disk.reader.readFile(filename).data);
            assertEquals(data, output);
        } catch (DiskFileNotFound diskFileNotFound) {
            fail("File was not found.");
        }
    }
}
