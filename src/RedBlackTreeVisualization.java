import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class RedBlackTreeVisualization extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("12.fxml"));
        primaryStage.setTitle("Red-Black Tree Visualization");
        primaryStage.setScene(new Scene(root, 645, 426));
        primaryStage.show();
    }

}
