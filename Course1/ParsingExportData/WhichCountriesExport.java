import edu.duke.*;
import org.apache.commons.csv.*;
public class WhichCountriesExport {

    public void listExporters(CSVParser parser, String exportOfInterest) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }

    public void tester()
    {
        FileResource fr = new FileResource();

        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser,"Germany"));

        parser=fr.getCSVParser();
        listExportersTwoProducts(parser,"gold","diamonds");

        parser=fr.getCSVParser();
        System.out.println("Number of exporters "+numberOfExporters(parser,"gold"));

        parser=fr.getCSVParser();
        bigExporters(parser,"$999,999,999");
    }

    public String countryInfo(CSVParser parser,String country)
    {
        String details="NOT FOUND";
        for(CSVRecord rec:parser)
        {
            if(rec.get(0).equals((country))) {
                details = rec.get(0) + ": " + rec.get(1) + ": " + rec.get(2);
                return details;
            }
        }
        return details;
    }

    public void listExportersTwoProducts(CSVParser parser,String export1,String export2)
    {
        String exports;
        for(CSVRecord rec:parser)
        {
            exports=rec.get(1);
            if(exports.contains(export1) && exports.contains(export2))
            {
                System.out.println(rec.get(0));
            }
        }
    }

    public int numberOfExporters(CSVParser parser,String exportItem)
    {
        int count=0;
        for(CSVRecord rec:parser) {
            if (rec.get(1).contains(exportItem))
                count++;
        }
        return count;
    }

    public void  bigExporters(CSVParser parser, String amount)
    {
        for(CSVRecord rec:parser)
        {
            if(rec.get(2).length()>amount.length())
                System.out.println(rec.get(0)+" "+rec.get(2));
        }
    }

    public static void main(String[] args)
    {
        WhichCountriesExport exp=new WhichCountriesExport();
        exp.tester();
    }
}
