package se.iths.games.tictactoe;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("tictactoe.fxml"));
        Scene scene = new Scene (fxmlLoader.load(), 350, 500);
        stage.setScene(scene);
        fxmlLoader.<Controller>getController();
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}