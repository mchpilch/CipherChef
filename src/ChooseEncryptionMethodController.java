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

public class ChooseEncryptionMethodController {

    ArrayList<EncryptionMethod> encryptionMethods = new ArrayList<>();

    @FXML
    private ComboBox chooseEncryptionMethodComboBox;

    public ChooseEncryptionMethodController() {
        encryptionMethods.add(new Monoalphabetic());
        encryptionMethods.add(new Homophonic());
        encryptionMethods.add(new Playfair());
        encryptionMethods.add(new Vigener());
        encryptionMethods.add(new AES());
    }

    @FXML
    void initialize(){
        chooseEncryptionMethodComboBox.getItems().addAll(encryptionMethods);
    }

    public void backButtonPressed(ActionEvent actionEvent) throws IOException { //powrót do menu startowego
        Parent newRoot = FXMLLoader.load(getClass().getResource("FXMLFiles/StartMenu.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(newRoot));
    }

    public void chooseEncryptionMethodComboBoxOnAction(ActionEvent actionEvent) throws IOException {
        EncryptionMethod choosedMethod = (EncryptionMethod) chooseEncryptionMethodComboBox.getValue();
        String methodName = choosedMethod.getName();
        String path = "FXMLFiles/" + methodName + "EncryptionInterface.fxml";

        Parent newRoot = FXMLLoader.load(getClass().getResource(path));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(newRoot);
        scene.getStylesheets().add("CSS/style.css");
        stageTheEventSourceNodeBelongs.setScene(scene);
    }
}
