import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;


public class Stylists {
  private int id;
  private String name;
  private String detail;


  public Stylists(String name, String detail) {
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

  public static List<Stylists> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists ORDER by name;";
      return con.createQuery(sql)
      .executeAndFetch(Stylists.class);
    }
  }

  public static void update(int id, String name, String detail) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE stylists SET name = :name, detail = :detail WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .addParameter("name", name)
      .addParameter("detail", detail)
      .executeUpdate();
  }
}

  public static Stylists find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id = :id;";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylists.class);
    }
  }

  public static void delete(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM clients WHERE stylist_id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
  }
  try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM stylists WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
