
import java.util.*;

class Encrypter {

    HashMap<Character, String> encryptMap;
    HashMap<String, Integer> decryptMap;

    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        encryptMap = new HashMap<>();
        decryptMap = new HashMap<>();

        // Build character-to-encrypted-value mapping
        for (int i = 0; i < keys.length; i++) {
            encryptMap.put(keys[i], values[i]);
        }

        // Pre-encrypt dictionary words and count frequencies
        for (String word : dictionary) {
            String encrypted = encrypt(word);

            if (!encrypted.isEmpty()) {
                decryptMap.put(
                    encrypted,
                    decryptMap.getOrDefault(encrypted, 0) + 1
                );
            }
        }
    }

    public String encrypt(String word1) {
        StringBuilder result = new StringBuilder();

        for (char c : word1.toCharArray()) {
            if (!encryptMap.containsKey(c)) {
                return "";
            }

            result.append(encryptMap.get(c));
        }

        return result.toString();
    }

    public int decrypt(String word2) {
        return decryptMap.getOrDefault(word2, 0);
    }
}


/**
 * Your Encrypter object will be instantiated and called as such:
 * Encrypter obj = new Encrypter(keys, values, dictionary);
 * String param_1 = obj.encrypt(word1);
 * int param_2 = obj.decrypt(word2);
 */