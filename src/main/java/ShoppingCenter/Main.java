package ShoppingCenter;

import ShoppingCenter.Services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        //UserService.loadUsersFromFile();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
        primaryStage.setTitle("SEF - Shopping Center");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

}
