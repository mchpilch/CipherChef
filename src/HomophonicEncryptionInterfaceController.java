import Classes.Homophonic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HomophonicEncryptionInterfaceController {

    private Homophonic homophonic;
    private int letterCount;

    private StackPane[][] matrixStackPane;
    private Label[] matrixCharactersLabel;

    @FXML
    private GridPane replacementGridPane;

    public HomophonicEncryptionInterfaceController() {
        homophonic = new Homophonic();
        letterCount = homophonic.getLetterCount();
        matrixStackPane = new StackPane[letterCount][2];
        matrixCharactersLabel = new Label[letterCount];
    }

    @FXML
    void initialize() {
        initializeMatrix();
    }

    private void initializeMatrix() {
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
