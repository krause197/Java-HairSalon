import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class ClientTest {
  private Clients clientA;
  private Clients clientB;
  private Stylist stylist;

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    stylist = new Stylist("Mahesh Ankur", "Mahesh produces great fade and high and tight hair styles");
    clientA = new Clients("SGT Smith", "SGT Smith comes every 1st and 3rd Friday, High and Tight with a zero up the sides and fingerlength on top");
    clientB = new Clients("1LT Clark", "1LT Clark comes every 2nd and 4th Tuesday, Fade with a zero up the sides and a three on top");
  }

  @Test
  public void Client_instantiates_true() {
    assertTrue(clientA instanceof Clients);
  }

  @Test
  public void Client_getId_true() {
    assertTrue(clientA.getId() > 0);
  }
