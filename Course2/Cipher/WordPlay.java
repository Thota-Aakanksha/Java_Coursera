public class WordPlay {
    public boolean isVowel(char ch)
    {
        ch=Character.toLowerCase(ch);
        String vowels="aeiou";
        return vowels.indexOf(ch) != -1;
    }
    public void testIsVowel()
    {
        System.out.println(isVowel('F'));
        System.out.println(isVowel('A'));
        System.out.println(replaceVowels("Hello",'*'));
        System.out.println(emphasize("dna ctgaaactga", 'a') );
        System.out.println(emphasize("Mary Bella Abracadabra", 'a') );
    }
    public String replaceVowels(String phrase, char ch)
    {
        StringBuilder result=new StringBuilder(phrase.toLowerCase());
        for(int i=0;i<result.length();i++)
        {
            if(isVowel(result.charAt(i)))
                result.setCharAt(i,ch);
        }
        return result.toString();
    }
    public String emphasize(String phrase,char ch)
    {
        StringBuilder result=new StringBuilder(phrase.toLowerCase());
        for(int i=0;i<result.length();i++)
        {
            if(result.charAt(i)==ch)
                if(i%2==0)
                    result.setCharAt(i,'*');
                else
                    result.setCharAt(i,'+');
        }
        return result.toString();
    }

    public static void main(String[] args) {
        WordPlay word=new WordPlay();
        word.testIsVowel();
    }
}
