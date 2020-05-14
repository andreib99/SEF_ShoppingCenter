package ShoppingCenter.Controllers;

import ShoppingCenter.Model.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Objects;

import static ShoppingCenter.Services.UserService.managers;


public class ViewStoresController {
    @FXML
    public TableView<Manager> storeTable;

    @FXML
    public TableColumn<Manager, String> Store_name;
    public TableColumn<Manager, String> Manager_name;
    public TableColumn<Manager, String> Manager_number;

    @FXML
    public void initialize() {
        Store_name.setCellValueFactory(new PropertyValueFactory<>("Store_name"));
        Manager_name.setCellValueFactory(new PropertyValueFactory<>("username"));
        Manager_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        storeTable.setItems(lines);
    }

    private ObservableList<Manager> lines = FXCollections.observableArrayList(managers);

    public void handleLoginButtonAction()
    {
        try {
            Stage stage = (Stage) storeTable.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleEditButtonAction()
    {
        try {
            Stage stage = (Stage) storeTable.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("edit_profile.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
