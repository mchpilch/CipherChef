package Controllers;

import Classes.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ChooseDecryptionMethodController {

    ArrayList<EncryptionMethod> decryptionMethods = new ArrayList<>();
    EventHandler<MouseEvent> buttonEventHandler;

    @FXML
    private VBox buttonsVBox;

    private Button[] button;

    public ChooseDecryptionMethodController() {

        buttonEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Button btn = (Button) e.getSource();
                String methodName = btn.getText();
                String path = "/FXMLFiles/" + methodName + "DecryptionInterface.fxml";

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

        decryptionMethods.add(new Monoalphabetic());
        decryptionMethods.add(new Homophonic());
        decryptionMethods.add(new MorseCode());
        decryptionMethods.add(new Playfair());
        decryptionMethods.add(new Vigener());
        decryptionMethods.add(new AES());

        button = new Button[decryptionMethods.size()];
    }

    @FXML
    void initialize(){
        initializeButtons();
    }

    private void initializeButtons() {
        for (int i = 0; i < decryptionMethods.size(); i++) {
            button[i] = new Button();
            button[i].addEventHandler(MouseEvent.MOUSE_CLICKED, buttonEventHandler);
            button[i].setText(decryptionMethods.get(i).getName());
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
