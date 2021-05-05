
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    public void testUniqueIP()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println(la.countUniqueIPs());
    }
    public void testprintAllHigherThanNum()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAllHigherThanNum(200);
    }
    public void testUniqueIPVisitsOnDay()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("weblog-short_log");
        List<String> uniqueVisits=la.uniqueIPVisitsOnDay("Sep 14");
        System.out.println(uniqueVisits.size());

    }
    public void testcountVisitsPerIP()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> countVisits=la.countVisitsPerIP();
        System.out.println(countVisits);

    }
    public void  testMostNumberVisitsByIP()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,Integer> countIPs=la.countVisitsPerIP();
        int maxVisits=la.mostNumberVisitsByIP(countIPs);
        System.out.println(maxVisits);

    }
    public void  testiPsMostVisits()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,Integer> countIPs=la.countVisitsPerIP();
        ArrayList<String> maxVisitsIPs=la.iPsMostVists(countIPs);
        System.out.println(maxVisitsIPs);

    }
    public void testiPsForDays()
    {
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> iPDaysMapping=la.iPsForDays();
        System.out.println(iPDaysMapping);

    }
    public static void main(String[] args) {
        Tester t=new Tester();
        t.testiPsForDays();
    }
}
