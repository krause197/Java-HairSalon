import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;


public class StylistTest {
  private Stylist stylist;

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    stylist = new Stylist("Mahesh Ankur", "Mahesh produces great fade and high and tight hair styles");
  }

  @Test
  public void Stylist_instantiates_true() {
    assertTrue(stylist instanceof Stylist);
  }

  @Test
  public void Stylist_getId_true() {
    assertTrue(stylist.getId()) > 0);
  }

  @Test
  public void Stylist_getName_string() {
    assertEquals("Mahesh Ankur", stylist.getName());
  }

  @Test
  public void Stylist_getDetail_string() {
    assertEquals("Mahesh produces great fade and high and tight hair styles", stylist.getDetail());
  }

  @Test
  public void Stylist_all_ArrayList() {
    assertTrue(stylist.all().size() > 0);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists *;";
      con.createQuery(sql).executeUpdate();
    }
  }

}
