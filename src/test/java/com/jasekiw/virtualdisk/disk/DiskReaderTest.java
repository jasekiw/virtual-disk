package com.jasekiw.virtualdisk.disk;

import com.jasekiw.virtualdisk.AppTestCase;
import com.jasekiw.virtualdisk.disk.exceptions.DiskFileNotFound;
import com.jasekiw.virtualdisk.disk.exceptions.IncorrectClusterSizeException;
import com.jasekiw.virtualdisk.disk.writing.exceptions.InsufficientDiskSpace;

import java.io.UnsupportedEncodingException;

public class DiskReaderTest extends AppTestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DiskReaderTest(String testName)
    {
        super(testName);
    }

    public void test_fileSize() {
        String data = "hello";
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
        } catch (InsufficientDiskSpace e) {
            fail();
        }
        try {
            assertEquals(1,disk.getReader().getFileSize("someFile.txt"));
        } catch (DiskFileNotFound diskFileNotFound) {
            fail("File should be found");
        }

    }
}
