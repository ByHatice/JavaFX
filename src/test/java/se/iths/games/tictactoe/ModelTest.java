package se.iths.games.tictactoe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
class ModelTest {

    private Model model;
    private ObservableList<StringProperty> buttonList;

    @BeforeEach
    void setUp() {
        model = new Model();
        buttonList = model.getButtonList();
        buttonList.addAll(
                new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty(""),
                new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty(""),
                new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty("")
        );
    }
    @Test
    @DisplayName("Make a valid move")
    void testIsValidMove() {

        model.getButtonList().get(0).set("X");

        assertFalse(model.isValidMove(model.getButtonList().get(0)));

        assertTrue(model.isValidMove(model.getButtonList().get(1)));
    }
    @Test
    @DisplayName("Starting a Game")
    void gameStartsSuccessfully() {

        model.startGame();

        assertTrue(model.isGameStarted());
        assertTrue(model.isPlayerTurn);
    }
    @Test
    @DisplayName("Stopping the game")
    void gameStopsSuccessfully() {
        model.startGame();

        model.stopGame();

        assertFalse(model.isGameStarted());
        assertFalse(model.isPlayerTurn);
    }
    @ParameterizedTest
    @MethodSource("gameFinishedTestData")
    @DisplayName("Check Winner")
    void isGameFinishedTest(String[] buttonTexts, String expectedWinner, boolean expectedResult) {

        for (int i = 0; i < 9; i++) {
            buttonList.get(i).set(buttonTexts[i]);
        }

        if (expectedResult) {
            assertEquals(expectedWinner, model.determineWinner());
        }
    }
    static Stream<Arguments> gameFinishedTestData() {
        return Stream.of(
                Arguments.of(new String[]{"X", "O", "X", "O", "X", "O", "X", "O", "X"}, "X", true),
                Arguments.of(new String[]{"O", "X", "O", "X", "O", "X", "O", "X", "O"}, "O", true),
                Arguments.of(new String[]{"X", "O", "X", "O", "X", "X", "O", "X", "O"}, "Draw", true));
    }
}
