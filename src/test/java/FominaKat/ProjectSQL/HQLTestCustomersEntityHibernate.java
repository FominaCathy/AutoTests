package FominaKat.ProjectSQL;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

public class HQLTestCustomersEntityHibernate extends AbstractTestHibernate {

    private static CustomersEntity newCustomer;
    private static String firstName = "Mouse";
    private static short customerId = 99;

    @BeforeEach
    void createParam() {
        newCustomer = new CustomersEntity();
        newCustomer.setCustomerId(customerId);
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName("Gray");
        newCustomer.setPhoneNumber("+7999900000");
        newCustomer.setDistrict("All");
        newCustomer.setStreet("Down");
        newCustomer.setHouse("1");
        newCustomer.setApartment("22");
    }

    @Test
    void geCustomersEntity_ValidCount() {
        final Query query = getSession().createQuery("from CustomersEntity");
        Assertions.assertEquals(4, query.list().size());

    }

    @ParameterizedTest
    @CsvSource({"1, Bear, White, North", "2, Penguin, Black, North", "3, Giraffe, Orange, South", "4, Tiger, Orange, South"})
    void getCustomersEntity_ValidReturnForID(int customerId, String firstName, String lastName, String district) {

        final Query query = getSession().createQuery("from CustomersEntity where customerId=" + customerId);
        CustomersEntity result = (CustomersEntity) query.uniqueResult();
        Assertions.assertEquals(firstName, result.getFirstName());
        Assertions.assertEquals(lastName, result.getLastName());
        Assertions.assertEquals(district, result.getDistrict());
    }

    @Test
    void addAndDelete_CustomersEntity() {

        // добавление записи
        //given
        // when
        Session session = getSession();
        session.beginTransaction();
        session.persist(newCustomer);
        session.getTransaction().commit(); //закрыли и записали

        final Query query = getSession().createQuery("from CustomersEntity where customerId=" + customerId);
        CustomersEntity result = (CustomersEntity) query.uniqueResult();

        // then
        Assertions.assertEquals(firstName, result.getFirstName());

        //удаление записи
        session.beginTransaction();
        session.createQuery("delete from CustomersEntity where customerId =" + customerId)
                .executeUpdate();
        session.getTransaction().commit();

        List resultDelete = session.createQuery("from CustomersEntity where customerId=" + customerId).list();
        Assertions.assertEquals(0, resultDelete.size());

        List resultTable = session.createQuery("from CustomersEntity").list();
        Assertions.assertEquals(4, resultTable.size());
    }

    @Test
    void update_CustomersEntity() {
        // given
        Session session = getSession();
        session.beginTransaction();
        session.persist(newCustomer);
        session.getTransaction().commit(); //закрыли и записали

        final Query query = getSession().createQuery("from CustomersEntity where customerId=" + customerId);
        CustomersEntity addCustomer = (CustomersEntity) query.uniqueResult();

        // изменение записи
        //given
        String newFirstName = "Elephant";
        // when
        session.beginTransaction();
        addCustomer.setLastName(newFirstName);
        session.merge(addCustomer);
        session.getTransaction().commit();

        CustomersEntity addcustomerAfterUpdate = (CustomersEntity) getSession()
                .createQuery("from CustomersEntity where customerId=" + customerId)
                .uniqueResult();

        // then
        Assertions.assertEquals(newFirstName, addcustomerAfterUpdate.getLastName());
        //

        //удаление записи
        session.beginTransaction();
        session.createQuery("delete from CustomersEntity where customerId =" + customerId)
                .executeUpdate();
        session.getTransaction().commit();

        List resultDelete = session.createQuery("from CustomersEntity where customerId=" + customerId).list();
        Assertions.assertEquals(0, resultDelete.size());

        List resultTable = session.createQuery("from CustomersEntity").list();
        Assertions.assertEquals(4, resultTable.size());
    }
}
