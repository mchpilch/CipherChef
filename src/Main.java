import Classes.Playfair;
import Classes.Vigener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Classes.EncryptionMethod;
import Classes.Monoalphabetic;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLFiles/StartMenu.fxml"));
        primaryStage.setTitle("CipherChef");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
    /*
        Monoalphabetic mono = new Monoalphabetic();
        mono.setInput("Litwo Ojczyzno moja Ty jesteś jak zdrowie Ile cię stracił Dziś piękność twą w szlacheckim stanie trudno zaradzić wolał gości Żydom do nowej mody odsyłać konie porzucone same szczypiąc trawę ciągnęły powoli pod lasem zwaliska");
        mono.decrypt();
        mono.displayTextLettersFrequency();
    */


        Playfair playfair = new Playfair("jebac telewizje polska jacek kurski chuj ci w dziaslo", "marcin");
        //Playfair playfair = new Playfair("cfhbe yltbyficf ugkthr mrelt tsixsm ioqm ia z fxmrtkkz", "marcin");
        playfair.displayMatrix();
        //playfair.decrypt();
        playfair.encrypt();
        System.out.print(playfair.getOutput());

/*
        Vigener vigener = new Vigener("tajny tekst","klucz");
        vigener.displayMatrix();
        System.out.println("getOutput() " + vigener.getOutput());
        */
        launch(args);
    }
}
