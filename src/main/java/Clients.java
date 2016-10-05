import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Clients {
  private int id;
  private String name;
  private String notes;
  private int stylist_id;

  public Clients(String name, String notes, int stylist_id) {
    this.name = name;
    this.notes = notes;
    this.stylist_id = stylist_id;

    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, notes, stylist_id) VALUES (:name, :notes, :stylist_id);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("notes", notes)
        .addParameter("stylist_id", stylist_id)
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
    return notes;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public static void update(int client_id, String name, String notes, int stylist_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name, notes = :notes, stylist_id = :stylist_id WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("notes", notes)
        .addParameter("stylist_id", stylist_id)
        .addParameter("id", client_id)
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

  public static List<Clients> allForStylist(int stylist_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id = :id ORDER by name;";
      return con.createQuery(sql)
        .addParameter("id", stylist_id)
        .executeAndFetch(Clients.class);
    }
  }

  public static Clients find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id = :id;";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Clients.class);
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
