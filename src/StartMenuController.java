import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartMenuController {

    @FXML
    private Button encryptionButton;
    @FXML
    private Button decryptionButton;
    @FXML
    private Button authorsButton;
    @FXML
    private Button exitButton;

    public StartMenuController(){

    }

    @FXML
    void initialize(){

    }

    public void encryptionButtonPressed(ActionEvent actionEvent) throws IOException {  // tutaj otworzy sie okno z wyborem metody szyfrowania
        Parent newRoot = FXMLLoader.load(getClass().getResource("FXMLFiles/ChooseEncryptionMethod.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(newRoot));
    }

    public void decryptionButtonPressed(ActionEvent actionEvent) throws IOException { // tutaj otworzy się okno z wyborem metody deszyfrowania
        Parent newRoot = FXMLLoader.load(getClass().getResource("FXMLFiles/ChooseDecryptionMethod.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(newRoot));
    }

    public void authorsButtonPressed(ActionEvent actionEvent) {
        // tutaj otworzy się okno przeglądarki z naszymi kontami na gicie
    }

    public void exitButtonPressed(ActionEvent actionEvent) {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }
}
