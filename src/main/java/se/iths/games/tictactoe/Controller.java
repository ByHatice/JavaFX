package se.iths.games.tictactoe;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class Controller {

    public Button startGame;
    public Button stopResetGame;
    public Label headline;
    public Label playerScore;
    public Label botScore;

    private int playerWins = 0;
    private int botWins = 0;
    ObservableList<StringProperty> buttonList;

    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    public Button button8;
    public Button button9;

    private final Model model = new Model();


    public void initialize (){
        startGame.setOnAction(this::onStartButtonClick);
        stopResetGame.setOnAction(this::onStopButtonClick);
        buttonList = model.getButtonList();

        buttonList.add(button1.textProperty());
        buttonList.add(button2.textProperty());
        buttonList.add(button3.textProperty());
        buttonList.add(button4.textProperty());
        buttonList.add(button5.textProperty());
        buttonList.add(button6.textProperty());
        buttonList.add(button7.textProperty());
        buttonList.add(button8.textProperty());
        buttonList.add(button9.textProperty());
    }
    @FXML
    protected void onStartButtonClick(ActionEvent actionEvent) {
        Runnable playStatus = () -> {
            model.startGame ();
            headline.setText("Tic-Tac-Toe");
            model.resetButtons();
        };
        playStatus.run();
    }
    protected void onStopButtonClick(ActionEvent actionEvent) {
        Runnable stopStatus = () -> {
            model.stopGame();
            playerWins = 0;
            botWins = 0;
            playerScore.setText("0");
            botScore.setText("0");
            headline.setText("Tic-Tac-Toe");
            model.resetButtons();
        };
        stopStatus.run();
    }
    public void onButtonClicked(MouseEvent mouseEvent) {
        Button clickedButton = (Button) mouseEvent.getSource();
        if (model.makeMove(clickedButton.textProperty())) {
            clickedButton.setText("X");

        if (model.isGameFinished()) {
            handleGameResult();
        }
        else {
            model.makeBotMove();
            if (model.isGameFinished()) {
                handleGameResult();
            }
        }
        }
    }
    private void handleGameResult() {
    String winner = model.determineWinner();
        switch (winner) {
            case "X" -> {
                playerWins++;
                headline.setText("X WINS!");
            }
            case "O" -> {
                botWins++;
                headline.setText("O WINS!");
            }
            case "Draw" -> headline.setText("It's a draw!");
        }

    playerScore.setText(String.valueOf(playerWins));
    botScore.setText(String.valueOf(botWins));
    model.stopGame();
    }
}
