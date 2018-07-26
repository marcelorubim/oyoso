package br.com.runsol.database;

import javax.enterprise.context.RequestScoped;
import java.sql.*;
import java.util.Arrays;
import java.util.stream.IntStream;

@RequestScoped
public class MySQLConnector {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/feedback?user=sqluser&password=sqluserpw");
        } catch (ClassNotFoundException e) {
            //TODO Logger
            close();
            e.printStackTrace();
        } catch (SQLException e) {
            //TODO Logger
            close();
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql) {
        connect();
        try {
            statement = connect.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet executeQueryUpdate(String sql, Object... args) {
        connect();
        try {
            preparedStatement = connect.prepareStatement(sql);
            int index = 0;
            IntStream.range(0, args.length)
                    .forEach(idx -> {
                                Object arg = args[idx];
                                try {
                                    if (arg instanceof String) {
                                        preparedStatement.setString(index, (String) arg);
                                    }
                                }catch (SQLException e){
                                    e.printStackTrace();
                                }
                            }

                    );
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
