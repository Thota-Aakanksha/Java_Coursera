import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice=new StringBuilder();
        for(int i=whichSlice;i<message.length();i+=totalSlices)
            slice.append(message.charAt(i));
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int sliceNumber = 0; sliceNumber < klength; sliceNumber++)
        {
            String slice = sliceString(encrypted, sliceNumber, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[sliceNumber] = cc.getKey(slice);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> dictionary=new HashSet<>();

        for(String line:fr.lines())
            dictionary.add(line.toLowerCase());

        return dictionary;
    }

    public int countWords(String message,HashSet<String> dictionary)
    {
        int count=0;
        String[] words=message.split("\\W");

        for(String word: words)
            if(dictionary.contains(word.toLowerCase()))
                count++;

        return count;
    }


    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        HashMap<Character,Integer> countChar=new HashMap<>();
        int maxOccurence=0;
        char maxChar='\0';
        for(String word:dictionary)
            countCharInWord(countChar, word);

        for(Character ch:countChar.keySet()) {

            int currOccurence = countChar.get(ch);
            if (currOccurence > maxOccurence){
                maxOccurence = currOccurence;
                maxChar = ch;
            }
        }
        return maxChar;
    }

    private void countCharInWord(HashMap<Character, Integer> countChar, String word) {
        for(int i = 0; i< word.length(); i++)
        {
            char currChar= word.charAt(i);
            if(countChar.containsKey(currChar))
                countChar.put(currChar, countChar.get(currChar)+1);
            else
                countChar.put(currChar,1);
        }
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
        int curCount=0, maxCount=0;
        String decrypted="";
        char mostCommonChar=mostCommonCharIn(dictionary);
        for(int keyLength = 1; keyLength <100; keyLength++)
        {
            int[] key = tryKeyLength(encrypted, keyLength, mostCommonChar);

            VigenereCipher vc = new VigenereCipher(key);
            String curDecrypted=vc.decrypt(encrypted);

            curCount = countWords(curDecrypted,dictionary);
            if(curCount>maxCount)
            {
                maxCount=curCount;
                decrypted=curDecrypted;
            }
        }
        return decrypted;
    }

    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages)
    {
        HashSet<String> dictionary;
        int curCount=0,maxCount=0;
        String decrypted="";
        for(String language:languages.keySet())
        {
            dictionary=languages.get(language);

            String curDecrypted=breakForLanguage(encrypted,dictionary);

            curCount=countWords(curDecrypted,dictionary);
            if(curCount>maxCount)
            {
                maxCount=curCount;
                decrypted=curDecrypted;
            }
        }
        return decrypted;

    }

     /* Assignment 1 - when keylength is known */
    /*
    public void breakVigenere () {
        int keylength=5;
        FileResource fr = new FileResource();
        String message = fr.asString();

        int[] key = tryKeyLength(message, keylength, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(message));
    }
    */

    /* Assignment 2 - when keylength is unknown */
    /*
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();

        FileResource dictFile=new FileResource();
        HashSet<String> dict=readDictionary(dictFile);

        String decrypted= breakForLanguage(message,dict);
        System.out.println(decrypted);
    }
    */

    /* Assignment 3 - when keylength and language is unknown */
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        HashMap<String,HashSet<String>> languages=new HashMap<>();
        DirectoryResource dictDirectory=new DirectoryResource();

        for(File f:dictDirectory.selectedFiles()) {
            FileResource dictFile=new FileResource(f);
            HashSet<String> dict = readDictionary(dictFile);
            String language=f.getName();
            languages.put(language,dict);
        }

        String decrypted=breakForAllLangs(message,languages);
        System.out.println(decrypted);
    }

    public static void main(String[] args) {
        VigenereBreaker vg=new VigenereBreaker();
        vg.breakVigenere();
    }
    
}
