public class Part3 {
    public boolean twoOccurences(String a,String b)
    {
        int index1=b.indexOf(a);
        if(index1==-1)
            return false;
        int index2=b.indexOf(a,index1+a.length());
        if(index2==-1)
            return false;
        return true;
    }
    public String lastPart(String a,String b)
    {
        int index1=b.indexOf(a);
        if(index1==-1)
            return b;
        return b.substring(index1+a.length());
    }
    public void testing()
    {
        System.out.println(twoOccurences("by","A story by Abby"));
        System.out.println(twoOccurences("an","banana"));
        System.out.println(twoOccurences("atg","ctgatga"));
        System.out.println(lastPart("an", "banana") );
        System.out.println(lastPart("zoo","forest") );

    }
    public static void main(String[] args)
    {
        Part3 part=new Part3();
        part.testing();
    }
}