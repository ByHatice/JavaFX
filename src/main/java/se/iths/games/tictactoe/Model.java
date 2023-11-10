package se.iths.games.tictactoe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;


public class Model {
    private ObservableList<Button> buttonList = FXCollections.observableArrayList();

    public boolean gameStarted;
    public boolean isPlayerTurn;

    public void startGame() {
        gameStarted = true;
        isPlayerTurn = true;
        resetButtons();
    }

    public void stopGame() {
        gameStarted = false;
        isPlayerTurn = false;
    }

    public void resetButtons() {
        for (Button button : buttonList) {
            button.setText("");
        }
    }
    public boolean isGameStarted() {
        return gameStarted;
    }

    public ObservableList<Button> getButtonList() {
        return buttonList;
    }

    public void addButton(Button button) {
        buttonList.add(button);
    }

    public boolean isValidMove(Button button) {
        return button.getText().isEmpty();
    }

    public void playerTurn(boolean isPlayerTurn) {
        this.isPlayerTurn = isPlayerTurn;
    }

    public boolean makeMove(Button button) {
        if (isGameStarted() && isValidMove(button) && isPlayerTurn) {
            button.setText("X");
            isPlayerTurn = false;
            return true;
        }
        return false;
    }

    public String determineWinner() {
        String[][] winningCombinations = {
                {"0", "1", "2"},
                {"3", "4", "5"},
                {"6", "7", "8"},
                {"0", "3", "6"},
                {"1", "4", "7"},
                {"2", "5", "8"},
                {"0", "4", "8"},
                {"2", "4", "6"}
        };

        for (String[] combo : winningCombinations) {
            String a = buttonList.get(Integer.parseInt(combo[0])).getText();
            String b = buttonList.get(Integer.parseInt(combo[1])).getText();
            String c = buttonList.get(Integer.parseInt(combo[2])).getText();

            if (a.equals(b) && b.equals(c)) {
                if (a.equals("X")) {
                    return "X";
                } else if (a.equals("O")) {
                    return "O";
                }
            }
        }
        return "Draw";
    }
    public boolean isGameFinished() {
        if (determineWinner().equals("X"))
            return true;
        if (determineWinner().equals("O"))
            return true;
        for (Button button : getButtonList()) {
            if (button.getText().isEmpty()) {
                return false;
            }
        }
        return true;

    }
}
