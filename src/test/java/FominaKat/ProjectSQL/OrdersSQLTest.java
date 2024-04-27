package FominaKat.ProjectSQL;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * класс с тестами таблицы orders с использованием sqlite
 */

public class OrdersSQLTest extends AbstractSQLTest {
    private static final int countSize = 15;

    @Test
    @DisplayName("Check size table")
    @Description("проверка кол-ва записей в таблице")
    void checkSizeTableOrder() {
        String getOrder = "SELECT * FROM orders";
        int count = 0;
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getOrder);
            while (resultSet.next()) {
                count++;
            }
            Assertions.assertEquals(countSize, count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
