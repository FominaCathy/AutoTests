package MontyHall;

import FominaKat.MontyHall.GameMontyHall;
import org.junit.jupiter.api.BeforeAll;

public abstract class AbstractTest {
    protected static GameMontyHall game;

    @BeforeAll
    public static void initgame() {
        game = new GameMontyHall();
    }


}
