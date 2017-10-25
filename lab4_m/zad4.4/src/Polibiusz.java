public class Polibiusz implements Algorithm {
    /**
     * algorytm jest case insensitive, tzn nie zwaracamy uwagi na wielkosc liter
     * szachownica jest klasyczna, bez klucza
     * alfabet angielski bez litery q
     */

    private static char[][] array = {{ 'a','b','c','d','e'},
                                     { 'f','g','h','i','j'},
                                     { 'k','l','m','n','o'},
                                     { 'p','r','s','t','u'},
                                     { 'v','w','x','y','z'}};

    @Override
    public String crypt(String wordToEncrypt) {
        String wordEncrypted = "";
        for(char letter : wordToEncrypt.toCharArray()) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j] == letter) {
                        wordEncrypted = wordEncrypted + (i+1) + (j+1) + ".";
                        break;
                    }
                }
            }
        }
        return wordEncrypted;
    }

    @Override
    public String decrypt(String wordToDecrypt) {
        String wordDecrypted = "";
        String[] lettersToDecrypt = wordToDecrypt.split("\\.");
        Integer wantedLetterJ;
        Integer wantedLetterI;

        for (String letter : lettersToDecrypt) {
            wantedLetterJ = Integer.parseInt(letter)%10;        //dla "31" dostane 1
            wantedLetterI = (Integer.parseInt(letter))/10;      //dla "31" dostane 3 (dzielenie calkowite)

            wordDecrypted += array[wantedLetterI-1][wantedLetterJ-1];
        }

        return wordDecrypted;
    }
}
