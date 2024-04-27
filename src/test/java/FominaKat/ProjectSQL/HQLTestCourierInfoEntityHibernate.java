package FominaKat.ProjectSQL;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HQLTestCourierInfoEntityHibernate extends AbstractTestHibernate {

    private static String lastNameNew = "Speedy";
    private static short courierId = 99;


    @Test
    @Order(1)
    void getCourierInfoEntity_ValidCount() {
        final Query query = getSession().createQuery("from CourierInfoEntity");
        Assertions.assertEquals(3, query.list().size());

    }

    @Order(2)
    @ParameterizedTest
    @CsvSource({"1, Stork, avia", "2, Tom, car", "3, Snail, food"})
    void getCourierInfoEntity_ValidReturnForID(int courierId, String lastName, String deliveryType) {

        final Query query = getSession().createQuery("from CourierInfoEntity where courierId=" + courierId);
        CourierInfoEntity result = (CourierInfoEntity) query.uniqueResult();
        Assertions.assertEquals(lastName, result.getLastName());
        Assertions.assertEquals(deliveryType, result.getDeliveryType());
    }

    @Test
    @Order(3)
    void addCourierInfoEntity() {

        //given
        CourierInfoEntity newCourier = new CourierInfoEntity();
        newCourier.setCourierId(courierId);
        newCourier.setFirstName("Courier");
        newCourier.setLastName(lastNameNew);


        // when
        Session session = getSession();
        session.beginTransaction();
        session.persist(newCourier);
        session.getTransaction().commit(); //закрыли и записали

        final Query query = getSession().createQuery("from CourierInfoEntity where courierId=" + courierId);
        CourierInfoEntity result = (CourierInfoEntity) query.uniqueResult();

        // then
        Assertions.assertEquals(lastNameNew, result.getLastName());
    }

    @Test
    @Order(4)
    void updateCourierInfoEntity() {
        // given
        final Query query = getSession().createQuery("from CourierInfoEntity where courierId=" + courierId);
        CourierInfoEntity newCourier = (CourierInfoEntity) query.uniqueResult();
        String newLastName = "Slow";

        // when
        Session session = getSession();
        session.beginTransaction();
        newCourier.setLastName(newLastName);
        session.merge(newCourier);
        session.getTransaction().commit();

        CourierInfoEntity addCourierAfterUpdate = (CourierInfoEntity) getSession()
                .createQuery("from CourierInfoEntity where courierId=" + courierId)
                .uniqueResult();

        // then
        Assertions.assertEquals(newLastName, addCourierAfterUpdate.getLastName());
        //

    }

    @Test
    @Order(5)
    void deleteCourierInfoEntity() {

        //удаление записи
        Session session = getSession();
        session.beginTransaction();
        session.createQuery("delete from CourierInfoEntity where courierId =" + courierId)
                .executeUpdate();
        session.getTransaction().commit();

        List resultDelete = session.createQuery("from CourierInfoEntity where courierId=" + courierId).list();
        Assertions.assertEquals(0, resultDelete.size());

        List resultTable = session.createQuery("from CourierInfoEntity").list();
        Assertions.assertEquals(3, resultTable.size());
    }


}
