package ProjectSQL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLTestProductEntity extends AbstractSQLTest {

    @Test
    void getCountProductEntity() throws SQLException {
        //given
        int count = 0;
        String sql = "SELECT * FROM products";
        try {
            Statement statement = getConnection().createStatement();

            //when
            ResultSet result = statement.executeQuery(sql);
            Assertions.assertNotNull(result);
            while (result.next()) {
                count++;
            }

            //then
            Assertions.assertEquals(4, count);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource({"1, banana, 0.5", "2, kivi, 2.0", "3, milk, 1.0", "4, apple, 0.3"})
    void getProductInID(short productId, String menuName, String price) throws SQLException {
        String sql = "SELECT * FROM products WHERE product_id=" + productId;
        Statement statement = getConnection().createStatement();

        ResultSet product = statement.executeQuery(sql);

        Assertions.assertEquals(menuName, product.getString("menu_name"));
        Assertions.assertEquals(price, product.getString("price"));

    }

    @Test
    void addAndDeleteProduct() throws SQLException {
        int count = 0;
        String addProduct = "INSERT INTO products (product_id, menu_name, price) VALUES (77, \"fish\", 5.0)";
        String deleteProduct = "DELETE FROM products WHERE product_id=77";
        String selectProduct = "SELECT * FROM products WHERE product_id=77";
        String allProduct = "SELECT * FROM products";
        try {
            Statement statement = getConnection().createStatement();
            statement.execute(addProduct);

            ResultSet resultAdd = statement.executeQuery(selectProduct);
            Assertions.assertEquals("fish", resultAdd.getString("menu_name"));

            //delete
            statement.execute(deleteProduct);
            ResultSet resultDelete = statement.executeQuery(allProduct);
            Assertions.assertNotNull(resultDelete);
            while (resultDelete.next()) {
                count++;
            }
            //then
            Assertions.assertEquals(4, count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    void updateProduct() throws SQLException {
        //given

        String addProduct = "INSERT INTO products (product_id, menu_name, price) VALUES (77, \"fish\", 5.0)";
        String updateProduct = "UPDATE products SET menu_name=\"BIG FISH\" WHERE product_id=77";
        String selectProduct = "SELECT * FROM products WHERE product_id=77";
        String deleteProduct = "DELETE FROM products WHERE product_id=77";

        try {
            Statement statement = getConnection().createStatement();
            statement.execute(addProduct);
            statement.execute(updateProduct);
            //when
            ResultSet resultUpdate = statement.executeQuery(selectProduct);
            Assertions.assertEquals("BIG FISH", resultUpdate.getString("menu_name"));
            //delete

            statement.execute(deleteProduct);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
