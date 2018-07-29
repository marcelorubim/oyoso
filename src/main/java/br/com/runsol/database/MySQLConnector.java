package br.com.runsol.database;

import javax.enterprise.context.RequestScoped;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

@RequestScoped
public class MySQLConnector {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    private void connect() {
        try {
            System.out.println("Connecting to Mysql "+System.getenv("MYSQL_URL"));
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection(System.getenv("MYSQL_URL"),System.getenv("MYSQL_USER"),System.getenv("MYSQL_PASSWORD"));
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

    public List executeQuery(String sql, Function<ResultSet,List> f) {
        connect();
        try {
            statement = connect.createStatement();
            List output = f.apply(statement.executeQuery(sql));
            close();
            return  output;
        } catch (Exception e) {
            close();
            e.printStackTrace();
            return null;
        } finally {
            close();
        }
    }

    public Object executeQueryUpdate(String sql,Function<ResultSet,Object> f, Object... args) {
        connect();
        try {
            preparedStatement = connect.prepareStatement(sql);
            Object output = new Boolean(preparedStatement.execute());
            close();
            return  output;
        } catch (Exception e) {
            close();
            e.printStackTrace();
            return null;
        } finally {
            close();
        }
    }

    private void close() {
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
            e.printStackTrace();
        }
    }
}
