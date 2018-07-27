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
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://172.17.0.2:3306/solar?useSSL=false","root","1234");
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

    public List executeQueryUpdate(String sql,Function<ResultSet,List> f, Object... args) {
        connect();
        try {
            preparedStatement = connect.prepareStatement(sql);
            List output = f.apply(preparedStatement.getResultSet());
            close();
            return  output;
        } catch (SQLException e) {
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
