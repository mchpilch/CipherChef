package Classes;

import java.util.*;

public class Playfair extends EncryptionMethod{
    char[][] matrix = new char[5][5];
    String alphabet = "abcdefghiklmnopqrstuvwxyz";
    ArrayList<Integer> spaces = new ArrayList<>();

    public Playfair(String input, String key) {
        super.setKey(key.toLowerCase().replace('j', 'i'));
        super.setInput(input.toLowerCase().replace('j', 'i'));

        generateMatrix();
    }

    public Playfair() {
    }

    public void encrypt() {
        String text = getInput();
        text = removeSpaces(text);
        String[] pairs = formPairs(text);

        char charA, charB;
        int [] posA, posB;
        String encryptedText = "";

        for (int i = 0; i < pairs.length; i++) {
            charA = pairs[i].charAt(0);
            charB = pairs[i].charAt(1);

            posA = getPositionInMatrix(charA);
            posB = getPositionInMatrix(charB);

            // obydwa znaki są takie same
            if (charA == charB) {

                posA[1] = Math.floorMod((posA[1] + 1), 5);
                posA[0] = Math.floorMod((posA[0] + 1), 5);
                posB = posA;
            }

            // obydwie litery są w tym samym wierszu
            else if (posA[0] == posB[0]) {

                posA[1] = (posA[1] + 1) % 5;
                posB[1] = (posB[1] + 1) % 5;
            }

            // obydwie litery są w tej samej kolumnie
            else if (posA[1] == posB[1]) {

                posA[0] = (posA[0] + 1) % 5;
                posB[0] = (posB[0] + 1) % 5;
            }

            // litery są w różnych kolumnach i wierszach
            else {

                int temp = posA[1];
                posA[1] = posB[1];
                posB[1] = temp;
            }

            encryptedText = encryptedText + matrix[posA[0]][posA[1]] + matrix[posB[0]][posB[1]];
        }

        encryptedText = addSpaces(encryptedText);
        setOutput(encryptedText);
    }

    public void decrypt() {
        String text = getInput();
        text = removeSpaces(text);
        String[] pairs = formPairs(text);

        char charA, charB;
        int [] posA, posB;
        String decryptedText = "";
        for (int i = 0; i < pairs.length; i++) {
            charA = pairs[i].charAt(0);
            charB = pairs[i].charAt(1);

            posA = getPositionInMatrix(charA);
            posB = getPositionInMatrix(charB);

            // obydwa znaki są takie same
            if (charA == charB) {

                posA[1] = Math.floorMod((posA[1] - 1) , 5);
                posA[0] = Math.floorMod((posA[0] - 1) , 5);
                posB = posA;
            }

            // obydwie litery są w tym samym wierszu
            else if (posA[0] == posB[0]) {

                posA[1] = Math.floorMod((posA[1] - 1) , 5);
                posB[1] = Math.floorMod((posB[1] - 1) , 5);
            }

            // obydwie litery są w tej samej kolumnie
            else if (posA[1] == posB[1]) {

                posA[0] = Math.floorMod((posA[0] - 1) , 5);
                posB[0] = Math.floorMod((posB[0] - 1) , 5);
            }

            // litery są w różnych kolumnach i wierszach
            else {

                int temp = posA[1];
                posA[1] = posB[1];
                posB[1] = temp;
            }

            decryptedText = decryptedText + matrix[posA[0]][posA[1]] + matrix[posB[0]][posB[1]];

        }
        decryptedText = addSpaces(decryptedText);
        setOutput(decryptedText);
    }

    //funkcja generująca macierz potrzebną do szyfrowania i deszyfrowania
    public void generateMatrix() {
        String keyWithoutDup = removeDuplicateCharacters(getKey());
        String stringToMatrix = removeDuplicateCharacters(keyWithoutDup + alphabet);
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

    // funkcja dodająca spacje
    private String addSpaces(String text) {
        String textWithSpaces = "";

        List<String> strToList = new ArrayList<String>(Arrays.asList(text.split("")));

        for (int i = 0; i < spaces.size(); i++) {
            strToList.add(spaces.get(i), " ");
        }

        for (String s : strToList)
        {
            textWithSpaces += s;
        }

        return  textWithSpaces;
    }

    // funkcja grupująca teks w pary
    public String[] formPairs(String message) {
        int len = message.length();

        if (len % 2 != 0) message += 'x';

        len = message.length();
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

    public char[][] getMatrix() {
        return matrix;
    }

    public void setKey(String key) {
        super.setKey(key);
        generateMatrix();
    }
}
