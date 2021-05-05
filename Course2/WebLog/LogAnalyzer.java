
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records=new ArrayList<>();
     }
        
     public void readFile(String filename) {
         FileResource fr=new FileResource(filename);
         for(String line:fr.lines())
            records.add(WebLogParser.parseEntry(line));
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public int countUniqueIPs()
     {
         HashMap<String,Integer> countIPs=countVisitsPerIP();
         return countIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry rec : records) {
            if(rec.getStatusCode()>num)
                System.out.println(rec);
         }
     }
     public ArrayList<String> uniqueIPVisitsOnDay(String someday)
     {
         ArrayList<String> result =new ArrayList<>();
         for (LogEntry rec : records) {
             String date=rec.getAccessTime().toString();
             String ipAddress=rec.getIpAddress();
             if(date.contains(someday)) {
                 if (!result.contains(ipAddress))
                     result.add(ipAddress);
             }
         }
         return result;
     }
    public HashMap<String,Integer> countVisitsPerIP()
    {
        HashMap<String,Integer> countIPs=new HashMap<>();
        for(LogEntry rec:records)
        {
            String ipAddr=rec.getIpAddress();
            if(! countIPs.containsKey(ipAddr))
                countIPs.put(ipAddr,1);
            else
                countIPs.put(ipAddr,countIPs.get(ipAddr)+1);
        }
        return countIPs;
    }
    public int mostNumberVisitsByIP(HashMap<String,Integer> countIPs)
    {
        int maxVisits=0;
        int currVisits=0;
        for(String ipAddr: countIPs.keySet())
        {
            currVisits=countIPs.get(ipAddr);
            if(currVisits>maxVisits)
                maxVisits=currVisits;
        }
        return maxVisits;
    }
    public ArrayList<String> iPsMostVists(HashMap<String,Integer> countIPs)
    {
        int maxVisits=mostNumberVisitsByIP(countIPs);
        ArrayList<String> mostVisitedIPs=new ArrayList<>();
        int currVisits=0;
        for(String ipAddr: countIPs.keySet())
        {
            currVisits=countIPs.get(ipAddr);
            if(currVisits==maxVisits)
                mostVisitedIPs.add(ipAddr);
        }
        return mostVisitedIPs;
    }
    public HashMap<String,ArrayList<String>> iPsForDays()
    {
        HashMap<String,ArrayList<String>> result=new HashMap<>();
        for(LogEntry rec:records)
        {
            String date=rec.getAccessTime().toString().substring(4,10);
            String ipAddress=rec.getIpAddress();
            ArrayList<String> ips;
            if(! result.containsKey(date))
                ips=new ArrayList<>();
            else
                ips=result.get(date);
            ips.add(ipAddress);
            result.put(date,ips);
        }
        return result;
    }
}
