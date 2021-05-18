import Classes.RSA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RSAEncryptionInterfaceController {

    @FXML
    private TextField pTextField;
    @FXML
    private TextField qTextField;
    @FXML
    private TextArea plainTextTextArea;
    @FXML
    private TextArea encryptedTextTextArea;

    private RSA rsa;

    public RSAEncryptionInterfaceController() {
        rsa = new RSA();
    }

    @FXML
    void initialize() {
        qTextField.getStyleClass().add("rsa-text-field");
        pTextField.getStyleClass().add("rsa-text-field");
    }

    public void backButtonPressed(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("FXMLFiles/ChooseEncryptionMethod.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(newRoot));
    }

    public void runButtonPressed(ActionEvent actionEvent) {
        rsa.setP(Integer.valueOf(pTextField.getText()));
        rsa.setQ(Integer.valueOf(qTextField.getText()));
        rsa.setMessage(plainTextTextArea.getText());
        rsa.encrypt();
        encryptedTextTextArea.setText(rsa.getOutput());
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
                myWriter.write(rsa.getOutput());
                myWriter.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
