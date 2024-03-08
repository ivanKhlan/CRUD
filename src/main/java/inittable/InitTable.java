package inittable;

import database.Database;
import org.flywaydb.core.Flyway;
import prefs.Prefs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InitTable {

    private PreparedStatement createSt;
    private Connection conn;
    public void initDb(String connectionUrl)  {
//        String connectionUrl = new Prefs().getString(Prefs.DB_URL);

        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();

        flyway.migrate();

//        conn = database.getConnection();
//
//        createTableWorker();
//        createTableClient();
//        createTableProject();
//        createTableProjectWorker();

    }

    public void createTableWorker() throws SQLException, IOException {
        String sql = String.join("\n",
                Files.readAllLines(
                        Paths.get("src/main/resources/worker.sql"))
        );
        createSt = conn.prepareStatement(sql);
        createSt.executeUpdate();
    }

    public void createTableClient() throws SQLException, IOException {
        String sql = String.join("\n",
                Files.readAllLines(
                        Paths.get("src/main/resources/client.sql"))
        );
        createSt = conn.prepareStatement(sql);
        createSt.executeUpdate();
    }

    public void createTableProject() throws SQLException, IOException {
        String sql = String.join("\n",
                Files.readAllLines(
                        Paths.get("src/main/resources/project.sql"))
        );
        createSt = conn.prepareStatement(sql);
        createSt.executeUpdate();
    }

    public void createTableProjectWorker() throws SQLException, IOException {
        String sql = String.join("\n",
                Files.readAllLines(
                        Paths.get("src/main/resources/projectWorker.sql"))
        );
        createSt = conn.prepareStatement(sql);
        createSt.executeUpdate();
    }

}
