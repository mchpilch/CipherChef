package Classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Playfair extends EncryptionMethod{
    char[][] matrix = new char[5][5];
    String alphabet = "abcdefghiklmnopqrstuvwxyz";
    ArrayList<Integer> spaces = new ArrayList<>();

    public Playfair(String plainText, String key) {
        super.setKey(key.toLowerCase().replace('j', 'i'));
        super.setInput(plainText.toLowerCase().replace('j', 'i'));

        generateMatrix();
    }

    public void encrypt() {
        String text = getInput();
        text = removeSpaces(text);
        String[] pairs = formPairs(text);

        char charA, charB;
        int [] posA, posB;
        for (int i = 0; i < pairs.length; i++) {
            charA = pairs[i].charAt(0);
            charB = pairs[i].charAt(1);

            posA = getPositionInMatrix(charA);
            posB = getPositionInMatrix(charB);

        }
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
    // funkcja usuwająca spacje
    private String removeSpaces(String text) {
        for (int i = 0; i < text.length(); i++) {
            if( text.charAt(i) == ' ' ) {
                spaces.add(i);

            }
        }
        text = text.replaceAll("\\s", "");
        return text;
    }

    // funkcja grupująca teks w pary
    public String[] formPairs(String message)
    {
        int len = message.length();
        String[] pairs = new String[len / 2];

        for (int i = 0, cnt = 0; i < len / 2; i++)
            pairs[i] = message.substring(cnt, cnt += 2);

        return pairs;
    }

    //funckja zwracająca pozycję litery w macierzy
    private int[] getPositionInMatrix(char letter) {
        boolean found = false;
        int [] position = new int[2];
        for ( int i = 0; i < matrix.length; i++) {
            if (found) break;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == letter) {
                    position[0] = i;
                    position[1] = j;
                    found = true;
                    break;
                }
            }
        }
        return position;
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
