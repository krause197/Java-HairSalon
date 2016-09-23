import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.Date;

public class Stylist {
  private int id;
  private String name;
  private String detail;


  public Stylist(String name, String detail, int stylistId) {
    this.name = name;
    this.detail = detail;

    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, detail) VALUES (:name, :detail);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("detail", this.detail)
        .executeUpdate()
        .getKey();
    }
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDetail() {
    return detail;
  }

  public static List<Stylist> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists ORDER by name;";
      return con.createQuery(sql)
      .executeAndFetch(Stylist.class);
    }
  }

}
