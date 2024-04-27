package FominaKat.ProjectSQL;

import jdk.jfr.Description;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Класс с тестами таблицы Courier_info
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourierInfoHQLTest extends AbstractHQLTest {

    private final int sizeTable = 4;
    private static CourierInfoEntity newCourier;

    @BeforeAll
    static void initCourier() {
        newCourier = new CourierInfoEntity();
        newCourier.setCourierId((short) 99);
        newCourier.setFirstName("Courier");
        newCourier.setLastName("Speedy");
        newCourier.setPhoneNumber("+132456");
        newCourier.setDeliveryType("foot");
   }

    @Test
    @DisplayName("Check size table")
    @Description("проверка кол-ва записей в таблице")
    @Order(1)
    void checkSizeTable() {
        final Query query = getSession().createQuery("from CourierInfoEntity");
        assertEquals(sizeTable, query.list().size());

    }

    @Order(2)
    @ParameterizedTest(name = "{index} - id: {0} lastName: {1}, delivery: {2}")
    @DisplayName("Check get entry table for ID")
    @Description("проверка получения записи по ID")
    @CsvSource({
            "1, John, foot",
            "2, Kate, car",
            "3, Bob, car",
            "4, Michael, car"})
    void getEntryTableForID(int courierId, String lastName, String deliveryType) {

        final Query query = getSession().createQuery("from CourierInfoEntity where courierId=" + courierId);
        Optional<CourierInfoEntity> result = query.uniqueResultOptional();
        assertTrue(result.isPresent());
        assertEquals(lastName, result.get().getFirstName());
        assertEquals(deliveryType, result.get().getDeliveryType());
    }

    @Test
    @Order(3)
    @DisplayName("Check add entry table")
    @Description("проверка добавления записи в таблицу")
    void checkAddCourierInfo() {
        //given
        // when
        Session session = getSession();
        session.beginTransaction();
        session.persist(newCourier);
        session.getTransaction().commit(); //закрыли и записали

        final Query query = getSession()
                .createQuery("from CourierInfoEntity where courierId=" + newCourier.getCourierId());
        CourierInfoEntity result = (CourierInfoEntity) query.uniqueResult();

        // then
        assertEquals(newCourier.getLastName(), result.getLastName());
    }

    @Test
    @DisplayName("Check update entry table")
    @Description("проверка изменения записи в таблице")
    @Order(4)
    void checkUpdateCourierInfo() {
        // given
        final Query query = getSession()
                .createQuery("from CourierInfoEntity where courierId=" + newCourier.getCourierId());
        CourierInfoEntity newCourier = (CourierInfoEntity) query.uniqueResult();
        String newLastName = "Slow";

        // when
        Session session = getSession();
        session.beginTransaction();
        newCourier.setLastName(newLastName);
        session.merge(newCourier);
        session.getTransaction().commit();

        CourierInfoEntity courierAfterUpdate = (CourierInfoEntity) getSession()
                .createQuery("from CourierInfoEntity where courierId=" + newCourier.getCourierId())
                .uniqueResult();

        // then
        assertEquals(newLastName, courierAfterUpdate.getLastName());

    }

    @Test
    @DisplayName("Check delete entry table")
    @Description("проверка удаления записи в таблице")
    @Order(5)
    void checkDeleteCourierInfo() {

        //удаление записи
        Session session = getSession();
        session.beginTransaction();
        session.createQuery("delete from CourierInfoEntity where courierId =" + newCourier.getCourierId())
                .executeUpdate();
        session.getTransaction().commit();

        List resultDelete = session
                .createQuery("from CourierInfoEntity where courierId=" + newCourier.getCourierId()).list();
        assertEquals(0, resultDelete.size());

        List resultTable = session.createQuery("from CourierInfoEntity").list();
        assertEquals(4, resultTable.size());
    }


}
