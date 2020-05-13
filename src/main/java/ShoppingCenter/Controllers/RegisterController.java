package ShoppingCenter.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ShoppingCenter.Exceptions.UsernameAlreadyExistsException;
import ShoppingCenter.Services.UserService;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

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
    private ChoiceBox<String> role;


    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Manager");
    }

    @FXML
    private String getChoice()
    {
        return this.role.getValue();
    }
    @FXML
    public void handleSigningButtonAction()
    {
        if(role.getValue()==null)
        {
            LoginMessage.setText("Need to select a role!");
            return;
        }

        try {
            if(getChoice().equals("Manager")) {
                System.out.println("Hei");
                UserService.addManager(usernameField.getText(), passwordField.getText()
                        ,nameField.getText(),numberField.getText(),storeField.getText() );
            }
            if(getChoice().equals("Client")) {
                 UserService.addClient(usernameField.getText(), passwordField.getText()
                       ,nameField.getText(),numberField.getText(),addressField.getText());
            }
            LoginMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            LoginMessage.setText(e.getMessage());
        }
    }

    public void handleLoginButtonAction()
    {
            try {
                Stage stage = (Stage) LoginMessage.getScene().getWindow();
                Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
                Scene scene = new Scene(store);
                stage.setScene(scene);
            }catch (Exception e){
                e.printStackTrace();
            }
    }

}
