import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Clients_instantiates_true() {
    Clients clientAlpha = new Clients("name", "notes", 1);
    assertTrue(clientAlpha instanceof Clients);
  }

  @Test
  public void Clients_getId_true() {
    Clients clientAlpha = new Clients("name", "notes", 1);
    assertTrue(clientAlpha.getId() > 0);
  }

  @Test
  public void Clients_getName_string() {
    Clients clientAlpha = new Clients("name", "notes", 1);
    assertEquals("SGT Smith", clientAlpha.getName());
  }

  @Test
  public void Clients_getNote_string() {
    Clients clientAlpha = new Clients("name", "notes", 1);
    assertEquals("notes", clientAlpha.getNote());
  }

  @Test
  public void Clients_getStylistId_int() {
    Clients clientAlpha = new Clients("name", "notes", 1);
    assertEquals(1, clientAlpha.getStylistId());
  }

  @Test
  public void Clients_all_ArrayList() {
    assertTrue(Clients.allForStylist(1).size() >0);
  }

  @Test
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients *;";
      con.createQuery(sql).executeUpdate();
    }
  }

}
