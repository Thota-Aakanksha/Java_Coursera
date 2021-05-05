import edu.duke.*;

class CaesarCipherWithoutOOPS {
   public String encrypt(String input,int key)
    {
        String alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftAlpha=alpha.substring(key)+alpha.substring(0,key);
        String alphaLower=alpha.toLowerCase();
        String shiftAlphaLower=shiftAlpha.toLowerCase();

        StringBuilder encryptedMessage= new StringBuilder();
        char currChar;
        for(int i=0;i<input.length();i++) {
            currChar = input.charAt(i);
            currChar = replaceCurrChar(shiftAlpha,alpha,currChar);
            currChar = replaceCurrChar(shiftAlphaLower,alphaLower,currChar);
            encryptedMessage.append(currChar);
        }
        return encryptedMessage.toString();
    }

    private char replaceCurrChar(String shiftAlphaLower, String alpha,char currChar) {
       int index=(alpha.indexOf(currChar));
       if(index !=-1)
            currChar = shiftAlphaLower.charAt(index);
       return currChar;
    }

    public void testCaesar()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key=21;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }

    public static void main(String[] args) {
        CaesarCipherWithoutOOPS cs=new CaesarCipherWithoutOOPS();
        cs.testCaesar();
    }
}
