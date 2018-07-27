package br.com.runsol.service;

import br.com.runsol.database.CassandraConnector;
import br.com.runsol.database.MySQLConnector;
import br.com.runsol.entity.User;
import com.datastax.driver.core.Session;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@RequestScoped
public class UserService {
    @Inject
    private MySQLConnector mySQLConnector;

    public List<User> getAll(){
        return mySQLConnector.executeQuery("SELECT * FROM users", rs -> {
            List<User> users = new LinkedList<User>();
            try{
                while (rs.next()){
                    users.add(new User(rs.getString("username"),rs.getString("firstName"),rs.getString("lastName")));
                };
            }catch (Exception e){
                System.err.println("Erro");
                e.printStackTrace();
            }
            return  users;
        });

    }
}
