package FominaKat.ProjectSQL;

import org.hibernate.Session;
import org.junit.jupiter.api.*;

import javax.persistence.PersistenceException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HQLTestOrdersHibernate extends AbstractTestHibernate {


    @Test
    @Order(1)
    void addOrderNotValid() {
        //given
        OrdersEntity notValidOrder = new OrdersEntity();
        // when
        Session session = getSession();
        session.beginTransaction();
        session.persist(notValidOrder);

        //then
        Assertions.assertThrows(PersistenceException.class, () -> session.getTransaction().commit());
    }


}
