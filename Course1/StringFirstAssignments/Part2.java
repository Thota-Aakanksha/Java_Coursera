
public class Part2 {
    public String findSimpleGene(String dna,String start, String stop)
    {
        char c=dna.charAt(0);
        if(Character.isLowerCase(c))
        {
            start=start.toLowerCase();
            stop=stop.toLowerCase();
        }
        int startIndex=dna.indexOf(start);
        if(startIndex==-1 )
            return "";
        int endIndex=dna.indexOf(stop,startIndex+3);
        if(endIndex==-1 )
            return "";
        while(endIndex!=-1) {
            if ((endIndex - startIndex) % 3 == 0)
                return dna.substring(startIndex, endIndex + 3);
            endIndex=dna.indexOf(stop,endIndex+1);
        }
        return "";
    }
    public void testSimpleGene()
    {
        test("TGACAATAA","ATG","TAA");
        test("CATGAAATG","ATG","TAA");
        test("GTAGAT","ATG","TAA");
        test("AGGATGAATGTATAA","ATG","TAA");
        test("GTCATGAAGTATAAGTAA","ATG","TAA");
        test("gtcatgaagtataagtaa","ATG","TAA");
    }

    private void test(String dna,String start, String stop) {
        System.out.println("DNA string is "+ dna);
        System.out.println("Gene is "+findSimpleGene(dna,start,stop));
    }
    public static void main(String[] args)
    {
        Part2 part=new Part2();
        part.testSimpleGene();
    }
}