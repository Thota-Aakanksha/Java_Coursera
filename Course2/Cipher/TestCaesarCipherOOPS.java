import edu.duke.FileResource;

public class TestCaesarCipherOOPS {
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length(); k++){
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] values){
        int maxInd = 0;
        for(int k=0; k < values.length; k++){
            if (values[k] > values[maxInd]){
                maxInd= k;
            }
        }
        return maxInd;
    }

    public void simpleTests()
    {
        int key=18;
        FileResource fr = new FileResource();
        String message = fr.asString();

        CaesarCipherOOPS cs=new CaesarCipherOOPS(key);
        String encrypted = cs.encrypt(message);
        System.out.println("key is " + key + "\n" + encrypted);

        CaesarCipherOOPS csdecrypt=new CaesarCipherOOPS(26-key);
        String decrypted = csdecrypt.encrypt(encrypted);
        System.out.println("key is " + key + "\n" + decrypted);

        int breakedCipherKey=breakCaesarCipher(encrypted);
        CaesarCipherOOPS csbreaked=new CaesarCipherOOPS(26-breakedCipherKey);
        decrypted = csbreaked.encrypt(encrypted);
        System.out.println("key is " + key + "\n" +decrypted);
    }

    public int breakCaesarCipher(String encrypted){
        int[] freqs = countLetters(encrypted);
        int maxIndex = maxIndex(freqs);
        int dkey = maxIndex - 4;
        if (maxIndex< 4) {
            dkey = 26 -(4-maxIndex);
        }
        return dkey;
    }

    public static void main(String[] args) {
      TestCaesarCipherOOPS ts=new TestCaesarCipherOOPS();
      ts.simpleTests();
    }
}
