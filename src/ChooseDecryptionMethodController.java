import Classes.*;
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
        decryptionMethods.add(new Monoalphabetic());
        decryptionMethods.add(new Playfair());
        decryptionMethods.add(new Homophonic());
        decryptionMethods.add(new AES());
    }

    @FXML
    void initialize(){
        chooseDecryptionMethodComboBox.getItems().addAll(decryptionMethods);
    }

    public void backButtonPressed(ActionEvent actionEvent) throws IOException { //powrót do menu startowego
        Parent newRoot = FXMLLoader.load(getClass().getResource("FXMLFiles/StartMenu.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(newRoot));
    }

    public void chooseDecryptionMethodComboBoxOnAction(ActionEvent actionEvent) throws IOException {
        EncryptionMethod choosedMethod = (EncryptionMethod) chooseDecryptionMethodComboBox.getValue();
        String methodName = choosedMethod.getName();
        String path = "FXMLFiles/" + methodName + "DecryptionInterface.fxml";

        Parent newRoot = FXMLLoader.load(getClass().getResource(path));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(newRoot);
        scene.getStylesheets().add("CSS/style.css");
        stageTheEventSourceNodeBelongs.setScene(scene);
    }
}
