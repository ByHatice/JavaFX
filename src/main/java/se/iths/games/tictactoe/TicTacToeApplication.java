package se.iths.games.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TicTacToeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApplication.class.getResource("tictactoe.fxml"));
        Scene scene = new Scene (fxmlLoader.load(), 350, 500);
        stage.setScene(scene);
        fxmlLoader.<TicTacToeController>getController();
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}