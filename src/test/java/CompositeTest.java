import FominaKat.Composite.BoxToys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompositeTest {

    /**
     * Тест для коробки только с игрушками
     *
     * @param boxToys   - коробки с игрушками (только ингрушк)
     * @param totalCost - ст-ть коробки
     */
    @ParameterizedTest(name = "{index}- коробка с игрушками, ст-тью = {1}")
    @DisplayName("Проверка получения ст-ти коробки только с игрушками")
    @MethodSource("BoxArgumentProvider#unitBox")
    void onlyToysInBoxTest(BoxToys boxToys, int totalCost) {

        assertEquals(totalCost, boxToys.getCost());
    }

    /**
     * Тест для сборных коробок с игрушками (коробки и игрушки)
     *
     * @param boxToys   сборная коробка (коробки и игрушки внутри)
     * @param totalCost общая ст-ть коробки
     */

    @ParameterizedTest(name = "{index}- коробка с игрушками и коробками, ст-тью = {1}")
    @DisplayName("Проверка получения ст-ти коробки с игрушками и вложенными коробками")
    @MethodSource("BoxArgumentProvider#combineBox")
    void combineBoxTest(BoxToys boxToys, int totalCost) {

        assertEquals(totalCost, boxToys.getCost());
    }
}
