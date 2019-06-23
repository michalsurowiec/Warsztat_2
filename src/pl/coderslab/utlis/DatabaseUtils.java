package pl.coderslab.utlis;

import java.sql.*;

public class DatabaseUtils {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Warsztat_2?useSSL=false&characterEncoding=utf8",
                "root",
                "coderslab");
    }

    public static void print(ResultSet resultSet, String... column_names) throws SQLException {

        while(resultSet.next()) {
            String result = "";
            for(String column_name: column_names) {
                result += column_name + ":" + resultSet.getString(column_name)+", ";
            }
            System.out.println(result);
        }
    }

    public static void printAll(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        String[] columnNames = new String[columnCount];
        // The column count starts from 1
        for (int i = 1; i <= columnCount; i++ ) {
            String name = resultSetMetaData.getColumnName(i);
            columnNames[i-1] = name;
        }

        print(resultSet, columnNames);
    }
}