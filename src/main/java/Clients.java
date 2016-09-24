import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Clients {
  private int id;
  private String name;
  private String note;
  private int stylistId;

  public Clients(String name, String note, int stylistId) {
    this.name = name;
    this.note = note;
    this.stylistId = stylistId;

    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, note, stylist_id) VALUES (:name, :note, :stylistId);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("note", this.note)
        .addParameter("stylist_id", this.stylistId)
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

  public String getNote() {
    return note;
  }

  public int getStylistId() {
    return stylistId;
  }

  public static void update(int id, String name, String note, int stylistId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name, note = :note, stylist_id = :stylistId, WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .addParameter("note", note)
        .addParameter("stylist_id", stylistId)
        .executeUpdate();
    }
  }

  public static List<Clients> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients ORDER by name;";
      return con.createQuery(sql)
        .executeAndFetch(Clients.class);
    }
  }

  public static List<Clients> allForStylist(int stylistId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id = :id ORDER by name;";
      return con.createQuery(sql)
        .addParameter("id", stylistId)
        .executeAndFetch(Clients.class);
    }
  }

  public static void delete(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id;";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

 }
