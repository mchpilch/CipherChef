import Classes.EncryptionMethod;
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

public class ChooseDecryptionMethodController {

    ArrayList<EncryptionMethod> decryptionMethods = new ArrayList<>();

    @FXML
    private ComboBox chooseDecryptionMethodComboBox;

    public ChooseDecryptionMethodController() {
        decryptionMethods.add(new Playfair());
    }

    @FXML
    void initialize(){
        chooseDecryptionMethodComboBox.getItems().add(decryptionMethods);
    }

    public void backButtonPressed(ActionEvent actionEvent) throws IOException { //powrót do menu startowego
        Parent newRoot = FXMLLoader.load(getClass().getResource("FXMLFiles/StartMenu.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(newRoot));
    }

    public void chooseDecryptionMethodComboBoxOnAction(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("FXMLFiles/PlayfairDecryptionInterface.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(newRoot));
    }
}
