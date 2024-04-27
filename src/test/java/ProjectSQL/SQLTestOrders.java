package ProjectSQL;

import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SQLTestOrders extends AbstractSQLTest {

    @Test
    @Order(1)
    void getOrder() {
        String getOrder = "SELECT INTO * FROM orders";
        int count = 0;
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getOrder);
            Assertions.assertNotNull(resultSet);
            while (resultSet.next()){
                count++;
            }
            Assertions.assertEquals(3, count);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(2)
    void addOrder() {
        String addOrder = "INSERT INTO orders (customer_id, date_get) VALUES (1, date('2033-10-10'))";
        String selectOrder = "SELECT * FROM orders ORDER BY order_id DESC LIMIT 1";
        try {
            Statement statement = getConnection().createStatement();

            ResultSet resultAdd = statement.executeQuery(selectOrder);
            Assertions.assertEquals("2033-10-10", resultAdd.getString("date_get"));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    void updateOrder() {
        String updateProduct = "UPDATE orders SET date_get=\"2055-12-12\" WHERE date_get=\"2033-10-10\"";
        String selectOrder = "SELECT * FROM orders ORDER BY order_id DESC LIMIT 1";

        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultAdd = statement.executeQuery(selectOrder);

            Assertions.assertEquals("2055-12-12", resultAdd.getString("date_get"));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    void deleteOrder() {
        String deleteProduct = "DELETE FROM orders WHERE date_get=\"2055-12-12\"";
        String selectOrder = "SELECT * FROM orders";
        int count = 0;
        try {
            Statement statement = getConnection().createStatement();
            statement.execute(deleteProduct);

            ResultSet resultDel = statement.executeQuery(selectOrder);

            Assertions.assertNotNull(resultDel);
            while (resultDel.next()){
                count++;
            }
            Assertions.assertEquals(3, count);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
