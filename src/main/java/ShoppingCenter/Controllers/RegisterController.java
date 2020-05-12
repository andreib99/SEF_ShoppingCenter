package ShoppingCenter.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController< choice > {
    @FXML
    private TextField nameField;
    @FXML
    private TextField numberField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField storeField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    public RegisterController() {
    }

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Manager");
    }
    //private String choice;
  // private String choice = new String( (String)role.getValue());

}
