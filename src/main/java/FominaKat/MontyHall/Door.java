package FominaKat.MontyHall;

/**
 * класс описания дверей
 */
public class Door {
    private boolean prise;

    public Door(boolean prise) {
        this.prise = prise;
    }

    public boolean isPrise() {
        return prise;
    }

    public void setPrise(boolean prise) {
        this.prise = prise;
    }
}
