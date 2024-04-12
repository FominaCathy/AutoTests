package MontyHall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PlayGameTest extends AbstractTest {

    @BeforeEach
    void initGame() {
        game.newGame(0);
    }

    /**
     * проверка выигрыша, если игрок меняет свой выбор после отгрытия двери
     *
     * @param choice первоначалный выбор
     */
    @ParameterizedTest(name = "{index}- первоначальный выбор двери № {0}")
    @DisplayName("Проверка выигрыша, при смене выбора(выигрыш за дверью № 0).")
    @ValueSource(ints = {1, 2})
    void testChangeChoiceTrueWin(int choice) {
        game.choicePlayer(choice);
        assertTrue(game.playGame(true));
    }

    /**
     * проверка выигрыша, если игрок НЕ меняет свой выбор после отгрытия двери
     *
     * @param choice - выбор
     */

    @ParameterizedTest(name = "{index}- выбранная дверь {0}")
    @DisplayName("Проверка выигрыша, не меняя выбор (выигрыш за дверью 0).")
    @ValueSource(ints = {0})
    void testNotChangeChoiceWin(int choice) {
        game.choicePlayer(choice);
        assertTrue(game.playGame(false));
    }

    /**
     * Проверка проигрыша
     *
     * @param choice       - выбор двери игрока
     * @param changeChoice - меняет ли игрок свой выбор после открытия двери
     */
    @ParameterizedTest(name = "{index} - выбор игрока - {0}, смена выбора - {1}")
    @DisplayName("Проверка проигрыша. (Выигрыш за дверью 0).")
    @CsvSource({"0, true", "1, false", "2, false"})
    void testNotChangeChoiceLose(int choice, boolean changeChoice) {
        game.choicePlayer(choice);
        assertFalse(game.playGame(changeChoice));
    }
}
