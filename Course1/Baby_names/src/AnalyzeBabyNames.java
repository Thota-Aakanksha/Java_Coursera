import edu.duke.*;
import org.apache.commons.csv.CSVRecord;
import java.io.File;

public class AnalyzeBabyNames {
    public void totalBirths(FileResource fr)
    {
        int births=0;
        int girlBirths=0;
        int boyBirths=0;
        int names=0;
        int girlNames=0;
        int boyNames=0;
        for(CSVRecord rec:fr.getCSVParser(false))
        {
            int curBirths=Integer.parseInt(rec.get(2));
            births+=curBirths;
            names+=1;
            if(rec.get(1).equals("M")) {
                boyBirths += curBirths;
                boyNames+=1;
            }
            else{
                girlBirths+=curBirths;
                girlNames+=1;
            }
        }

        System.out.println("Number of births = "+ births);
        System.out.println("Number of unique names = "+ names);
        System.out.println("Number of unique girl names = "+ girlNames);
        System.out.println("Number of unique boy names = "+ boyNames);

    }
    public int getRank(int year,String name, String gender)
    {
        FileResource fr=new FileResource("us_babynames_small/yob"+year+"short.csv");
        return getRankInFile(name,gender,fr);
    }

    private int getRankInFile(String name, String gender,FileResource fr) {
        int rank=0;
        boolean flag=false;
        for(CSVRecord rec: fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender)) {
                rank+=1;
                if (rec.get(0).equals(name)){
                    flag=true;
                    break;}
            }
        }
        if(flag)
            return rank;
        return -1;
    }

    public String getName(int year,int rank,String gender)
    {
        FileResource fr=new FileResource("us_babynames_small/yob"+year+"short.csv");
        return getNameinFile(rank, gender, fr);
    }

    private String getNameinFile(int rank, String gender, FileResource fr) {
        int curRank=0;
        String ans="NO NAME";
        for(CSVRecord rec: fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender)) {
                curRank+=1;
                if (curRank== rank){
                    ans=rec.get(0);
                    break;}
            }
        }
        return ans;
    }

    public String whatIsNameInYear(String name,int yearOfBirth, int newYear,String gender)
    {
        int curRank=getRank(yearOfBirth,name,gender);
        return getName(newYear,curRank,gender);
    }
    public int yearOfHighestRank(String name,String gender)
    {
        int highestRank=-1;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int curRank=getRankInFile(name,gender,fr);
            if(highestRank==-1)
                highestRank=curRank;
            highestRank=Math.min(highestRank,curRank);
        }
        return highestRank;
    }
    public static void main(String[] args)
    {
        AnalyzeBabyNames names=new AnalyzeBabyNames();
        //System.out.println(names.getRank(2012,"Olivia","F"));
        //System.out.println(names.getName(2012,8,"M"));
        //System.out.println(names.whatIsNameInYear("Isabella",2012,2014,"F"));
        System.out.println(names.yearOfHighestRank("Mason","M"));
    }
}
