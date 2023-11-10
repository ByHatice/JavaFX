package se.iths.games.tictactoe;

import javafx.scene.control.Button;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ModelTest {

    Model model = new Model();
/*
    @Test
    @DisplayName("Testar om knapp 4 innehåller 'X' när spelet startas och sedan återställs när spelet startas på nytt")
    public void testStartAndStopGame() {
        model.startGame();
        Button button4 = model.getButtonList().get(3);
        model.makeMove(button4);

        assertEquals("X", button4.getText());
        model.stopGame();

        model.startGame();
        assertEquals("", button4.getText());
    }
*/
}
