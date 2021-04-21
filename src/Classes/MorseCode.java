package Classes;


import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class MorseCode extends EncryptionMethod{

    Map<Character, String> letterToMorse = new HashMap<Character, String>();

    public MorseCode(String plainText) {
        setLetterToMorse();
        System.out.println("przypisanie " + letterToMorse);
        super.setInput(plainText.toLowerCase());
        super.setOutput(encrypt());


    }

    public void setLetterToMorse() {// • —
        letterToMorse.put('a', "•—");
        letterToMorse.put('b', "—•••");
        letterToMorse.put('c', "—•—•");
        letterToMorse.put('d', "—••");
        letterToMorse.put('e', "•");
        letterToMorse.put('f', "••—•");
        letterToMorse.put('g', "——•");
        letterToMorse.put('h', "••••");
        letterToMorse.put('i', "••");
        letterToMorse.put('j', "•———");
        letterToMorse.put('k', "—•—");
        letterToMorse.put('l', "•—••");
        letterToMorse.put('m', "——");
        letterToMorse.put('n', "—•");
        letterToMorse.put('o', "———");
        letterToMorse.put('p', "•——•");
        letterToMorse.put('q', "——•—");
        letterToMorse.put('r', "•—•");
        letterToMorse.put('s', "•••");
        letterToMorse.put('t', "—");
        letterToMorse.put('u', "••—");
        letterToMorse.put('v', "•••—");
        letterToMorse.put('w', "•——");
        letterToMorse.put('x', "—••—");
        letterToMorse.put('y', "—•——");
        letterToMorse.put('z', "——••");


        letterToMorse.put(' ', "   ");

    }

    public String encrypt(){
        String encryptedInMorse = "";
        for(int i = 0; i < getInput().length();i++){
            String currentSign = letterToMorse.get(getInput().charAt(i));
            encryptedInMorse += currentSign;
        }

        return encryptedInMorse;
    };


}
