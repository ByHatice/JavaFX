module se.iths.games.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.iths.games.tictactoe to javafx.fxml;
    exports se.iths.games.tictactoe;
}