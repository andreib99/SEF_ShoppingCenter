package ShoppingCenter.Controllers;

import ShoppingCenter.Exceptions.StoreAlreadyExistsException;
import ShoppingCenter.Exceptions.UsernameAlreadyExistsException;
import ShoppingCenter.Services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;


public class EditProfileController {

    @FXML
    private Text EditMessage;
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



    public void handleEditButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String store = storeField.getText();
        String number = numberField.getText();
        String address = addressField.getText();
        String name = nameField.getText();

        try {
            if (UserService.getCurrent_manager().equals("")) {
                UserService.modifyClient(username, password, name, number, address);
            }
            if (UserService.getCurrent_client().equals("")) {
                UserService.modifyManager(username, password, name, number, store);
            }
            EditMessage.setText("Successfully modified!");

        }catch(UsernameAlreadyExistsException | StoreAlreadyExistsException e)
        {
            EditMessage.setText(e.getMessage());
        }
    }

    public void handleViewStoresButtonAction()
    {
        try {
            Stage stage = (Stage) EditMessage.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_stores.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }





}
