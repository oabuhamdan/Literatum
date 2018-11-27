package backstage;

import java.sql.*;

public class DBProcessing {


    private static Statement statement;

    public static void Connect() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        final String connectionURL="jdbc:mysql://localhost:3306/LIT?useSSl=true&serverTimezone=UTC";

        Connection connection = DriverManager.getConnection(connectionURL, "root", "password");

         statement= connection.createStatement();
    }

    public static boolean isUserValid(String userName,String password) throws SQLException {

        String order="select * from users where user_name='"+userName+"';";
        ResultSet resultSet=statement.executeQuery(order);

        String DBPass = "";
        while(resultSet.next()){
        DBPass=resultSet.getString("password");
        }

        //CHECK PASS
        if(DBPass.equals(password))
            return true;

        return false;

    }


}
