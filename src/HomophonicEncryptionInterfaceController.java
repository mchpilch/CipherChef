import Classes.Homophonic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
            replacementTextField[i].setText(homophonic.getReplacement(letterLabel[i].getText().charAt(0)));
            replacementTextField[i].setFont(new Font("Arial", 15));
            replacementTextField[i].setPadding(new Insets(1));
            textFieldsVBox.getChildren().add(replacementTextField[i]);
        }
    }
    /*private void initializeMatrix() {
        for(int i = 0; i < letterCount; i++){
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / letterCount);
            replacementGridPane.getColumnConstraints().add(colConst);
        }
        for(int i = 0; i < 2; i++){
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / 2);
            replacementGridPane.getRowConstraints().add(rowConst);
        }

        for (int i = 0; i < letterCount; i++) {
            for (int j = 0; j < 2; j++) {
                fillGridWithStackPane(i, j);
            }
            addLabelToGridPane(i, 0);
        }
    }

    private void fillGridWithStackPane(int colIndex, int rowIndex) {
        matrixStackPane[colIndex][rowIndex] = new StackPane();
        replacementGridPane.add(matrixStackPane[colIndex][rowIndex], colIndex, rowIndex);
    }

    private void addLabelToGridPane(int colIndex, int rowIndex){
        matrixCharactersLabel[colIndex] = new Label();
        matrixCharactersLabel[colIndex].setText("-");
        matrixCharactersLabel[colIndex].setFont(new Font("Arial", 24));
        matrixCharactersLabel[colIndex].setTextFill(Color.valueOf("#0095cb"));
        matrixStackPane[colIndex][rowIndex].getChildren().add(matrixCharactersLabel[colIndex]);
    }*/

    public void backButtonPressed(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("FXMLFiles/ChooseEncryptionMethod.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(newRoot));
    }

    public void encryptButtonPressed(ActionEvent actionEvent) {
    }

    public void saveButtonPressed(ActionEvent actionEvent) {
    }

    public void setReplacementButtonPressed(ActionEvent actionEvent) throws IOException {
        setReplacementAnchorPane.setVisible(true);
        setReplacementAnchorPane.setDisable(false);
    }

    public void confirmButtonPressed(ActionEvent actionEvent) {
        for (int i = 0; i < letterCount; i++) {
            homophonic.setReplacement(replacementTextField[i].getText());
        }
    }

    public void closeButtonPressed(ActionEvent actionEvent) {
        setReplacementAnchorPane.setVisible(false);
        setReplacementAnchorPane.setDisable(true);
    }
}
