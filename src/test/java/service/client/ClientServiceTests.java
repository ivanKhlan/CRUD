package service.client;

import client.Client;
import inittable.InitTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTests {
    private Connection connection;
    private ClientService clientService;

    @BeforeEach
    public void beforeEach() throws SQLException {

        String connectionUrl = "jdbc:h2:mem:test";
        new InitTable().initDb(connectionUrl);

        connection = DriverManager.getConnection(connectionUrl);

        clientService = new ClientService(connection);
    }

    @AfterEach
    public void afterEach() throws SQLException {
        connection.close();
    }

    @Test
    public void testThatHumanCreatedCorrectly() throws SQLException {

        Client client = new Client();
        client.setName("TestName");

        long id = clientService.create("ivan");

        Client saved = clientService.getById(id);

        Assertions.assertEquals(id, saved.getId());
        Assertions.assertEquals(client.getName(), saved.getName());

    }

}