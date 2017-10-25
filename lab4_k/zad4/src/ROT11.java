public class ROT11 implements Algorithm {

    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static int rotation = 11;

    @Override
    public String crypt(String word) {
        String wordEncrypted = "";
        for (char letter : word.toCharArray()) {
            wordEncrypted += alphabet.charAt((alphabet.indexOf(letter) + rotation) % alphabet.length());
            // bierze character ktory jest na pozycji (dana litera + rotacja) modulo dlugosc alfabetu
        }
        return wordEncrypted;
    }

    @Override
    public String decrypt(String word) {
        String wordDecrypted = "";
        for (char letter : word.toCharArray()) {
            if (alphabet.indexOf(letter) < rotation) {
                wordDecrypted += alphabet.charAt(alphabet.length() + alphabet.indexOf(letter) - rotation);
            } else {
                wordDecrypted += alphabet.charAt((alphabet.indexOf(letter) - rotation));
            }
        }
        return wordDecrypted;
    }
}


