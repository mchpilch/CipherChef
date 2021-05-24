package Controllers;

import Classes.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ChooseEncryptionMethodController {

    ArrayList<EncryptionMethod> encryptionMethods = new ArrayList<>();
    EventHandler<MouseEvent> buttonEventHandler;

    @FXML
    private VBox buttonsVBox;

    private Button[] button;

    public ChooseEncryptionMethodController() {

        buttonEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Button btn = (Button) e.getSource();
                String methodName = btn.getText();
                String path = "/FXMLFiles/" + methodName + "EncryptionInterface.fxml";

                Parent newRoot = null;
                try {
                    newRoot = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
                Scene scene = new Scene(newRoot);
                scene.getStylesheets().add("CSS/style.css");
                stageTheEventSourceNodeBelongs.setScene(scene);
                stageTheEventSourceNodeBelongs.setResizable(false);
            }
        };

        encryptionMethods.add(new Monoalphabetic());
        encryptionMethods.add(new Homophonic());
        encryptionMethods.add(new MorseCode());
        encryptionMethods.add(new Playfair());
        encryptionMethods.add(new Vigener());
        encryptionMethods.add(new AES());
        encryptionMethods.add(new RSA());
        encryptionMethods.add(new ElGamal());

        button = new Button[encryptionMethods.size()];
    }

    @FXML
    void initialize(){
        initializeButtons();
    }

    private void initializeButtons() {
        for (int i = 0; i < encryptionMethods.size(); i++) {
            button[i] = new Button();
            button[i].addEventHandler(MouseEvent.MOUSE_CLICKED, buttonEventHandler);
            button[i].setText(encryptionMethods.get(i).getName());
            button[i].getStyleClass().add("enc-methods-btn");
            button[i].setPrefWidth(350);
            buttonsVBox.getChildren().add(button[i]);
        }
    }

    public void backButtonPressed(ActionEvent actionEvent) throws IOException { //powrót do menu startowego
        Parent newRoot = FXMLLoader.load(getClass().getResource("/FXMLFiles/StartMenu.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(newRoot));
        stageTheEventSourceNodeBelongs.setResizable(false);
    }

}
