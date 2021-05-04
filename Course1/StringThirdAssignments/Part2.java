public class Part32 {
    public double countCGRatio(String dna)
    {   int countCG=0;
        int total=dna.length();
        char[] charArray=dna.toCharArray();
        for(char ch:charArray)
        {
            if(ch=='C'||ch=='G')
                countCG+=1;
        }
        return countCG/(double)total;
    }
    public void testCountCGRatio()
    {
        System.out.println(countCGRatio("ATGCCATAG"));
    }

    public static void main(String[] args)
    {
        Part32 part=new Part32();
        part.testCountCGRatio();
    }
}
