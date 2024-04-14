import FominaKat.Composite.BoxToys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompositeTest {

    @ParameterizedTest(name = "{index}- коробка с игрушками, ст-тью = {1}")
    @DisplayName("Проверка получения ст-ти коробки только с игрушками")
    @MethodSource("BoxArgumentProvider#unitBox")
    void onlyToysInBoxTest(BoxToys boxToys, int totalCost) {

        assertEquals(totalCost, boxToys.getCost());
    }

    @ParameterizedTest(name = "{index}- коробка с игрушками и коробками, ст-тью = {1}")
    @DisplayName("Проверка получения ст-ти коробки с игрушками и вложенными коробками")
    @MethodSource("BoxArgumentProvider#combineBox")
    void combineBoxTest(BoxToys boxToys, int totalCost) {

        assertEquals(totalCost, boxToys.getCost());
    }
}
