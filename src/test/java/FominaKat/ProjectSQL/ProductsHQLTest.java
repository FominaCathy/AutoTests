package FominaKat.ProjectSQL;

import jdk.jfr.Description;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductsHQLTest extends AbstractHQLTest {
    private static int sizeTable = 10;

    private static ProductsEntity newProduct;

    @BeforeAll
    static void initProduct() {
        newProduct = new ProductsEntity();
        newProduct.setProductId((short) 99);
        newProduct.setMenuName("MILK");
        newProduct.setPrice("15.0");

    }

    @Test
    @DisplayName("Check size table")
    @Description("проверка кол-ва строк в таблице")
    @Order(1)
    void checkSizeTable() {
        final Query query = getSession().createQuery("from ProductsEntity");
        assertEquals(sizeTable, query.list().size());

    }

    @ParameterizedTest(name = "{index} - id: {0} lastName: {1}, delivery: {2}")
    @DisplayName("Check get entry table for ID")
    @Description("проверка получения записи по ID")
    @CsvSource({
            "1, GOJIRA ROLL, 300.0",
            "10, MINERAL WATER, 50.0",
            "9, COFFEE, 79.0"})
    @Order(2)
    void getProductInID(short productId, String menuName, String price) {
        final Query query = getSession().createQuery("from ProductsEntity where productId=" + productId);
        ProductsEntity product = (ProductsEntity) query.uniqueResult();

        assertEquals(menuName, product.getMenuName());
        assertEquals(price, product.getPrice());

    }

    @Test
    @DisplayName("Check add entry table ")
    @Description("проверка создания строки")
    @Order(3)
    void checkAddEntryTable() {

        Session session = getSession();
        session.beginTransaction();
        session.persist(newProduct);
        session.getTransaction().commit(); //закрыли и записали

        final Query query = getSession()
                .createQuery("from ProductsEntity where productId=" + newProduct.getProductId());
        ProductsEntity result = (ProductsEntity) query.uniqueResult();

        assertEquals(newProduct.getMenuName(), result.getMenuName());
    }

    @Test
    @DisplayName("Check update entry table ")
    @Description("проверка изменения строки")
    @Order(4)
    void checkUpdateEntryTable() {

        final Query query = getSession().createQuery("from ProductsEntity where productId=" + newProduct.getProductId());
        ProductsEntity newProduct = (ProductsEntity) query.uniqueResult();
        String newNameMenu = "CREAM";

        Session session = getSession();
        session.beginTransaction();
        newProduct.setMenuName(newNameMenu);
        session.merge(newProduct);
        session.getTransaction().commit();

        ProductsEntity productAfterUpdate = (ProductsEntity) getSession()
                .createQuery("from ProductsEntity where productId=" + newProduct.getProductId())
                .uniqueResult();

        assertEquals(newNameMenu, productAfterUpdate.getMenuName());
    }

    @Test
    @DisplayName("Check delete entry table")
    @Description("проверка удаления строки")
    @Order(5)
    void checkDeleteEntryTable() {

        Session session = getSession();
        session.beginTransaction();
        session.createQuery("delete from ProductsEntity where productId =" + newProduct.getProductId())
                .executeUpdate();
        session.getTransaction().commit();

        List resultDelete = session.createQuery("from ProductsEntity where productId=" + newProduct.getProductId()).list();
        assertEquals(0, resultDelete.size());

        List resultTable = session.createQuery("from ProductsEntity").list();
        assertEquals(sizeTable, resultTable.size());
    }

}
