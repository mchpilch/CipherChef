package Classes;

import java.util.HashMap;
import java.util.Map;

public class Monoalphabetic extends EncryptionMethod{
    /*
    private char[][] replacement = { {'a', 'ą', 'b', 'c', 'ć', 'd', 'e', 'ę', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'ł', 'm', 'n', 'ń', 'o', 'ó', 'p', 'q', 'r', 's', 'ś', 't', 'u', 'w', 'x', 'y', 'z', 'ż', 'ź'},
                                      {'h', 'i', 'j', 'k', 'l', 'ł', 'm', 'n', 'ń', 'o', 'ó', 'p', 'q', 'r', 's', 'ś', 't', 'u', 'v', 'w', 'x', 'z', 'ż', 'ź', 'a', 'ą', 'b', 'c', 'ć', 'd', 'e', 'ę', 'f', 'g'} }; //dla testów podstawiłem literki, potem się doda mechanizm wprowadzania wlasnych podstawien
                                      //{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '} };

    private double[] polishLetterFrequency = { 8.91, 0.99, 1.47, 3.96, 0.40, 3.25, 7.66, 1.11, 0.30, 1.42, 1.08, 8.21, 2.28, 3.51, 2.10, 1.82, 2.80, 5.52, 0.20, 7.75, 0.85, 3.13, 4.69, 4.32, 0.66, 3.98, 2.50, 4.65, 3.76, 5.64, 0.06, 0.83};
    private double[] textLetterFrequency = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

     */
    private int textLettersCount = 0;

    Map<Character, Character> replacement = new HashMap<Character, Character>();
    Map<Character, Double> polishLetters = new HashMap<Character, Double>();
    Map<Character, Double> textLetters = new HashMap<Character, Double>();

    public Monoalphabetic(String input, String output, String key) {
        super(input, output, key);
    }

    public Monoalphabetic() {
        setDefaultReplacement();
    }

    /*public double[] getTextLettersFrequency() {
        return textLetters.;
    }*/

    public void setInput(String input) {
        super.setInput(input.toLowerCase());
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

    public void setReplacement( Map<Character,Character> replacement) {
        this.replacement = replacement;
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

    public void encrypt() {
        char letter;
        char[] text = getInput().toCharArray();
        for(int i = 0; i < text.length; i++) {
            letter = text[i];
            text[i] = replacement.get(letter);
        }

        setOutput(new String(text));
    }

    public void decrypt() {
        //calculateTextLettersFrequency();
    }

    public void calculateTextLettersFrequency() {
        calculateTextLettersCount();
        // po przejsciu na mapy jedszcze nie napisałem tego
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

}
