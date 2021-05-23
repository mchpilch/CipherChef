package Controllers;

import Classes.MorseCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MorseCodeEncryptionInterfaceController {

    @FXML
    private TextArea plainTextTextArea;
    @FXML
    private TextArea encryptedTextTextArea;
    @FXML
    private VBox leftColumnVBox;
    @FXML
    private VBox rightColumnVBox;

    private MorseCode morseCode;
    private Label[] legendLabel;
    private int charactersCount;

    public MorseCodeEncryptionInterfaceController() {
        morseCode = new MorseCode();
        charactersCount = morseCode.getMorseToLetter().size();
        legendLabel = new Label[charactersCount];
    }

    @FXML
    void initialize(){
        initializeLegend();
    }

    private void initializeLegend() {
        int index = 0;
        String str;
        for ( String code : morseCode.getMorseToLetter().keySet()) {
            str = morseCode.getMorseToLetter().get(code) + " = " + code;
            legendLabel[index] = new Label();
            legendLabel[index].setText(str);
            legendLabel[index].setPadding(new Insets(2, 0, 2, 0));
            legendLabel[index].getStyleClass().add("morse-legend-label");
            if (index < charactersCount/2 + 1) {
                leftColumnVBox.getChildren().add(legendLabel[index]);
            } else {
                rightColumnVBox.getChildren().add(legendLabel[index]);
            }
            index++;
        }
    }

    public void backButtonPressed(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("/FXMLFiles/ChooseEncryptionMethod.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(newRoot);
        scene.getStylesheets().add("CSS/style.css");
        stageTheEventSourceNodeBelongs.setScene(scene);
    }

    public void encryptButtonPressed(ActionEvent actionEvent) {
        morseCode.setInput(plainTextTextArea.getText());
        morseCode.encrypt();
        encryptedTextTextArea.setText(morseCode.getOutput());
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
                myWriter.write(morseCode.getOutput());
                myWriter.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void playButtonPressed(ActionEvent actionEvent) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        morseCode.soundMorseCode(morseCode.getOutput());
    }
}
