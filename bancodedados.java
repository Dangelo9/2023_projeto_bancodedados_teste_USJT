import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bancodedados {

    private static final String JDBC_URL = "mysql-3548a416-descubra02-a95d.aivencloud.com";
    private static final String JDBC_USER = "avnadmin";
    private static final String JDBC_PASSWORD = "AVNS_Rjf9JauNYFfx6iGv5wX";

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void cadastrarUsuario(String nome, int idade, String sexo, String tipoLivro1, String tipoLivro2, String username, String senha) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String query = "INSERT INTO usuarios (nome, idade, sexo, tipo_livro_preferido1, tipo_livro_preferido2, username, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nome);
            preparedStatement.setInt(2, idade);
            preparedStatement.setString(3, sexo);
            preparedStatement.setString(4, tipoLivro1);
            preparedStatement.setString(5, tipoLivro2);
            preparedStatement.setString(6, username);
            preparedStatement.setString(7, senha);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}