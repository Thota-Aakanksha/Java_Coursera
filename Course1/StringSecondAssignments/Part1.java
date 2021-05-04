public class Part1 {
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
    public void printAllGenes(String dna)
    {
        int index=0;
        while(true)
        {
            String gene=findGene(dna.substring((index)));
            if(gene.isEmpty())
                break;
            System.out.println("Genes are " + gene);
            index=dna.indexOf(gene)+gene.length();
        }
    }
    public void testFindStopCodon()
    {
        System.out.println(findStopCodon("ATGGTATAA",0,"TAA"));
    }
    public void testFindGene() {
        System.out.println("Gene is "+findGene("CATGAAATG"));
        System.out.println("Gene is "+findGene("TGACAATAA"));
        System.out.println("Gene is "+findGene("AGGATGAATGTATAA"));
        System.out.println("Gene is "+findGene("GTCATGAAGTATAAGTAA"));
    }
    public static void main(String[] args)
    {
        Part1 part=new Part1();
        //part.testFindGene();
        part.printAllGenes("ATGGCTTAAGTAATGATGAAGTAG");
    }
}
