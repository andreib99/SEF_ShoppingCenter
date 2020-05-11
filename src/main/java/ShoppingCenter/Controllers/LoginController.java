package ShoppingCenter.Controllers;


import ShoppingCenter.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ShoppingCenter.Exceptions.UsernameAlreadyExistsException;
import ShoppingCenter.Services.UserService;

    public class LoginController {

        @FXML
        private Text LoginMessage;
        @FXML
        private PasswordField passwordField;
        @FXML
        private TextField usernameField;
        @FXML
        private ChoiceBox<String> role;

        @FXML
        public void initialize() {
            role.getItems().addAll("Client", "Manager");
        }

        @FXML
        public void handleLoginButtonAction() {
            try {
                UserService.verifyUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
                LoginMessage.setText("Account created successfully!");
            } catch (UsernameAlreadyExistsException e) {
                LoginMessage.setText(e.getMessage());
            }
        }
    }
