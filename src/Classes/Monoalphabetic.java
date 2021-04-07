package Classes;

public class Monoalphabetic extends EncryptionMethod{

    private char[][] substitunion = { {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'},
                                      {'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a'} }; //dla testów podstawiłem literki, potem się doda mechanizm wprowadzania wlasnych podstawien
                                      //{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '} };

    public Monoalphabetic(String input, String output, String key) {
        super(input, output, key);
    }

    public Monoalphabetic() {
    }

    public void encrypt() {
        char letter;
        char[] text = getInput().toLowerCase().toCharArray();
        for(int i = 0; i < text.length; i++) {
            letter = text[i];
            int j = 0;
            for(; j < 24; j++) {
                if( letter == substitunion[0][j] ){
                    break;
                }
            }
            if( j < 24 ) {
                text[i] = substitunion[1][j];
            }
        }

        setOutput(new String(text));
    }

}
