package Classes;

import java.util.*;

public class Homophonic extends EncryptionMethod {

    Random generator;
    private Map<Character, ArrayList<Integer>> replacementMap;
    public Homophonic() {
        generator = new Random();
        replacementMap = new HashMap<Character, ArrayList<Integer>>();
        constructReplacementMap();
        setDefaultReplacement();
        super.setName("Homophonic");
    }

    private void constructReplacementMap() { //konstruujemy mapę z 26 znakami i liste dla każdego znaku
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

    public void displayReplacement() { //też na potrzeby testów
        for (Character letter : replacementMap.keySet()) {
            System.out.print(letter + "=");
            System.out.println(replacementMap.get(letter));
        }
    }

    public void encrypt() {
        String plainText = getInput();
        String encryptedText = "";
        for (int i = 0; i < plainText.length(); i++) {
            if (plainText.charAt(i) != ' ') {
                encryptedText += drawReplacement(plainText.charAt(i));
            } else {
                encryptedText += " ";
            }
        }
        setOutput(encryptedText);
    }

    public void decrypt() {
        String encryptedText = getInput();
        String plainText = "";
        char firstNumber, secondNumber;
        for (int i = 0; i < encryptedText.length(); i++) {
            if (encryptedText.charAt(i) != ' ') {
                firstNumber = encryptedText.charAt(i);
                i++;
                secondNumber = encryptedText.charAt(i);
                plainText += getLetterFromReplacement(firstNumber, secondNumber);
            } else {
                plainText += " ";
            }
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

    private void addReplacement(String strNumber, char letter) {
        int number = Integer.parseInt(strNumber);
        replacementMap.get(letter).add(number);
    }

    public boolean checkPlainText(String input) {
        input = input.toLowerCase();
        Character character;

        for (int i = 0; i < input.length(); i++) {

            character = input.charAt(i);

            if ((character < 97 || character > 122) && character != ' ') {
                return false;
            }

        }

        return true;
    }

    public boolean checkCryptogram(String input) {
        input = input.toLowerCase();
        String[] inputArray = input.split(" ");

        for (String str : inputArray) {
            if (str.length() % 2 != 0) {
                return false;
            }
        }

        return true;

    }

    public void setInput(String input) {
        super.setInput(input.toLowerCase());
    }

    public int getLetterCount() {
        return replacementMap.size();
    }

    public Object[] getLetters() {
        return replacementMap.keySet().toArray();
    }

    public String getReplacement(char letter) {
        return replacementMap.get(letter).toString();
    }

    public void setReplacement(String string, char letter) {
        replacementMap.get(letter).clear();
        String[] newString = string.split(",");
        for (String a : newString) {
            if (a.contains("[")) {
                a = a.replace("[", "");
            }
            if (a.contains("]")) {
                a = a.replace("]", "");
            }
            if (a.contains(" ")) {
                a = a.replace(" ", "");
            }
            addReplacement(a, letter);
        }
    }


}
