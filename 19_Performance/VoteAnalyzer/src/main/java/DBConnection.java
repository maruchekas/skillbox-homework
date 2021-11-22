import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBConnection {

    private static Connection connection;

    private static final String url = "jdbc:mysql://localhost:3306/learn"
        + "?useSSL=false&serverTimezone=Europe/Moscow"
        + "&rewriteBatchedStatements=true";
    private static final String dbUser = "root";
    private static final String dbPass = "Zabbil@3qew8Nbi1984";
    private static StringBuffer insertQuery = new StringBuffer();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                    url, dbUser, dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "`count` INT NOT NULL, " +
                    "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executePrepareStatementInsert(HashMap<Voter, Integer> voters) throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate, count) "
            + "VALUES (?, ?, ?)";
        PreparedStatement ps = getConnection().prepareStatement(sql);
        final int batchSize = 100000;
        int count = 0;
        for (Map.Entry<Voter, Integer> voter: voters.entrySet()) {
            ps.setString(1, voter.getKey().getName());
            ps.setString(2, voter.getKey().getBirthDay());
            ps.setString(3, String.valueOf(voter.getValue()));
            ps.addBatch();
            if(++count >= batchSize) {
                ps.executeBatch();
                count = 0;
            }
        }
        ps.executeBatch();
        ps.close();
    }

    public static void printDuplicatedVoters() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }

    public static void closeConnection() throws SQLException {
        if (!connection.isClosed()){
            connection.close();
        }
    }
}