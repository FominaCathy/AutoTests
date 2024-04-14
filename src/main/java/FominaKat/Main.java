package FominaKat;

import FominaKat.Composite.BoxToys;
import FominaKat.Composite.SoftToy;

public class Main {

    public static void main(String[] args) {

        BoxToys allToys = new BoxToys();

        BoxToys litleBox = new BoxToys();
        litleBox.add(new SoftToy("mouse", 2));
        litleBox.add(new SoftToy("mouse", 2));

        BoxToys middleBox = new BoxToys();

        BoxToys catBox = new BoxToys();
        catBox.add(new SoftToy("cat", 4));
        catBox.add(new SoftToy("cat", 4));
        catBox.add(new SoftToy("cat", 4));

        middleBox.add(catBox);
        middleBox.add(new SoftToy("dog", 5));

        allToys.add(middleBox);
        allToys.add(litleBox);
        allToys.add(new SoftToy("beer", 10));

        System.out.println("catBox = " + catBox.getCost());
        System.out.println("litleBox = " + litleBox.getCost());
        System.out.println("middleBox = " + middleBox.getCost());
        System.out.println("allToys = " + allToys.getCost());


    }
}