import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Clients {
  private int id;
  private String name;
  private String note;
  private int stylistId;

  public Client(String name, String note, String stylistId) {
    this.name = name;
    this.note = note;
    this.stylistId = stylistId;

    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, note, stylistId) VALUES (:name, :note, :stylistId);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("note", this.note)
        .addParameter("stylistId", this.stylistId)
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

  public String getStylistId() {
    return stylistId;
  }

  public static void update(int id, String name, String note, int stylistId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name, note = :note, stylistId = :stylistId, WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .addParameter("note", note)
        .addParameter("stylistId", stylistId)
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
      String sql = "SELECT * FROM clients WHERE stylistId = :id ORDER by name;";
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
