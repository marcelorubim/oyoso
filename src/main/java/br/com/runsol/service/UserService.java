package br.com.runsol.service;

import br.com.runsol.database.CassandraConnector;
import br.com.runsol.entity.User;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

import javax.enterprise.context.RequestScoped;
import java.util.LinkedList;
import java.util.List;

@RequestScoped
public class UserService {


    public List<User> getAll(){
        final List<User> users = new LinkedList<User>();
        CassandraConnector connector = new CassandraConnector();
        connector.connect();
        Session session =  connector.getSession();
        ResultSet rs = session.execute("SELECT * FROM users");
        rs.forEach(r -> {
            users.add(new User());
        });
        connector.close();
        return users;

    }
}
