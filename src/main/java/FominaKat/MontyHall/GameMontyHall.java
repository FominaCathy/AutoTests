package FominaKat.MontyHall;

import java.util.ArrayList;
import java.util.List;

/**
 * класс описания игры
 */
public class GameMontyHall {
    private List<Door> doorList;
    private int choice;

    public GameMontyHall() {
        this.doorList = new ArrayList<>();
    }

    /**
     * начинаю новую игру: прячу за укзаной дверью приз
     *
     * @param doorIsPrize - дверь, за которой приз
     */
    public void newGame(int doorIsPrize) {
        doorList.clear();
        for (int i = 0; i < 3; i++) {
            if (i == doorIsPrize) {
                doorList.add(new Door(true));
            } else {
                doorList.add(new Door(false));
            }
        }
    }


    public void choicePlayer(int choice) {
        this.choice = choice;
    }

    public boolean playGame(boolean changeChoice) {
        if (changeChoice) {
            doorList.remove(choice);
            return (doorList.get(0).isPrise() || doorList.get(1).isPrise());
        }
        return doorList.get(choice).isPrise();
    }
}
