import Classes.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLFiles/StartMenu.fxml"));
        primaryStage.setTitle("CipherChef");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedAudioFileException, IOException, LineUnavailableException {
    /*
        Monoalphabetic mono = new Monoalphabetic();
        mono.setInput("Litwo Ojczyzno moja Ty jesteś jak zdrowie Ile cię stracił Dziś piękność twą w szlacheckim stanie trudno zaradzić wolał gości Żydom do nowej mody odsyłać konie porzucone same szczypiąc trawę ciągnęły powoli pod lasem zwaliska");
        mono.decrypt();
        mono.displayTextLettersFrequency();



        //Playfair playfair = new Playfair("jebac telewizje polska jacek kurski chuj ci w dziaslo", "marcin");
        Playfair playfair = new Playfair("cfhbe yltbyficf ugkthr mrelt tsixsm ioqm ia z fxmrtkkZ", "marcin");
        //playfair.displayMatrix();
        playfair.decrypt();
        //playfair.encrypt();
        System.out.print(playfair.getOutput());
*/
        //Vigener vigener = new Vigener("tajny tekst","klucz");
//        Vigener vigener = new Vigener("ATTACKATDAWN","lemon");
//        vigener.encrypt();
        //Vigener vigener = new Vigener("dldpxdpeus","klucz");
        //Vigener vigener = new Vigener("LXFOPVEFRNHR","LEMONLEMONLE");
//        vigener.displayMatrix();
        //vigener.decrypt();

//        System.out.println("getOutput() " + vigener.getOutput());
        //launch(args);

//        MorseCode morseCode = new MorseCode("sos jednostka uszkodzona N 21 5 W 78 7 niedaleko Karaibów  sos");
//        morseCode.encrypt();
        MorseCode morseCode = new MorseCode("... --- ... / .--- . -.. -. --- ... - -.- .- / ..- ... --.. -.- --- -.. --.. --- -. .- / -. / ..--- .---- / ..... / .-- / --... ---.. / --... / -. .. . -.. .- .-.. . -.- --- / -.- .- .-. .- .. -... --- .-- / / ... --- ... ");
        //morseCode.decrypt();
        morseCode.soundMorseCode("... --- ... / .--- . -.. -. --- ... - -.- .- ");
        //System.out.println("getOutput() " + morseCode.getOutput());

        /*Homophonic homophonic = new Homophonic();
        //homophonic.setInput("dziala");
        //homophonic.encrypt();
        homophonic.setInput("166126103210");
        homophonic.decrypt();
        System.out.println(homophonic.getOutput());

        AES ecb = new AES("CBC");
        ecb.setInputString("Twój stary najebany na wersalce");
        ecb.setKeyString("thisisa128bitkey");
        //ecb.generateIv();
        ecb.setIv("RKA2IpuGQlBJAez6276obQ==");
        System.out.println(ecb.getIvString());
        //ecb.encrypt();
        //System.out.println(ecb.getOutputString());
        ecb.setInputString("KIGeBzGI8YGfqNL3YDge5omW52A9dO6g3IuKkjyzN/r0GmsgkHjuSCGhPN6VZlQ4");
        ecb.decrypt();
        //System.out.println(ecb.getOutputString());
        //System.out.println(ecb.getKeyString());*/


        //morseCode.soundMorseCode("... --- ... / .--- . -.. -. --- ... - -.- .- ");
        launch(args);
    }
}
