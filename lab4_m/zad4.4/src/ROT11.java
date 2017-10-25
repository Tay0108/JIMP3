public class ROT11 implements Algorithm{

    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static int rotation = 11;

    @Override
    public String crypt(String wordToEncrypt) {
        String wordEncrypted = "";
        for (char letter : wordToEncrypt.toCharArray()) {
            /*if (alphabet.contains(letter)) {

            } else {
                */wordEncrypted += alphabet.charAt((alphabet.indexOf(letter) + rotation) % alphabet.length());
            //}
        }
        return wordEncrypted;
    }

    @Override
    public String decrypt(String wordToDecrypt) {
        String wordDecrypted = "";
        for (char letter : wordToDecrypt.toCharArray()) {
            if (alphabet.indexOf(letter) < rotation) {
                wordDecrypted += alphabet.charAt(alphabet.length() + alphabet.indexOf(letter) - rotation);
            } else {
                wordDecrypted += alphabet.charAt((alphabet.indexOf(letter) - rotation));
            }
        }
        return wordDecrypted;
    }
}