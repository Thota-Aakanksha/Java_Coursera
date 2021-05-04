import edu.duke.*;
public class Part31 {
    public int findStopCodon(String dna,int startIndex,String stopCodon)
    {
        int endIndex=dna.indexOf(stopCodon,startIndex+3);
        while(endIndex!=-1) {
            if ((endIndex - startIndex-3) % 3 == 0)
                return endIndex;
            endIndex=dna.indexOf(stopCodon,endIndex+1);
        }
        return dna.length();
    }
    public String findGene(String dna)
    {
        int startIndex=dna.indexOf("ATG");
        if(startIndex==-1)
            return "";

        int taaIndex=findStopCodon(dna,startIndex,"TAA");
        int tagIndex=findStopCodon(dna,startIndex,"TAG");
        int tgaIndex=findStopCodon(dna,startIndex,"TGA");

        int minIndex=Math.min(taaIndex,tagIndex);
        minIndex= Math.min(minIndex,tgaIndex);

        if(minIndex==dna.length())
            return "";
        return dna.substring(startIndex,minIndex+3);
    }
    public StorageResource getAllGenes(String dna)
    {
        StorageResource sr=new StorageResource();
        int index=0;
        while(true)
        {
            String gene=findGene(dna.substring((index)));
            if(gene.isEmpty())
                break;
            sr.add(gene);
            index=dna.indexOf(gene)+gene.length();
        }
        return sr;
    }
    public void testGetAllGenes()
    {
     StorageResource sr=getAllGenes("ATGGTACTATAAATGCGTTAG");
     for(String s:sr.data())
     {System.out.println(s);}
    }

    public static void main(String[] args)
    {
        Part31 part=new Part31();
        part.testGetAllGenes();
    }
}
