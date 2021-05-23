package Controllers;

import Classes.Monoalphabetic;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MonoalphabeticDecryptionInterfaceController {

    @FXML
    private GridPane replacementGridPane;

    @FXML
    private TextArea plainTextTextArea;

    @FXML
    private TextArea encryptedTextTextArea;

    private StackPane gridStackPane[][];
    private Monoalphabetic monoalphabetic;
    int lettersCount;
    Map<Label, ComboBox> replacement;
    private Label[] polishLetterLabel;
    private ComboBox[] replacementLetterComboBox;
    Timeline timeline;
    //private boolean modification;

    public MonoalphabeticDecryptionInterfaceController() {
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
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.2), e -> {  // <----- Wątek
                    refresh();
                })
        );

        initializeGridPane();

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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

            replacementLetterComboBox[index].getItems().add(monoalphabetic.getReplacement().get(letter));
            replacementLetterComboBox[index].setValue(monoalphabetic.getReplacement().get(letter));
            replacementLetterComboBox[index].getItems().add(new Character('-'));

            index++;
        }
    }

    private void refresh() {
        Character oldChar, newChar;
        for( int i = 0; i < lettersCount; i++) {
            oldChar = monoalphabetic.getReplacement().get(polishLetterLabel[i].getText().charAt(0));
            newChar = (Character) replacementLetterComboBox[i].getValue();

            if(!oldChar.equals(newChar)) {
                monoalphabetic.getReplacement().replace(polishLetterLabel[i].getText().charAt(0), newChar);
                for (int j = 0; j < lettersCount; j++) {
                    replacementLetterComboBox[j].getItems().remove(newChar);
                    if ( j != i) replacementLetterComboBox[j].getItems().add(oldChar);
                }
                replacementLetterComboBox[i].getItems().add(newChar);
                replacementLetterComboBox[i].setValue(newChar);
                //((ComboBoxBaseSkin) replacementLetterComboBox[i].getSkin()).getDisplayNode();
                //replacementLetterComboBox[i].getItems().remove(newChar);

                System.out.println("Różne");
                System.out.println(oldChar);
                System.out.println(newChar);
                System.out.println(replacementLetterComboBox[i].getValue());
                System.out.println("--------");
            }
        }
    }

    public void backButtonPressed(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("/FXMLFiles/ChooseDecryptionMethod.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(newRoot);
        scene.getStylesheets().add("CSS/style.css");
        stageTheEventSourceNodeBelongs.setScene(scene);
    }

    public void encryptButtonPressed(ActionEvent actionEvent) {
        monoalphabetic.setInput(encryptedTextTextArea.getText());
        monoalphabetic.decrypt();
        plainTextTextArea.setText(monoalphabetic.getOutput());
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
                myWriter.write(monoalphabetic.getOutput());
                myWriter.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void setDefaultReplacementButtonPressed(ActionEvent actionEvent) {
        timeline.stop();
        monoalphabetic.setDefaultReplacement();
        int index = 0;
        for (Character letter : monoalphabetic.getReplacement().keySet()) {
            replacementLetterComboBox[index].getItems().clear();
            replacementLetterComboBox[index].getItems().add(monoalphabetic.getReplacement().get(letter));
            replacementLetterComboBox[index].setValue(monoalphabetic.getReplacement().get(letter));
            replacementLetterComboBox[index].getItems().add(new Character('-'));

            index++;
        }
        timeline.play();
    }
}
