package se.iths.games.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TicTacToeController {

   // private boolean gameStarted = false;

    public Label headline;
    public Label playerScore;
    public Label botScore;
    public Button startResetGame;

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

    private final Model model = new Model();
    public Model getModel (){
        return model;
    }

    private final BotMove botMove = new BotMove(model); // Skapa en instans av BotMove

    public void initialize (){
        startResetGame.setOnAction(this::onStartButtonClick);
        //add buttons to a list of buttons
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
            model.toggleGameStarted ();
            startResetGame.setText(model.isGameStarted() ? "Reset Game" : "Start New Game");
        };
        playStatus.run();
    }
    @FXML
    public void onButtonClicked(MouseEvent mouseEvent) {
        Button clickedButton = (Button) mouseEvent.getSource();

        if (model.makeMove(clickedButton)) {
            // Draget var giltigt och X eller O har satts i knappen beroende på spelarens eller datorns tur.

            if (model.isGameFinished()) {
                // Spelet är över, kontrollera resultatet här.
                String winner = model.determineWinner();
                if (winner.equals("X")) {
                    playerWins++;
                } else if (winner.equals("O")) {
                    botWins++;
                }

                // Uppdatera poängen
                playerScore.setText(String.valueOf(playerWins));
                botScore.setText(String.valueOf(botWins));

                // Återställ spelet
                model.resetButtons();
            } else {
                // Det är datorns tur att göra ett drag.
                botMove.makeBotMove(); // Datorn gör sitt drag.

                if (model.isGameFinished()) {
                    // Spelet är över igen, kontrollera resultatet här.
                    String winner = model.determineWinner();
                    if (winner.equals("X")) {
                        playerWins++;
                    } else if (winner.equals("O")) {
                        botWins++;
                    }

                    // Uppdatera poängen
                    playerScore.setText(String.valueOf(playerWins));
                    botScore.setText("Bot: " + botWins);

                    // Återställ spelet
                    model.startOrResetGame();
                    model.resetButtons();
                }
            }
        }
    }
}
