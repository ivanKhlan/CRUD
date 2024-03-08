package service.client;

import client.Client;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientService {

    private PreparedStatement createSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement selectMaxIdSt;


    public ClientService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement(
                "INSERT INTO client (name) VALUES (?)"
        );

        getByIdSt = connection.prepareStatement(
                "SELECT name FROM client WHERE id = ?"
        );

        selectMaxIdSt = connection.prepareStatement(
                "SELECT max(id) AS maxId FROM client"
        );



    }

    public long create(String name) throws SQLException {
        createSt.setString(1, name);
        createSt.executeUpdate();

        int id;
        try(ResultSet rs = selectMaxIdSt.executeQuery()) {
            rs.next();
            id = rs.getInt("maxId");
        }


        return id;

    }

    public Client getById(long id) {
        return null;
    }
}
