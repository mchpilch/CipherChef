import Classes.Monoalphabetic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MonoalphabeticEncryptionInterfaceController {

    @FXML
    private GridPane replacementGridPane;

    private StackPane gridStackPane[][];
    private Monoalphabetic monoalphabetic;
    int lettersCount;
    Map<Label, ComboBox> replacement;
    private Label[] polishLetterLabel;
    private ComboBox[] replacementLetterComboBox;

    public MonoalphabeticEncryptionInterfaceController() {
        monoalphabetic = new Monoalphabetic();
        lettersCount = monoalphabetic.getPolishLettersCount();
        gridStackPane = new StackPane[lettersCount][2];
        replacement = new HashMap<Label, ComboBox>();
        polishLetterLabel = new Label[lettersCount];
        replacementLetterComboBox = new ComboBox[lettersCount];

        constructMap();
    }

    private void constructMap() {
        for (int i = 0; i < lettersCount; i++) {
            polishLetterLabel[i] = new Label();
            replacementLetterComboBox[i] = new ComboBox();
            replacement.put(polishLetterLabel[i], replacementLetterComboBox[i]);
        }
    }

    @FXML
    void initialize() {
        initializeGridPane();
    }

    private void initializeGridPane() {
        for(int i = 0; i < lettersCount; i++){
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / lettersCount);
            replacementGridPane.getColumnConstraints().add(colConst);
        }
        for(int i = 0; i < 2; i++){
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / 2);
            replacementGridPane.getRowConstraints().add(rowConst);
        }

        for (int i = 0; i < lettersCount; i++) {
            for (int j = 0; j < 2; j++) {
                fillGridWithStackPane(i, j);
            }
        }

        for (int i = 0; i < lettersCount; i++) {
            addLabelAndComboBoxToGridPane(i);
        }
    }

    private void fillGridWithStackPane(int colIndex, int rowIndex) {
        gridStackPane[colIndex][rowIndex] = new StackPane();
        replacementGridPane.add(gridStackPane[colIndex][rowIndex], colIndex, rowIndex);
    }

    private void addLabelAndComboBoxToGridPane(int colIndex) {
        gridStackPane[colIndex][0].getChildren().add(polishLetterLabel[colIndex]);
        gridStackPane[colIndex][1].getChildren().add(replacementLetterComboBox[colIndex]);
    }

    public void backButtonPressed(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("FXMLFiles/ChooseEncryptionMethod.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(newRoot));
    }

    public void encryptButtonPressed(ActionEvent actionEvent) {
    }

    public void saveButtonPressed(ActionEvent actionEvent) {
    }
}
