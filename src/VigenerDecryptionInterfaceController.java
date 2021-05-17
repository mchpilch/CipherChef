import Classes.Playfair;
import Classes.Vigener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class VigenerDecryptionInterfaceController {
    @FXML
    private TextField keyTextField;
    @FXML
    private TextArea plainTextTextArea;
    @FXML
    private TextArea encryptedTextTextArea;
    @FXML
    private GridPane matrixGridPane;

    private Vigener vigener;
    private int matrixDimension;
    private StackPane[][] matrixStackPane;
    private Label[][] matrixCharactersLabel;



}
