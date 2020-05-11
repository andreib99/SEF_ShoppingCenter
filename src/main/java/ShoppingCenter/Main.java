package ShoppingCenter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("SEF - Shopping Center");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

}
