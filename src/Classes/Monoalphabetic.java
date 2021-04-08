package Classes;

public class Monoalphabetic extends EncryptionMethod{

    private char[][] replacement = { {'a', 'ą', 'b', 'c', 'ć', 'd', 'e', 'ę', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'ł', 'm', 'n', 'ń', 'o', 'ó', 'p', 'q', 'r', 's', 'ś', 't', 'u', 'w', 'x', 'y', 'z', 'ż', 'ź'},
                                      {'h', 'i', 'j', 'k', 'l', 'ł', 'm', 'n', 'ń', 'o', 'ó', 'p', 'q', 'r', 's', 'ś', 't', 'u', 'v', 'w', 'x', 'z', 'ż', 'ź', 'a', 'ą', 'b', 'c', 'ć', 'd', 'e', 'ę', 'f', 'g'} }; //dla testów podstawiłem literki, potem się doda mechanizm wprowadzania wlasnych podstawien
                                      //{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '} };

    private double[] polishLetterFrequency = { 8.91, 0.99, 1.47, 3.96, 0.40, 3.25, 7.66, 1.11, 0.30, 1.42, 1.08, 8.21, 2.28, 3.51, 2.10, 1.82, 2.80, 5.52, 0.20, 7.75, 0.85, 3.13, 4.69, 4.32, 0.66, 3.98, 2.50, 4.65, 3.76, 5.64, 0.06, 0.83};
    private double[] textLetterFrequency = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private int textLettersCount = 0;

    public Monoalphabetic(String input, String output, String key) {
        super(input, output, key);
    }

    public Monoalphabetic() {
    }

    public double[] getTextLettersFrequency() {
        return textLetterFrequency;
    }

    public void setInput(String input) {
        super.setInput(input.toLowerCase());
    }

    public void encrypt() {
        char letter;
        char[] text = getInput().toCharArray();
        for(int i = 0; i < text.length; i++) {
            letter = text[i];
            int j = 0;
            for(; j < 24; j++) {
                if( letter == replacement[0][j] ){
                    break;
                }
            }
            if( j < 24 ) {
                text[i] = replacement[1][j];
            }
        }

        setOutput(new String(text));
    }

    public void decrypt() {
        calculateTextLettersFrequency();
    }

    public void calculateTextLettersFrequency() {
        calculateTextLettersCount();
        for(int i = 0; i < replacement[0].length; i++) { // petla po każdej literce alfabetu
            int letterCount = 0;
            for(int j = 0; j < getInput().length(); j++) { // petla po każdej literce tekstu
                if( getInput().charAt(j) == replacement[0][i]) {
                    letterCount++;
                }
            }
            //System.out.println(i + "-" + letterCount + '/' + textLettersCount);
            textLetterFrequency[i] = (double)letterCount/textLettersCount * 100;
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
        for (int i = 0; i < textLetterFrequency.length; i++) {
            System.out.println(textLetterFrequency[i]);
        }
    }

}
