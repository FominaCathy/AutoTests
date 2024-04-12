package FominaKat.Composite;

import java.util.List;

/**
 * Класс коробок с игрушками. в коробках могут лежать разные виды/типы игрушек
 */
public class BoxToys implements Component {
    private List<Component> box;

    public BoxToys() {
    }

    public void add(Component item) {
        box.add(item);
    }

    public boolean remove(Component item) {
        if (box.contains(item)) {
            box.remove(item);
            return true;
        }
        return false;
    }

    public List<Component> get() {
        return box;
    }

    @Override
    public int execute() {
        int summ = 0;
        for (Component item : box) {
            summ += item.execute();
        }
        return summ;
    }
}
