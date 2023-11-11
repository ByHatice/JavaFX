package se.iths.games.tictactoe;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.Random;

public class BotMove {
    private Model model;
    private ObservableList<StringProperty> buttonList;
    public BotMove(Model model) {
        this.model = model;
        this.buttonList = model.getButtonList();
    }
    public boolean makeBotMove() {
        if (model.isGameStarted()) {
            StringProperty randomButton = findRandomMove();
            if (randomButton != null) {
                randomButton.setValue("O");
                model.playerTurn(true);
                return true;
            }
        }
        return false;
    }

    private StringProperty findRandomMove() {
        Random rand = new Random();
        while (true) {
            int randomIndex = rand.nextInt(buttonList.size());
            StringProperty selectedButton = buttonList.get(randomIndex);
            if (selectedButton.getValue().isEmpty()) {
                return selectedButton;
            }
        }
    }
}
