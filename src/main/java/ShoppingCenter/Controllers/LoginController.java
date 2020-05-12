package ShoppingCenter.Controllers;


import ShoppingCenter.Model.User;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ShoppingCenter.Exceptions.UsernameAlreadyExistsException;
import ShoppingCenter.Services.UserService;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController< choice > {

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
        private void getChoice()
        {
            String choice =this.role.getValue();
            LoginMessage.setText("Picked " + choice);
        }
        @FXML
        public void handleRegisterButtonAction() throws  IOException
        {
            try {
                Stage stage = (Stage) LoginMessage.getScene().getWindow();
                Parent register = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
                Scene scene = new Scene(register, 600, 400);
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
