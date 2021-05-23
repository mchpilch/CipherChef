package Controllers;

import Classes.Playfair;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PlayfairEncryptionInterfaceController {

    @FXML
    private TextField keyTextField;
    @FXML
    private TextArea plainTextTextArea;
    @FXML
    private TextArea encryptedTextTextArea;
    @FXML
    private GridPane matrixGridPane;

    private Playfair playfair;
    private int matrixDimension;
    private StackPane[][] matrixStackPane;
    private Label[][] matrixCharactersLabel;

    public PlayfairEncryptionInterfaceController() {
        playfair = new Playfair();
        matrixDimension = 5;
        matrixStackPane = new StackPane[matrixDimension][matrixDimension];
        matrixCharactersLabel = new Label[matrixDimension][matrixDimension];
    }

    @FXML
    void initialize() {
        initializeMatrix();
        System.out.println(matrixStackPane[4][4].getChildren());
    }

    void refresh() {
        displayMatrix();
        displayOutput();
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
        matrixCharactersLabel[colIndex][rowIndex].setFont(new Font("Arial", 24));
        matrixCharactersLabel[colIndex][rowIndex].setTextFill(Color.valueOf("#0095cb"));
        matrixStackPane[colIndex][rowIndex].getChildren().add(matrixCharactersLabel[colIndex][rowIndex]);
    }

    private void displayMatrix() {
        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrixCharactersLabel[i][j].setText(String.valueOf(playfair.getMatrix()[j][i])); //ideksy macierzy w klasie Playfair i macierzy wyswietlanej są odwrócone, trzeba na to uważać
            }
        }
    }

    private void displayOutput() {
        encryptedTextTextArea.setText(playfair.getOutput());
    }

    public void backButtonPressed(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("/FXMLFiles/ChooseEncryptionMethod.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(newRoot);
        scene.getStylesheets().add("CSS/style.css");
        stageTheEventSourceNodeBelongs.setScene(scene);
    }

    public void keyTextFieldFilled(ActionEvent actionEvent) {
    }

    public void encryptButtonPressed(ActionEvent actionEvent) {
        playfair.setKey(keyTextField.getText());
        playfair.setInput(plainTextTextArea.getText());
        playfair.encrypt();
        refresh();
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
                myWriter.write(playfair.getOutput());
                myWriter.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
