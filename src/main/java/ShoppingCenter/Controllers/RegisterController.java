package ShoppingCenter.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ShoppingCenter.Exceptions.UsernameAlreadyExistsException;
import ShoppingCenter.Services.UserService;
import javafx.scene.text.Text;

public class RegisterController< choice > {
    @FXML
    private Text LoginMessage;
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


    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Manager");
    }

    @FXML
    private String getChoice()
    {
        String choice = (String) this.role.getValue();
        LoginMessage.setText("Picked " + choice);
        return choice;
    }
    @FXML
    public void handleSigninButtonAction()
    {
        try {
            if(getChoice().equals("Manager")) {
                UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue()
                        ,nameField.getText(),numberField.getText(),"-",storeField.getText() );
            }
            if(getChoice().equals("Client")) {
                 UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue()
                       ,nameField.getText(),numberField.getText(),addressField.getText(),"-" );
            }
            LoginMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            LoginMessage.setText(e.getMessage());
        }
    }
}
