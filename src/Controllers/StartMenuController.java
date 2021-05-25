package Controllers;

import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;

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
        Parent newRoot = FXMLLoader.load(getClass().getResource("/FXMLFiles/ChooseEncryptionMethod.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(newRoot);
        scene.getStylesheets().add("CSS/style.css");
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.setResizable(false);
    }

    public void decryptionButtonPressed(ActionEvent actionEvent) throws IOException { // tutaj otworzy się okno z wyborem metody deszyfrowania
        Parent newRoot = FXMLLoader.load(getClass().getResource("/FXMLFiles/ChooseDecryptionMethod.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(newRoot);
        scene.getStylesheets().add("CSS/style.css");
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.setResizable(false);
    }

    public void authorsButtonPressed(ActionEvent actionEvent) throws IOException {
        // tutaj otworzy się okno przeglądarki z naszymi kontami na gicie
        java.awt.Desktop.getDesktop().browse(URI.create("https://github.com/michpil19"));
        java.awt.Desktop.getDesktop().browse(URI.create("https://github.com/OperatorKodu"));
    }

    public void exitButtonPressed(ActionEvent actionEvent) {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }
}
