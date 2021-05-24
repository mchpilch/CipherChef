package Controllers;

import Classes.Vigener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class VigenerEncryptionInterfaceController {
    @FXML
    private TextField keyTextField;
    @FXML
    private TextArea plainTextTextArea;
    @FXML
    private TextArea encryptedTextTextArea;
    @FXML
    private GridPane matrixGridPane;

    Alert alert;
    private Vigener vigener;
    private int matrixDimension;
    private StackPane[][] matrixStackPane;
    private Label[][] matrixCharactersLabel;

    public VigenerEncryptionInterfaceController() {
        alert = new Alert(Alert.AlertType.ERROR);
        vigener = new Vigener();
        matrixDimension = vigener.getAlphabet().length();
        matrixStackPane = new StackPane[matrixDimension][matrixDimension];
        matrixCharactersLabel = new Label[matrixDimension][matrixDimension];
    }

    @FXML
    void initialize() {
        initializeMatrix();
        displayMatrix();
    }

    private void initializeMatrix() {
        for(int i = 0; i < matrixDimension; i++){
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / matrixDimension);
            matrixGridPane.getColumnConstraints().add(colConst);
        }
        for(int i = 0; i < matrixDimension; i++){
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / matrixDimension);
            matrixGridPane.getRowConstraints().add(rowConst);
        }

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                fillGridWithStackPane(i, j);
                addLabelToGridPane(i, j);
            }
        }
    }

    private void fillGridWithStackPane(int colIndex, int rowIndex) {
        matrixStackPane[colIndex][rowIndex] = new StackPane();
        matrixGridPane.add(matrixStackPane[colIndex][rowIndex], colIndex, rowIndex);
    }

    private void addLabelToGridPane(int colIndex, int rowIndex){
        matrixCharactersLabel[colIndex][rowIndex] = new Label();
        matrixCharactersLabel[colIndex][rowIndex].setText("-");
        matrixCharactersLabel[colIndex][rowIndex].setFont(new Font("Arial", 12));
        matrixCharactersLabel[colIndex][rowIndex].setTextFill(Color.valueOf("#0095cb"));
        matrixStackPane[colIndex][rowIndex].getChildren().add(matrixCharactersLabel[colIndex][rowIndex]);
    }

    private void displayMatrix() {
        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrixCharactersLabel[i][j].setText(String.valueOf(vigener.getMatrix()[j][i]));
            }
        }
    }

    public void backButtonPressed(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("/FXMLFiles/ChooseEncryptionMethod.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(newRoot);
        scene.getStylesheets().add("CSS/style.css");
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.setResizable(false);
    }

    public void keyTextFieldFilled(ActionEvent actionEvent) {
    }

    public void encryptButtonPressed(ActionEvent actionEvent) {
        if (vigener.checkInput(plainTextTextArea.getText()) && !plainTextTextArea.getText().equals("")) {
            if (vigener.checkInput(keyTextField.getText()) && !keyTextField.getText().equals("")) {
                vigener.setInput(plainTextTextArea.getText());
                vigener.setKey(keyTextField.getText());
                vigener.encrypt();
                encryptedTextTextArea.setText(vigener.getOutput());
            } else {
                alert.setTitle("Input Error");
                alert.setContentText("Nieprawidłowy klucz");
                alert.showAndWait();
            }
        } else {
            alert.setTitle("Input Error");
            alert.setContentText("Nieprawidłowy tekst jawny");
            alert.showAndWait();
        }
    }

    public void saveButtonPressed(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        //System.out.println(playfair.getOutput());
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("pliki TXT", "*.txt"));
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                FileWriter myWriter = new FileWriter(file.getName());
                myWriter.write(vigener.getOutput());
                myWriter.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
