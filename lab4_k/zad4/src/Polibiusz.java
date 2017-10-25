public class Polibiusz implements Algorithm {

    private static char[][] board = {{'a', 'b', 'c', 'd', 'e'},
            {'f', 'g', 'h', 'i', 'j'},
            {'k', 'l', 'm', 'n', 'o'},
            {'p', 'r', 's', 't', 'u'},
            {'v', 'w', 'x', 'y', 'z'}};

    // brakuje q, bo i i j zajmuja ta sama pozycje w oryginalnym Polibiuszu (sa nierozrozniane)

    @Override
    public String crypt(String word) {
        String wordEncrypted = "";
        for (char letter : word.toCharArray()) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == letter) {
                        wordEncrypted = wordEncrypted + (i + 1) + (j + 1) + "."; // +1 bo indeksujemy od 0, a w Polibiuszu od 1
                        break;
                    }
                }
            }
        }
        return wordEncrypted;
    }

    @Override
    public String decrypt(String word) {

        String wordDecrypted = "";
        String[] lettersToDecrypt = word.split("\\.");
        Integer Row;
        Integer Column;
        try {
            for (String letter : lettersToDecrypt) {

                Row = (Integer.parseInt(letter)) / 10;
                Column = Integer.parseInt(letter) % 10;


                wordDecrypted += board[Row - 1][Column - 1]; // -1 bo indeksujemy od 0, a w Polibiuszu od 1

            }
        } catch (NumberFormatException e) {
            System.out.println("This is not Polibius cypher!");
        }
        return wordDecrypted;
    }
}