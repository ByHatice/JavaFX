package se.iths.games.tictactoe;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
   final private ObservableList<StringProperty> buttonList = FXCollections.observableArrayList();

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
        for (StringProperty buttonText : buttonList) {
            buttonText.setValue("");
        }
    }
    public void playerTurn(boolean isPlayerTurn) { //används i botmove
        this.isPlayerTurn = isPlayerTurn;}
    public boolean isGameStarted() { //används i botmove
        return gameStarted;
    }

    public ObservableList<StringProperty> getButtonList() {
        return buttonList;
    }

    public boolean isValidMove(StringProperty button) {
        return button.getValue().isEmpty();
    }
    public boolean makeMove(StringProperty button) {
        if (isGameStarted() && isValidMove(button) && isPlayerTurn) {
            button.setValue("X");
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
            String a = buttonList.get(Integer.parseInt(combo[0])).getValue();
            String b = buttonList.get(Integer.parseInt(combo[1])).getValue();
            String c = buttonList.get(Integer.parseInt(combo[2])).getValue();

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
        String winner = determineWinner();
        if (winner.equals("X") || winner.equals("O")) {
            return true;
        }
        for (StringProperty button : getButtonList()) {
            if (button.get().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
