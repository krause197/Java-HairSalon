import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;


public class StylistTest {
  private Stylist stylist;

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    stylist = new Stylist("Mahesh Ankur", "Mahesh produces a great fade and high and tight hair styles");
  }

  @Test
  public void Stylist_instantiates_true() {
    assertTrue(stylist instanceof Stylist);
  }

  @Test
  public void Stylist_getId_true() {
    assertTrue(stylist.getId()) > 0);
  }

  
