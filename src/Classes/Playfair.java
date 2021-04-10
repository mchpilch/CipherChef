package Classes;

public class Playfair extends EncryptionMethod{
    char[][] matrix = new char[5][5];

    public Playfair(String plainText, String key) {
        super.setKey(key);
        super.setInput(plainText.toLowerCase());

        generateMatrix();
    }

    public void generateMatrix() { //funkcja generująca macierz potrzebną do szyfrowania i deszyfrowania
        String keyWithoutDup = removeDuplicateCharacters(getKey()); //<-------
        String alphabetWithoutKey = removeKeyFromAlphabet(keyWithoutDup);//<--   jeszcze nie napisane XD
        int keyIndex = 0;
        int alphabetIndex = 0;
        for ( int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if ( keyIndex < getKey().length() ) { //warunek jeśli klucz się nie skończył
                    matrix[i][j] = keyWithoutDup.charAt(keyIndex);
                    keyIndex++;
                }else { //warunek jeśli klucz się skończył
                    matrix[i][j] = alphabetWithoutKey.charAt(alphabetIndex);
                    keyIndex++;
                }
            }
        }
    }

    private boolean doesMatrixContain(char letter) { //funkcja sprawdzająca czy w macierzy już jest dana litera
        for ( int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if( matrix[i][j] == letter) {
                    return true;
                }
            }
        }
        return false;
    }
}
