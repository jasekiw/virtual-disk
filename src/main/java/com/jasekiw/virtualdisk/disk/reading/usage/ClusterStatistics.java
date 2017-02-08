package com.jasekiw.virtualdisk.disk.reading.usage;

/**
 * @author Jason Gallavin
 * @created 2/7/2017.
 */
public class ClusterStatistics
{
    private int count;
    private double percentage;


    public int getCount()
    {
        return count;
    }

    public ClusterStatistics setCount(int count)
    {
        this.count = count;
        return this;
    }

    public double getPercentage()
    {
        return percentage;
    }

    public ClusterStatistics setPercentage(double percentage)
    {
        this.percentage = percentage;
        return this;
    }
}
