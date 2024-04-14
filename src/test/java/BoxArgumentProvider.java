import FominaKat.Composite.BoxToys;
import FominaKat.Composite.SoftToy;
import org.junit.jupiter.params.provider.Arguments;


import java.util.stream.Stream;

public class BoxArgumentProvider {

    public static Stream<Arguments> unitBox() {
        BoxToys catBox = new BoxToys();
        catBox.add(new SoftToy("cat", 4));
        catBox.add(new SoftToy("cat", 4));
        catBox.add(new SoftToy("cat", 4));

        BoxToys littleBox = new BoxToys();
        littleBox.add(new SoftToy("mouse", 2));
        littleBox.add(new SoftToy("mouse", 2));

        return Stream.of(Arguments.of(catBox, 12),
                Arguments.of(littleBox, 4));
    }


    public static Stream<Arguments> combineBox() {
        BoxToys allToys = new BoxToys();

        BoxToys littleBox = new BoxToys();
        littleBox.add(new SoftToy("mouse", 2));
        littleBox.add(new SoftToy("mouse", 2));

        BoxToys middleBox = new BoxToys();

        BoxToys catBox = new BoxToys();
        catBox.add(new SoftToy("cat", 4));
        catBox.add(new SoftToy("cat", 4));
        catBox.add(new SoftToy("cat", 4));

        middleBox.add(catBox);
        middleBox.add(new SoftToy("dog", 5));

        allToys.add(middleBox);
        allToys.add(littleBox);
        allToys.add(new SoftToy("beer", 10));


        return Stream.of(Arguments.of(allToys, 31),
                Arguments.of(middleBox, 17));
    }
}
