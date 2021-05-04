public class Part1
{
    public String findSimpleGene(String dna)
    {
        int startIndex=dna.indexOf("ATG");
        if(startIndex==-1)
            return "";
        int endIndex=dna.indexOf("TAA",startIndex+3);
        if(endIndex==-1)
            return "";
        while(endIndex!=-1) {
            if ((endIndex - startIndex-3) % 3 == 0)
                return dna.substring(startIndex, endIndex + 3);
            endIndex=dna.indexOf("TAA",endIndex+1);
        }
        return "";
    }
    public void testSimpleGene()
    {
        test("TGACAATAA");
        test("CATGAAATG");
        test("GTAGAT");
        test("AGGATGAATGTATAA");
        test("GTCATGAAGTATAAGTAA");

    }

    private void test(String dna) {
        System.out.println("DNA string is "+ dna);
        System.out.println("Gene is "+findSimpleGene(dna));
    }

    public static void main(String[] args)
    {
        Part1 part=new Part1();
        part.testSimpleGene();
    }
}