package Classes;

import java.util.HashMap;
import java.util.Map;

public class Monoalphabetic extends EncryptionMethod{

    private int textLettersCount = 0;

    Map<Character, Character> replacement = new HashMap<Character, Character>();
    Map<Character, Double> polishLetters = new HashMap<Character, Double>();
    Map<Character, Double> textLetters = new HashMap<Character, Double>();

    /*public Monoalphabetic(String input, String output, String key) {
        super(input, output, key);
    }*/

    public Monoalphabetic() {
        setPolishLetters();
        setDefaultReplacement();
        super.setName("Monoalphabetic");
    }

    public void setInput(String input) {
        super.setInput(input.toLowerCase());
    }

    public void setReplacement( Map<Character,Character> replacement) {
        this.replacement = replacement;
    }

    public void encrypt() {
        char letter;
        char[] text = getInput().toCharArray();
        for(int i = 0; i < text.length; i++) {
            letter = text[i];
            if (text[i] != ' ') text[i] = replacement.get(letter);
        }

        setOutput(new String(text));
    }

    public void decrypt() {
        calculateTextLettersFrequency();
        char letter;
        char[] text = getInput().toCharArray();
        for(int i = 0; i < text.length; i++) {
            letter = text[i];
            if (text[i] != ' ') text[i] = getOriginalLetter(letter);
        }

        setOutput(new String(text));
    }

    private char getOriginalLetter(char letter) {
        for (char key : replacement.keySet()) {
            if (replacement.get(key).equals(letter)) {
                return key;
            }
        }
        return '#';
    }

    public void calculateTextLettersFrequency() {
        calculateTextLettersCount();
        for (Character letter : polishLetters.keySet()) { //pętla po każdej literce alfabetu
            int letterCount = 0;
            double freq = 0;
            for (int i = 0; i < getInput().length(); i++) { //petla po każdej literce tekstu
                if( letter == getInput().charAt(i)) {
                    letterCount++;
                }
            }
            freq = (double) letterCount/textLettersCount;
            textLetters.put(letter, freq);
        }
    }

    public void calculateTextLettersCount() {
        for(int i = 0; i < getInput().length(); i++) {
            if( getInput().charAt(i) != ' ' ) {
                textLettersCount++;
            }
        }
    }

    public void displayTextLettersFrequency() {
        System.out.print(textLetters);
    }

    public void setPolishLetters() {
        polishLetters.put('a', 8.91);
        polishLetters.put('ą', 0.99);
        polishLetters.put('b', 1.47);
        polishLetters.put('c', 3.96);
        polishLetters.put('ć', 0.40);
        polishLetters.put('d', 3.25);
        polishLetters.put('e', 7.66);
        polishLetters.put('ę', 1.11);
        polishLetters.put('f', 0.30);
        polishLetters.put('g', 1.42);
        polishLetters.put('h', 1.08);
        polishLetters.put('i', 8.21);
        polishLetters.put('j', 2.28);
        polishLetters.put('k', 3.51);
        polishLetters.put('l', 2.10);
        polishLetters.put('ł', 1.82);
        polishLetters.put('m', 2.80);
        polishLetters.put('n', 5.52);
        polishLetters.put('ń', 0.20);
        polishLetters.put('o', 7.75);
        polishLetters.put('ó', 0.85);
        polishLetters.put('p', 3.13);
        polishLetters.put('q', 0.14);
        polishLetters.put('r', 4.69);
        polishLetters.put('s', 4.32);
        polishLetters.put('ś', 0.66);
        polishLetters.put('t', 3.98);
        polishLetters.put('u', 2.50);
        polishLetters.put('v', 0.04);
        polishLetters.put('w', 4.65);
        polishLetters.put('x', 0.02);
        polishLetters.put('y', 3.76);
        polishLetters.put('z', 5.64);
        polishLetters.put('ź', 0.06);
        polishLetters.put('ż', 0.83);
    }

    public void setDefaultReplacement() {
        replacement.put('a', 'ą');
        replacement.put('ą', 'b');
        replacement.put('b', 'c');
        replacement.put('c', 'ć');
        replacement.put('ć', 'd');
        replacement.put('d', 'e');
        replacement.put('e', 'ę');
        replacement.put('ę', 'f');
        replacement.put('f', 'g');
        replacement.put('g', 'h');
        replacement.put('h', 'i');
        replacement.put('i', 'j');
        replacement.put('j', 'k');
        replacement.put('k', 'l');
        replacement.put('l', 'ł');
        replacement.put('ł', 'm');
        replacement.put('m', 'n');
        replacement.put('n', 'ń');
        replacement.put('ń', 'o');
        replacement.put('o', 'ó');
        replacement.put('ó', 'p');
        replacement.put('p', 'q');
        replacement.put('q', 'r');
        replacement.put('r', 's');
        replacement.put('s', 'ś');
        replacement.put('ś', 't');
        replacement.put('t', 'u');
        replacement.put('u', 'v');
        replacement.put('v', 'w');
        replacement.put('w', 'x');
        replacement.put('x', 'y');
        replacement.put('y', 'z');
        replacement.put('z', 'ź');
        replacement.put('ź', 'ż');
        replacement.put('ż', 'a');
    }

    public int getPolishLettersCount() {
        return polishLetters.size();
    }

    public Map<Character, Character> getReplacement() {
        return replacement;
    }
}
