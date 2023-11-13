package se.iths.games.tictactoe;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Model {

    final private ObservableList<StringProperty> buttonList = FXCollections.observableArrayList();

    private boolean gameStarted;
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
    public void playerTurn(boolean isPlayerTurn) {
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
    public void makeBotMove() {
        if (isGameStarted()) {
            StringProperty randomButton = findRandomMove();
            randomButton.setValue("O");
            playerTurn(true);
        }
    }
    private StringProperty findRandomMove() {
        Random random = new Random();
        while (true) {
            int randomIndex = random.nextInt(buttonList.size());
            StringProperty selectedButton = buttonList.get(randomIndex);
            if (isValidMove(selectedButton)) {
                return selectedButton;
            }
        }
    }
    public String determineWinner() {
        List<List<Integer>> winningCombinations = Arrays.asList(
                Arrays.asList(0, 1, 2),
                Arrays.asList(3, 4, 5),
                Arrays.asList(6, 7, 8),
                Arrays.asList(0, 3, 6),
                Arrays.asList(1, 4, 7),
                Arrays.asList(2, 5, 8),
                Arrays.asList(0, 4, 8),
                Arrays.asList(2, 4, 6)
        );
        for (List<Integer> combo : winningCombinations) {
            if (isWinningCombo(combo, "X")) {
                return "X";
            }
            if (isWinningCombo(combo, "O")) {
                return "O";
            }
        }
        return "Draw";
    }
    private boolean isWinningCombo(List<Integer> combo, String player) {
        for (int index : combo) {
            String value = buttonList.get(index).getValue();
            if (!value.equals(player)) {
                return false;
            }
        }
        return true;
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
