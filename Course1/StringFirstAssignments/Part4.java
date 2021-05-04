import edu.duke.*;
public class Part4 {
    public void findYoutube()
    {
        URLResource url=new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for(String word:url.words()) {
            String originalWord = word;
            word = word.toLowerCase();
            if (word.contains("youtube.com"))
            {
                int firstQuote = word.indexOf("\"");
                int lastQuote = word.lastIndexOf("\"");
                System.out.println(originalWord.substring(firstQuote, lastQuote + 1));
            }
        }
    }
    public static void main(String[] args)
    {
        Part4 part=new Part4();
        part.findYoutube();
    }
}