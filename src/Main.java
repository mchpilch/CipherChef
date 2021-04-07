import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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

        /*Monoalphabetic mono = new Monoalphabetic();
        mono.setInput("kupa i troche gowna");
        mono.encrypt();
        System.out.println(mono.getOutput());*/

        launch(args);
    }
}
