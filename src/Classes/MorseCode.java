package Classes;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MorseCode extends EncryptionMethod {

    Map<String, Character> morseToLetter = new HashMap<String, Character>();
    Map<Character, String> letterToMorse = new HashMap<Character, String>();

    public MorseCode(String plainText) {
        setLetterToMorse();
        setMorseToLetter();
        setInput(plainText.toLowerCase());
    }

    public MorseCode() {
        super.setName("MorseCode");
        setLetterToMorse();
        setMorseToLetter();
    }
    public void setLetterToMorse() {// . -
        letterToMorse.put('a', ".-");
        letterToMorse.put('ą', ".-");
        letterToMorse.put('b', "-...");
        letterToMorse.put('c', "-.-.");
        letterToMorse.put('ć', "-.-.");
        letterToMorse.put('d', "-..");
        letterToMorse.put('e', ".");
        letterToMorse.put('ę', ".");
        letterToMorse.put('f', "..-.");
        letterToMorse.put('g', "--.");
        letterToMorse.put('h', "....");
        letterToMorse.put('i', "..");
        letterToMorse.put('j', ".---");
        letterToMorse.put('k', "-.-");
        letterToMorse.put('l', ".-..");
        letterToMorse.put('ł', ".-..");
        letterToMorse.put('m', "--");
        letterToMorse.put('n', "-.");
        letterToMorse.put('ń', "-.");
        letterToMorse.put('o', "---");
        letterToMorse.put('ó', "---");
        letterToMorse.put('p', ".--.");
        letterToMorse.put('q', "--.-");
        letterToMorse.put('r', ".-.");
        letterToMorse.put('s', "...");
        letterToMorse.put('ś', "...");
        letterToMorse.put('t', "-");
        letterToMorse.put('u', "..-");
        letterToMorse.put('v', "...-");
        letterToMorse.put('w', ".--");
        letterToMorse.put('x', "-..-");
        letterToMorse.put('y', "-.--");
        letterToMorse.put('z', "--..");
        letterToMorse.put('ź', "--..");
        letterToMorse.put('ż', "--..");

        letterToMorse.put('1', ".----");
        letterToMorse.put('2', "..---");
        letterToMorse.put('3', "...--");
        letterToMorse.put('4', "....-");
        letterToMorse.put('5', ".....");
        letterToMorse.put('6', "-....");
        letterToMorse.put('7', "--...");
        letterToMorse.put('8', "---..");
        letterToMorse.put('9', "----.");
        letterToMorse.put('0', "-----");


        letterToMorse.put(' ', "/");
    }

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

        morseToLetter.put(".----",'1');
        morseToLetter.put("..---",'2');
        morseToLetter.put("...--",'3');
        morseToLetter.put("....-",'4');
        morseToLetter.put(".....",'5');
        morseToLetter.put("-....",'6');
        morseToLetter.put("--...",'7');
        morseToLetter.put("---..",'8');
        morseToLetter.put("----.",'9');
        morseToLetter.put("-----",'0');

        morseToLetter.put("/", ' ');
    }
    //getinput to teskt do zaszyfrowania


    public void encrypt(){
        String encryptedInMorse = "";
        for(int i = 0; i < getInput().length();i++){
            String currentSign = letterToMorse.get(getInput().charAt(i));
            encryptedInMorse += currentSign + " ";
        }
        setOutput(encryptedInMorse);
    }

    public void decrypt(){//litery w kodzie morsa odseparowuje sie spacjami a slowa "/"
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
        }

        int counter = 0;
        while(counter < morseCodeMessage.size()){
            if(morseCodeMessage.get(counter) == ""){
                morseCodeMessage.remove(counter);
            }else{
                counter++;
            }
        }

        for(int i = 0; i < morseCodeMessage.size();i++){//porwnuje przechowywane ciagi z arraylisty do mapy i zamieniam na normalna wiadomosc
                String cellContent = morseCodeMessage.get(i);
                char a = morseToLetter.get(cellContent);
                decrypted += a;

        }
        setOutput(decrypted);
    }

    public static void w8(long ms) {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void soundMorseCode(String sequence) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File long_sig = new File("src/Audio/long_signal.wav");
        File short_sig = new File("src/Audio/short_signal.wav");
        AudioInputStream audioStreamLong = AudioSystem.getAudioInputStream(long_sig);
        AudioInputStream audioStreamShort = AudioSystem.getAudioInputStream(short_sig);
        Clip clip_long = AudioSystem.getClip();
        Clip clip_short = AudioSystem.getClip();
        clip_long.open(audioStreamLong);
        clip_short.open(audioStreamShort);



        long lenOfshort = clip_short.getMicrosecondLength()/1000;//μs to ms
        long lenOflong = clip_long.getMicrosecondLength()/1000;//μs to ms

        //pętla iterująca po wiadomości i odtwarzająca dźwięk

        for(int i = 0; i < sequence.length(); i++){
            String currentSign = String.valueOf(sequence.charAt(i));
            if(currentSign.equals(".")){
                clip_short.setMicrosecondPosition(0);
                clip_short.start();
                System.out.println("dot");
                w8(lenOfshort+50); //wait(175);//bez tego dzwiek nie ma kiedy wybrzmiec i nie zostanie poprawnie odtworzony
            }
            if(currentSign.equals("-")){
                clip_long.setMicrosecondPosition(0);
                clip_long.start();
                System.out.println("dash");
                w8(lenOflong+50);//w8(345);//bez tego dzwiek nie ma kiedy wybrzmiec i nie zostanie poprawnie odtworzony, dash potrzebuje wiecej czasu niz dot
            }
            if(currentSign.equals(" ")){
                System.out.println(" ");
                w8(345);
            }
            if(currentSign.equals("/")){
                System.out.println("/");
                w8(1035);
            }

        }
    }

    @Override
    public void setInput(String input) {
        super.setInput(input.toLowerCase());
    }

    public Map<Character, String> getLetterToMorse() {
        return letterToMorse;
    }

    public Map<String, Character> getMorseToLetter() {
        return morseToLetter;
    }
}
