package FominaKat.Composite;

public class SoftToy implements Component {
    private String name;
    private int cost;

    public SoftToy(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    @Override
    public int execute() {
        return cost;
    }
}
