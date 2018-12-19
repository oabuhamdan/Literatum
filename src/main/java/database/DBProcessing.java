package database;

import java.sql.*;

public class DBProcessing {

    private static Statement statement;
    private static Connection connection;

    public static void Connect() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        final String connectionURL = "jdbc:mysql://localhost:3306/LIT?useSSl=true&serverTimezone=UTC";

        connection = DriverManager.getConnection(connectionURL, "root", "password");

        statement = connection.createStatement();
    }

    public static boolean isUserValid(String userName, String password) {

        String order = "select * from users where user_name='" + userName + "';";
        try {


            ResultSet resultSet = statement.executeQuery(order);

            String DBPass = "";
            while (resultSet.next()) {
                DBPass = resultSet.getString("password");
            }

            //CHECK PASS
            if (DBPass.equals(password))
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public static void executeStatement(String SQLStatement) {
        try {
            statement.executeUpdate(SQLStatement);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
