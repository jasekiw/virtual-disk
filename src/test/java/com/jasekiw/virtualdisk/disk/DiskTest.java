package com.jasekiw.virtualdisk.disk;

import com.jasekiw.virtualdisk.AppTestCase;
import com.jasekiw.virtualdisk.disk.clusters.EmptyCluster;
import com.jasekiw.virtualdisk.disk.exceptions.IncorrectClusterSizeException;

public class DiskTest extends AppTestCase
{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DiskTest(String testName)
    {
        super(testName);
    }

    public void test_format() {
        Disk disk = null;
        try {
            disk = new Disk(32,64);
        } catch (IncorrectClusterSizeException e) {
            fail();
        }
        disk.formatDisk("test");
        int emptyClusters = 1;
        EmptyCluster cluster = disk.getRootCluster().getEmptyCluster();
        assertNotNull(cluster);
        while(cluster != null)
        {
            cluster = cluster.getNextEmptyCluster();
            emptyClusters++;
        }
        assertEquals(32, emptyClusters);
    }

    public void test_toString() {
        Disk disk = null;
        try {
            disk = new Disk(32,64);
        } catch (IncorrectClusterSizeException e) {
            fail();
        }
        disk.formatDisk("test");
        assertNotNull(disk.toString());
    }
}
