package FominaKat.ProjectSQL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractSQLTest {

    private static Connection connection;

    @BeforeAll
    static void init() throws ClassNotFoundException, SQLException {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:projectSQL.db");
        } catch (Exception e) {
            System.err.println(e.getClass() + "\nError: " + e.getMessage());
            System.exit(0);
        }
        System.out.println("database is opened");
    }

    public static Connection getConnection() {
        return connection;
    }

    @AfterAll
    static void close() throws SQLException {
        connection.close();
    }
}
