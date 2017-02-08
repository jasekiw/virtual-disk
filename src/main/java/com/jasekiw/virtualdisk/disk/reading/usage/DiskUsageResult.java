package com.jasekiw.virtualdisk.disk.reading.usage;


import com.jasekiw.virtualdisk.disk.clusters.ClusterType;

import java.util.ArrayList;
import java.util.HashMap;

public class DiskUsageResult
{
    private HashMap<ClusterType, Integer> statistics = new HashMap<>();
    private boolean full;

   public DiskUsageResult() {
       for (ClusterType clusterType : ClusterType.values()) {
           statistics.put(clusterType, 0);
       }
   }
   public int getClustersCount(ClusterType clusterType)
   {
       return statistics.get(clusterType);
   }
   public void incrementClustersCount(ClusterType clusterType)
   {
       statistics.replace(clusterType, statistics.get(clusterType) + 1);
   }
   public int getClustersCountTotal()
   {
       int total = 0;
       for (ClusterType clusterType : ClusterType.values())
           total += statistics.get(clusterType);
       return total;
   }

   public int getUsedClusterPercent() {
       return (int)Math.round(((double)getUsedClustersCount() / (double)getClustersCountTotal()) * 100);
   }

   public HashMap<ClusterType, Integer> getAllClustersCount() {
       return statistics;
   }

   public void setClustersCount(ClusterType clusterType, int value)
   {
       statistics.replace(clusterType, value);
   }
   public String getClusterName(ClusterType clusterType)
   {
       if(clusterType == ClusterType.empty)
           return "Available";
       if(clusterType == ClusterType.damaged)
           return "Bad";
       return "Used";
   }
   public int getUsedClustersCount() {
       int used = 0;
       for (ClusterType clusterType : ClusterType.values()) {
           int current = statistics.get(clusterType);
           if(clusterType != ClusterType.empty)
               used += current;
       }
       return used;
   }

   public int getUsagePercent(ClusterType clusterTypeToFind)
   {
       double clusterSubject = 0;
       double total = 0;
       for (ClusterType clusterType : ClusterType.values()) {
           int current = statistics.get(clusterType);
           total += current;
           if(clusterTypeToFind == clusterType)
               clusterSubject = current;
       }
       return (int)Math.round((clusterSubject / total) * 100);
   }
}
