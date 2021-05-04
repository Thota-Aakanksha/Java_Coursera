public class Part2 {
    public int howMany(String a,String b)
    {
        int count=0;
        int curIndex= b.indexOf(a, 0);
        while(curIndex!=-1) {
            count+=1;
            curIndex = b.indexOf(a,curIndex+a.length());
        }
        return count;
    }

    void testHowMany(){
        System.out.println( howMany("aa","aabaaan"));
        System.out.println( howMany("c","banana"));
        System.out.println( howMany("b","banana"));
    }

    public static void main(String[] args)
    {
        Part2 part=new Part2();
        part.testHowMany();
    }

}
