import Classes.Homophonic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HomophonicEncryptionInterfaceController {

    private Homophonic homophonic;
    private int letterCount;

    private TableColumn<Character, Integer> replacementTableColumn[];

    @FXML
    private AnchorPane setReplacementAnchorPane;

    @FXML
    private VBox labelsVBox;

    @FXML
    private VBox textFieldsVBox;

    @FXML
    private TextArea plainTextTextArea;

    @FXML
    private TextArea encryptedTextTextArea;

    private Label letterLabel[];
    private TextField replacementTextField[];

    public HomophonicEncryptionInterfaceController() {
        homophonic = new Homophonic();
        letterCount = homophonic.getLetterCount();
        letterLabel = new Label[letterCount];
        replacementTextField = new TextField[letterCount];
    }

    @FXML
    void initialize() {
        setReplacementAnchorPane.setVisible(false);
        setReplacementAnchorPane.setDisable(true);
        initializeReplacementWindow();
    }


    private void initializeReplacementWindow() {
        for (int i = 0; i < letterCount; i++) {
            letterLabel[i] = new Label();
            letterLabel[i].setText(String.valueOf(homophonic.getLetters()[i]) + " =");
            letterLabel[i].setFont(new Font("Arial", 18));
            letterLabel[i].setTextFill(Color.valueOf("#0095cb"));
            labelsVBox.getChildren().add(letterLabel[i]);

            replacementTextField[i] = new TextField();
            replacementTextField[i].getStyleClass().add("text-field-homo");
            replacementTextField[i].setText(homophonic.getReplacement(letterLabel[i].getText().charAt(0)));
            replacementTextField[i].setFont(new Font("Arial", 15));
            replacementTextField[i].setPadding(new Insets(1));
            textFieldsVBox.getChildren().add(replacementTextField[i]);
        }
    }

    public void backButtonPressed(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("FXMLFiles/ChooseEncryptionMethod.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(newRoot);
        scene.getStylesheets().add("CSS/style.css");
        stageTheEventSourceNodeBelongs.setScene(scene);
    }

    public void encryptButtonPressed(ActionEvent actionEvent) {
        homophonic.setInput(plainTextTextArea.getText());
        homophonic.encrypt();
        encryptedTextTextArea.setText(homophonic.getOutput());
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
                myWriter.write(homophonic.getOutput());
                myWriter.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void setReplacementButtonPressed(ActionEvent actionEvent) throws IOException {
        setReplacementAnchorPane.setVisible(true);
        setReplacementAnchorPane.setDisable(false);
    }

    public void confirmButtonPressed(ActionEvent actionEvent) {
        for (int i = 0; i < letterCount; i++) {
            homophonic.setReplacement(replacementTextField[i].getText(), letterLabel[i].getText().charAt(0));
        }
        homophonic.displayReplacement();
        setReplacementAnchorPane.setVisible(false);
        setReplacementAnchorPane.setDisable(true);
    }

    public void closeButtonPressed(ActionEvent actionEvent) {
        setReplacementAnchorPane.setVisible(false);
        setReplacementAnchorPane.setDisable(true);
    }
}
