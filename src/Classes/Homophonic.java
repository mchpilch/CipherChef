package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Homophonic extends EncryptionMethod {

    Random generator;
    private Map<Character, ArrayList<Integer>> replacementMap;
    public Homophonic() {
        generator = new Random();
        replacementMap = new HashMap<Character, ArrayList<Integer>>();
        constructReplacementMap();
        setDefaultReplacement();
        displayReplacement();
        super.setName("Homophonic");
    }

    private void constructReplacementMap() { //konstuujemy mapę z 26 znakami i list dla każdego znaku
        for (int i = 97; i < 97 + 26; i++) {
            replacementMap.put((char) i, new ArrayList<Integer>());
        }
    }

    private  void setDefaultReplacement() { //jakieś deafultowe podstawienie na potrzeby testów
        int number = 10;
        for (int i = 97; i < 97 + 26; i++) {
            for (int j = 1; j < 3; j++) {
                replacementMap.get((char) i).add(number);
                number += 1;
            }
        }
    }

    private void displayReplacement() { //też na potrzeby testów
        for (Character letter : replacementMap.keySet()) {
            System.out.print(letter + "=");
            System.out.println(replacementMap.get(letter));
        }
    }

    public void encrypt() {
        String plainText = getInput();
        String encryptedText = "";
        for (int i = 0; i < plainText.length(); i++) {
            encryptedText += drawReplacement(plainText.charAt(i));
        }
        setOutput(encryptedText);
    }

    public void decrypt() {
        String encryptedText = getInput();
        String plainText = "";
        char firstNumber, secondNumber;
        for (int i = 0; i < encryptedText.length(); i++) {
            firstNumber = encryptedText.charAt(i);
            i++;
            secondNumber = encryptedText.charAt(i);
            plainText += getLetterFromReplacement(firstNumber, secondNumber);
        }
        setOutput(plainText);
    }

    private String drawReplacement(char letter) {
        int range = replacementMap.get(letter).size();
        return replacementMap.get(letter).get(generator.nextInt(range)).toString();
    }

    private  String getLetterFromReplacement(char fl, char sl) {
        int d = Character.getNumericValue(fl) * 10;
        int u = Character.getNumericValue(sl);
        int key = d + u;
        for (Character letter : replacementMap.keySet()) {
            if (replacementMap.get(letter).contains(key)) {
                return letter.toString();
            }
        }
        return "#";
    }

    public void setInput(String input) {
        super.setInput(input.toLowerCase());
    }

    public int getLetterCount() {
        return replacementMap.size();
    }

}
