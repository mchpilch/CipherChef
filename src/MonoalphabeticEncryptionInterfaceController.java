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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
        gridStackPane = new StackPane[4][lettersCount/2 + 1];
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
        for(int i = 0; i < 4; i++){
            ColumnConstraints colConst = new ColumnConstraints();
            if( i % 2 == 0) {
                colConst.setPercentWidth(80.0 / 4);
            } else {
                colConst.setPercentWidth(120.0 / 4);
            }
            replacementGridPane.getColumnConstraints().add(colConst);
        }
        for(int i = 0; i < lettersCount/2 + 1; i++){
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / (lettersCount/2 + 1));
            replacementGridPane.getRowConstraints().add(rowConst);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < lettersCount/2 + 1; j++) {
                fillGridWithStackPane(i, j);
            }
        }

        for (int i = 0; i < lettersCount; i++) {
                addLabelAndComboBoxToGridPane(i);
        }

        fillLabelsAndComboBoxesWithLetters();
    }

    private void fillGridWithStackPane(int colIndex, int rowIndex) {
        gridStackPane[colIndex][rowIndex] = new StackPane();
        replacementGridPane.add(gridStackPane[colIndex][rowIndex], colIndex, rowIndex);
    }

    private void addLabelAndComboBoxToGridPane(int labelNumber) {
        if (labelNumber <= 17) {
            gridStackPane[0][labelNumber].getChildren().add(polishLetterLabel[labelNumber]);
            gridStackPane[1][labelNumber].getChildren().add(replacementLetterComboBox[labelNumber]);
        } else {
            gridStackPane[2][labelNumber - 18].getChildren().add(polishLetterLabel[labelNumber]);
            gridStackPane[3][labelNumber - 18].getChildren().add(replacementLetterComboBox[labelNumber]);
        }
    }

    private void fillLabelsAndComboBoxesWithLetters() {
        int index = 0;
        for (Character letter : monoalphabetic.getReplacement().keySet()) {
            polishLetterLabel[index].setText(letter.toString());
            polishLetterLabel[index].setFont(new Font("Arial", 16));
            polishLetterLabel[index].setTextFill(Color.valueOf("#0095cb"));

            replacementLetterComboBox[index].getSelectionModel().select(monoalphabetic.getReplacement().get(letter));

            index++;
        }
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
