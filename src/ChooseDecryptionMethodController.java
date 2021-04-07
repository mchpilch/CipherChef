import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseDecryptionMethodController {
    public void backButtonPressed(ActionEvent actionEvent) throws IOException { //powrót do menu startowego
        Parent newRoot = FXMLLoader.load(getClass().getResource("FXMLFiles/StartMenu.fxml"));

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(new Scene(newRoot));
    }
}
