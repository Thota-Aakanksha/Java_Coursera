import edu.duke.*;

public class CaesarCipherOOPS{
    private String alpha;
    private String shiftAlpha;
    private int key;
    public CaesarCipherOOPS(int key)
    {
        this.key=key;
        alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftAlpha=alpha.substring(key)+alpha.substring(0,key);
    }
    public String encrypt(String input)
        {
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
        public String decrypt(String input)
        {
            CaesarCipherOOPS decryptionCipher=new CaesarCipherOOPS(26-key);
            return decryptionCipher.encrypt(input);
        }




}

