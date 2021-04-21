import Classes.EncryptionMethod;
import Classes.Monoalphabetic;
import Classes.Playfair;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ChooseEncryptionMethodController {

    ArrayList<EncryptionMethod> encryptionMethods = new ArrayList<>();

    @FXML
    private ComboBox chooseEncryptionMethodComboBox;

    public ChooseEncryptionMethodController() {
        encryptionMethods.add(new Playfair());
    }

    @FXML
    void initialize(){
        chooseEncryptionMethodComboBox.getItems().add(encryptionMethods);
    }

    public void backButtonPressed(ActionEvent actionEvent) throws IOException { //powrót do menu startowego
        Parent newRoot = FXMLLoader.load(getClass().getResource("FXMLFiles/StartMenu.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(newRoot));
    }

    public void chooseEncryptionMethodComboBoxOnAction(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("FXMLFiles/PlayfairInterface.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(newRoot));
    }
}
