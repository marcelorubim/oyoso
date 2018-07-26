package br.com.runsol.database;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Session;


public class CassandraConnector {
    private Cluster cluster;

    private Session session;

    public void connect() {
        Builder b = Cluster.builder().addContactPoint("172.17.0.3");
        cluster = b.build();

        session = cluster.connect();
    }

    public Session getSession() {
        return this.session;
    }

    public void close() {
        session.close();
        cluster.close();
    }
}
