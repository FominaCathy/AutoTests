package FominaKat.Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс коробок с игрушками. в коробках могут лежать разные виды/типы игрушек
 */
public class BoxToys implements Component {
    private List<Component> box;

    public BoxToys() {
        this.box = new ArrayList<>();
    }

    public void add(Component item) {
        box.add(item);
    }


    @Override
    public int getCost() {
        int summ = 0;
        for (Component item : box) {
            summ += item.getCost();
        }
        return summ;
    }


}
