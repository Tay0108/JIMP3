/**
 * w interfejsach metody są TYLKO public i nie mają ciał (no chyba ze Java 9 - metody domyślne
 */

public interface Algorithm {
    /**
     * poniższe metody szyfruja/deszyfruja po SŁOWIE, nie po liniach
     */
    String crypt(String wordToEncrypt);
    String decrypt(String wordToDecrypt);
}
