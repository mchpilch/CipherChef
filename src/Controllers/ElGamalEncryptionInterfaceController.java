package Controllers;

import Classes.ElGamal;
import Classes.RSA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ElGamalEncryptionInterfaceController {
    @FXML
    private TextField qTextField;
    @FXML
    private TextArea plainTextTextArea;
    @FXML
    private TextArea encryptedTextTextArea;

    Alert alert;
    private ElGamal elGamal;

    public ElGamalEncryptionInterfaceController() {
        alert = new Alert(Alert.AlertType.ERROR);
        elGamal = new ElGamal();
    }

    @FXML
    void initialize() {
        qTextField.getStyleClass().add("rsa-text-field");
        //qTextField.getStyleClass().add();
    }

    public void backButtonPressed(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("/FXMLFiles/ChooseEncryptionMethod.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(newRoot);
        scene.getStylesheets().add("CSS/style.css");
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.setResizable(false);
    }

    public void runButtonPressed(ActionEvent actionEvent) {
        elGamal.setQ(Integer.valueOf(qTextField.getText()));
        elGamal.setA(Integer.valueOf(qTextField.getText()));
        elGamal.setX(Integer.valueOf(qTextField.getText()));
        elGamal.setY(Integer.valueOf(qTextField.getText()),elGamal.getA(),elGamal.getX());
        elGamal.setMessage(plainTextTextArea.getText());
        elGamal.encrypt();
        elGamal.getInfo();
        encryptedTextTextArea.setText(elGamal.getOutput());
    }

    public void saveButtonPressed(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save file");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("pliki TXT", "*.txt"));
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                FileWriter myWriter = new FileWriter(file.getName());
                myWriter.write(elGamal.getOutput());
                myWriter.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
