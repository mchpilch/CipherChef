package Classes;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MorseCode extends EncryptionMethod{

    Map<Character, String> letterToMorse = new HashMap<Character, String>();
    Map<String, Character> morseToLetter = new HashMap<String, Character>();

    public MorseCode(String plainText) {
        setLetterToMorse();
        setMorseToLetter();
        System.out.println("przypisanie " + letterToMorse);
        super.setInput(plainText.toLowerCase());
//        super.setOutput(encrypt());
        super.setOutput(decrypt());


    }

    public void setLetterToMorse() {// . -
        letterToMorse.put('a', ".-");
        letterToMorse.put('b', "-...");
        letterToMorse.put('c', "-.-.");
        letterToMorse.put('d', "-..");
        letterToMorse.put('e', ".");
        letterToMorse.put('f', "..-.");
        letterToMorse.put('g', "--.");
        letterToMorse.put('h', "....");
        letterToMorse.put('i', "..");
        letterToMorse.put('j', ".---");
        letterToMorse.put('k', "-.-");
        letterToMorse.put('l', ".-..");
        letterToMorse.put('m', "--");
        letterToMorse.put('n', "-.");
        letterToMorse.put('o', "---");
        letterToMorse.put('p', ".--.");
        letterToMorse.put('q', "--.-");
        letterToMorse.put('r', ".-.");
        letterToMorse.put('s', "...");
        letterToMorse.put('t', "-");
        letterToMorse.put('u', "..-");
        letterToMorse.put('v', "...-");
        letterToMorse.put('w', ".--");
        letterToMorse.put('x', "-..-");
        letterToMorse.put('y', "-.--");
        letterToMorse.put('z', "--..");


        letterToMorse.put(' ', "   ");
    }
    //Each dot or dash within a character is followed by a period of signal absence, called a space, equal to the dot duration.
    // The letters of a word are separated by a space of duration equal to three dots,
    // and words are separated by a space equal to seven dots.[1]
    public void setMorseToLetter() {// . -
        morseToLetter.put(".-",'a');
        morseToLetter.put("-...",'b');
        morseToLetter.put("-.-.",'c');
        morseToLetter.put("-..",'d');
        morseToLetter.put(".",'e');
        morseToLetter.put("..-.",'f');
        morseToLetter.put("--.",'g');
        morseToLetter.put("....",'h');
        morseToLetter.put("..",'i');
        morseToLetter.put(".---",'j');
        morseToLetter.put("-.-",'k');
        morseToLetter.put(".-..",'l');
        morseToLetter.put("--",'m');
        morseToLetter.put("-.",'n');
        morseToLetter.put("---",'o');
        morseToLetter.put(".--.",'p');
        morseToLetter.put("--.-",'q');
        morseToLetter.put(".-.",'r');
        morseToLetter.put("...",'s');
        morseToLetter.put("-",'t');
        morseToLetter.put("..-",'u');
        morseToLetter.put("...-",'v');
        morseToLetter.put(".--",'w');
        morseToLetter.put("-..-",'x');
        morseToLetter.put("-.--",'y');
        morseToLetter.put("--..",'z');


        morseToLetter.put(" ", ' ');
    }
    public String encrypt(){
        String encryptedInMorse = "";
        for(int i = 0; i < getInput().length();i++){
            String currentSign = letterToMorse.get(getInput().charAt(i));//getinput to teskt do zaszyfrowania
            encryptedInMorse += currentSign;
        }
        return encryptedInMorse;
    };

    public String decrypt(){//litery w kodzie morsa odseparowuje sie spacjami a slowa "/"
        String decrypted = "";
        String messageInMorseWithSpaces = getInput()+" ";//dodanie jednej spacji pozwala na działanie prostszego algorytmu w petli potem
        ArrayList<String> morseCodeMessage = new ArrayList<String>();

        String letter = "";
        for(int i = 0; i < messageInMorseWithSpaces.length();i++) {
            if(messageInMorseWithSpaces.charAt(i) ==  ' '){
                morseCodeMessage.add(letter);///!- tu jest problem
                letter = "";
            }else{
                letter += messageInMorseWithSpaces.charAt(i);
            }
            //System.out.println("i " + i + " letter " + letter + " " + morseCodeMessage);//sprawdzanie
        }
        System.out.println(morseCodeMessage);//sprawdzanie
        int counter = 0;
        while(counter < morseCodeMessage.size()){
            if(morseCodeMessage.get(counter) == ""){
                morseCodeMessage.remove(counter);
            }else{
                counter++;
            }
        }
        System.out.println(morseCodeMessage);//sprawdzanie

        for(int i = 0; i < morseCodeMessage.size();i++){//porwnuje przechowywane ciagi z arraylisty do mapy i zamieniam na normalna wiadomosc
                String cellContent = morseCodeMessage.get(i);
                char a = morseToLetter.get(cellContent);
                decrypted += a;

        }
        return decrypted;
    }


}
