package ShoppingCenter.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    @FXML
    public Text loginMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;

    @FXML
    public void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isEmpty()) {
            loginMessage.setText("Please type in a username!");
            return;
        }

        if (password == null || password.isEmpty()) {
            loginMessage.setText("Password cannot be empty");
            return;
        }

        if (username.equals("client") && password.equals("client")) {
            loginMessage.setText("Logged in as client!");
            return;
        }

        if (username.equals("manager") && password.equals("manager")) {
            loginMessage.setText("Logged in as manager!");
            return;
        }

        loginMessage.setText("Incorrect login!");
    }
}
