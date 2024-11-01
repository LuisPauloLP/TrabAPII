package DATABASE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection(String enderecoIP, String porta, String databaseName, String usuario, String senha) throws SQLException {

        return DriverManager.getConnection
                (
                    "jdbc:mysql://" + enderecoIP + ":" + porta + "/" + databaseName,
                    usuario,
                    senha
                );
    }

}


