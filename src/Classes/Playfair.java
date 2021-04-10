package Classes;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class Playfair extends EncryptionMethod{
    char[][] matrix = new char[5][5];
    String alphabet = "abcdefghiklmnopqrstuvwxyz";

    public Playfair(String plainText, String key) {
        super.setKey(key.toLowerCase().replace('j', 'i'));
        super.setInput(plainText.toLowerCase().replace('j', 'i'));

        generateMatrix();
    }

    public void encrypt() {
        char charA, charB;
    }

    //funkcja generująca macierz potrzebną do szyfrowania i deszyfrowania
    public void generateMatrix() {
        String keyWithoutDup = removeDuplicateCharacters(getKey());
        String stringToMatrix = removeDuplicateCharacters(keyWithoutDup+alphabet);
        if ( stringToMatrix.length() != 25 ) {
            System.out.println("Przyps");
        }else {
            int index = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = stringToMatrix.charAt(index);
                    index++;
                }
            }
        }
    }

    //funkcja usuwająca powtarzające się znaki w tekście
    private String removeDuplicateCharacters(String text) {
        LinkedHashSet<Character> set
                = new LinkedHashSet<Character>();

        String newText = "";

        for (int i = 0; i < text.length(); i++)
            set.add(text.charAt(i));

        Iterator<Character> it = set.iterator();

        while (it.hasNext())
            newText += (Character)it.next();

        return newText;
    }

    public void displayMatrix() {
        for ( int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
