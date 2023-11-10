package se.iths.games.tictactoe;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.util.Random;

public class BotMove {
    private Model model;
    private ObservableList<Button> buttonList;
    public BotMove(Model model) {
        this.model = model;
        this.buttonList = model.getButtonList();
    }
    public boolean makeBotMove() {
        if (model.isGameStarted()) {
            botStrategy(); // Anropa botStrategy för att bestämma botens drag.
            model.playerTurn(true); // Byt tur till spelaren.
            return true; // Returnera true om draget utfördes.
        }
        return false; // Returnera false om boten inte kan göra ett drag.
    }
    public void botStrategy() {
        Button buttonToClick = findBlockingMove();
        if (buttonToClick == null) {
            buttonToClick = findWinningMove();
        }
        if (buttonToClick == null) {
            buttonToClick = findRandomMove();
        }
        if (buttonToClick != null) {
            buttonToClick.setText("O"); // Utför botens drag.
        }
    }
    private Button findBlockingMove() {
        for (int i = 0; i < 3; i++) {
            if (checkWinningCondition(buttonList.get(i * 3), buttonList.get(i * 3 + 1), buttonList.get(i * 3 + 2), "X")) {
                return findEmptyButton(buttonList.get(i * 3), buttonList.get(i * 3 + 1), buttonList.get(i * 3 + 2));
            }

            if (checkWinningCondition(buttonList.get(i), buttonList.get(i + 3), buttonList.get(i + 6), "X")) {
                return findEmptyButton(buttonList.get(i), buttonList.get(i + 3), buttonList.get(i + 6));
            }
        }
        if (checkWinningCondition(buttonList.get(0), buttonList.get(4), buttonList.get(8), "X")) {
            return findEmptyButton(buttonList.get(0), buttonList.get(4), buttonList.get(8));
        }
        if (checkWinningCondition(buttonList.get(2), buttonList.get(4), buttonList.get(6), "X")) {
            return findEmptyButton(buttonList.get(2), buttonList.get(4), buttonList.get(6));
        }
        return null;
    }
    private Button findWinningMove() {
        for (int i = 0; i < 3; i++) {
            if (checkWinningCondition(buttonList.get(i * 3), buttonList.get(i * 3 + 1), buttonList.get(i * 3 + 2), "O")) {
                return findEmptyButton(buttonList.get(i * 3), buttonList.get(i * 3 + 1), buttonList.get(i * 3 + 2));
            }
            if (checkWinningCondition(buttonList.get(i), buttonList.get(i + 3), buttonList.get(i + 6), "O")) {
                return findEmptyButton(buttonList.get(i), buttonList.get(i + 3), buttonList.get(i + 6));
            }
        }
        if (checkWinningCondition(buttonList.get(0), buttonList.get(4), buttonList.get(8), "O")) {
            return findEmptyButton(buttonList.get(0), buttonList.get(4), buttonList.get(8));
        }
        if (checkWinningCondition(buttonList.get(2), buttonList.get(4), buttonList.get(6), "O")) {
            return findEmptyButton(buttonList.get(2), buttonList.get(4), buttonList.get(6));
        }
        return null;
    }
    private boolean checkWinningCondition(Button a, Button b, Button c, String symbol) {
        return a.getText().equals(symbol) && b.getText().equals(symbol) && c.getText().isEmpty();
    }
    private Button findEmptyButton(Button a, Button b, Button c) {
        if (a.getText().isEmpty()) {
            return a;
        }
        if (b.getText().isEmpty()) {
            return b;
        }
        if (c.getText().isEmpty()) {
            return c;
        }
        return null;
    }
    private Button findRandomMove() {
        Random rand = new Random();
        while (true) {
            int randomIndex = rand.nextInt(buttonList.size());
            Button selectedButton = buttonList.get(randomIndex);
            if (selectedButton.getText().isEmpty()) {
                return selectedButton;
            }
        }
    }
}
