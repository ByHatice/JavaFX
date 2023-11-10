package se.iths.games.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TicTacToeController {

    public Button startGame;
    public Button stopResetGame;
    public Label headline;
    public Label playerScore;
    public Label botScore;


    private int playerWins = 0;
    private int botWins = 0;


    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    public Button button8;
    public Button button9;

    private Model model = new Model();
    private BotMove botMove = new BotMove(model);

    public void initialize (){
        startGame.setOnAction(this::onStartButtonClick);
        stopResetGame.setOnAction(this::onStopButtonClick);

        model.addButton(button1);
        model.addButton(button2);
        model.addButton(button3);
        model.addButton(button4);
        model.addButton(button5);
        model.addButton(button6);
        model.addButton(button7);
        model.addButton(button8);
        model.addButton(button9);
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

        if (model.makeMove(clickedButton)) {

             clickedButton.setText("X");
//clicked button vilken spelare?
            if (model.isGameFinished()) {
                String winner = model.determineWinner();
                if (winner.equals("X")) {
                    playerWins++;
                    headline.setText("X WINS!");
                }
                if (winner.equals("O")) {
                    botWins++;
                    headline.setText("O WINS!");
                } else if (winner.equals("Draw")){
                    headline.setText("It's a draw!");
                }
                playerScore.setText(String.valueOf(playerWins));
                botScore.setText(String.valueOf(botWins));
                model.stopGame();

            } else {
                botMove.makeBotMove();

                if (model.isGameFinished()) {
                    String winner = model.determineWinner();
                    if (winner.equals("X")) {
                        playerWins++;

                        headline.setText("X WINS!");
                    }
                    if (winner.equals("O")) {
                        botWins++;

                        headline.setText("O WINS!");
                    } else if (winner.equals("Draw")){
                        headline.setText("It's a draw!");
                    }
                    playerScore.setText(String.valueOf(playerWins));
                    botScore.setText(String.valueOf(botWins));
                    model.stopGame();
                }
            }
        }
    }
}
